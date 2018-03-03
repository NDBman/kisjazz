package hu.berryweb.kisjazz.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.berryweb.kisjazz.AbstractService;
import hu.berryweb.kisjazz.IAuthService;
import hu.berryweb.kisjazz.IValidatorService;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.exception.AccessTokenExpiredException;
import hu.berryweb.kisjazz.exception.RefreshTokenExpiredException;
import hu.berryweb.kisjazz.exception.AuthenticationException;
import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;
import hu.berryweb.kisjazz.repository.IUserEntityRepository;
import hu.berryweb.kisjazz.util.PasswordHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Nandi on 2018. 01. 21..
 */
@Slf4j
@Service
public class AuthServiceImpl extends AbstractService implements IAuthService {

    @Autowired
    private IValidatorService validatorService;

    @Autowired
    private IUserEntityRepository IUserEntityRepository;

    private final String CLIENT_SECRET = "secret";
    private final String REFRESH_TOKEN_SECRET = "rsecret";

    private final Integer ACCESS_EXPIRES_IN = 10;
    private final Integer REFRESH_EXPIRES_IN = ACCESS_EXPIRES_IN * 10;

    @Override
    public AuthenticationToken authenticateUser(String email, String password) throws UnsupportedEncodingException {
        log.debug("start");
        UserEntity userEntity = IUserEntityRepository.findByEmail(email);
        if (userEntity != null && userEntity.getPasswordHash().equals(PasswordHasher.hash(password))) {
            log.debug("create authentication dto.");
            AuthenticationToken authToken = createAuthenticationToken(userEntity.getId());
            return authToken;
        }
        throw new AuthenticationException("Hibás e-mail cím vagy jelszó!");
    }

    private AuthenticationToken createAuthenticationToken(Long userId) throws UnsupportedEncodingException {
        Calendar nowAccess = Calendar.getInstance();
        Calendar nowRefresh = Calendar.getInstance();
        nowAccess.add(Calendar.SECOND, ACCESS_EXPIRES_IN);
        Algorithm algorithmAT = Algorithm.HMAC256(CLIENT_SECRET);
        String accessToken = JWT.create()
                .withIssuer("auth0")
                .withClaim("uid", userId)
                .withExpiresAt(nowAccess.getTime())
                .sign(algorithmAT);
        Random random = new Random();
        Algorithm algorithmRT = Algorithm.HMAC256(REFRESH_TOKEN_SECRET);
        nowRefresh.add(Calendar.SECOND, REFRESH_EXPIRES_IN);
        String refreshToken = JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(nowAccess.getTime())
                .withClaim("uid", userId)
                .withClaim("rn", random.nextDouble())
                .sign(algorithmRT);
        AuthenticationToken authToken = AuthenticationToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(ACCESS_EXPIRES_IN)
                .tokenType("Bearer")
                .build();
        return authToken;
    }

    @Override
    public AuthenticationToken refreshAuthentication(RefreshTokenRequest request) throws UnsupportedEncodingException {
        log.debug("start");
        Long userId;
        try {
            userId = verifyToken(request.getRefreshToken(), REFRESH_TOKEN_SECRET);
        } catch (TokenExpiredException e) {
            log.debug("Refresh token expired");
            throw new RefreshTokenExpiredException();
        }

        AuthenticationToken authToken = createAuthenticationToken(userId);
        log.debug("stop");
        return authToken;

    }

    private Long verifyToken(String token, String secret) throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Claim userIdClaim = decodedJWT.getClaim("uid");
        return userIdClaim.asLong();
    }

    @Override
    public Long authorizeUser(String authorizationHeader) throws UnsupportedEncodingException {
        log.debug("start");
        String token = authorizationHeader.split("\\s")[1];
        Long userId;
        try {
            userId = verifyToken(token, CLIENT_SECRET);
        } catch (TokenExpiredException e) {
            log.debug("Access token expired");
            throw new AccessTokenExpiredException();
        }

        return userId;
    }
}
