package hu.berryweb.kisjazz.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.MusicEntity;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.MusicEntityRepository;
import hu.berryweb.kisjazz.repository.UserEntityRepository;
import hu.berryweb.kisjazz.util.PasswordHasher;
import hu.berryweb.kisjazz.validator.IEmailValidator;
import hu.berryweb.kisjazz.validator.IUserValidator;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private MusicEntityRepository musicEntityRepository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private IUserValidator userValidator;

	@Autowired
	private IEmailValidator emailValidator;

	private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDto createUser(String name, String email, String password) {
		LOG.debug("start");
		UserEntity userEntity = UserEntity.builder().name(name).email(email).passwordHash(PasswordHasher.hash(password))
		        .build();
		userValidator.validateUser(userEntity);
		userEntity = userEntityRepository.save(userEntity);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto findUser(Long userId) {
		LOG.debug("start");
		UserEntity userEntity = userEntityRepository.findOne(userId);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto editUser(Long userId, String email, String password) {
		LOG.debug("start");
		UserEntity userEntity = userEntityRepository.findOne(userId);
		userEntity.setEmail(email);
		userEntity.setPasswordHash(PasswordHasher.hash(password));
		userValidator.validateUser(userEntity);
		userEntity = userEntityRepository.save(userEntity);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public AuthenticationTokenDto authenticateUser(String email, String password)
	        throws IllegalArgumentException, UnsupportedEncodingException {
		LOG.debug("start");
		emailValidator.checkEmailNotBlank(email);
		emailValidator.checkEmailPattern(email);
		Validate.notBlank(password);
		UserEntity userEntity = userEntityRepository.findByEmail(email);
		if (userEntity != null && userEntity.getPasswordHash().equals(PasswordHasher.hash(password))) {
			Algorithm algorithmHS = Algorithm.HMAC256("secret");
			Map<String, Object> headerClaims = new HashMap<>();
			headerClaims.put("password", password);
			String accessToken = JWT.create().withIssuer("auth0").withHeader(headerClaims)
			        .withClaim("userId", userEntity.getId()).withClaim("email", email).sign(algorithmHS);
			return AuthenticationTokenDto.builder().accessToken(accessToken).tokenType("Bearer").build();
		}
		return null;
	}

	@SuppressWarnings("unused")
	public Long authorizeUser(String authorizationHeader)
	        throws IllegalArgumentException, UnsupportedEncodingException {
		String[] headerContent = authorizationHeader.split("\\s+");
		Algorithm algorithm = Algorithm.HMAC256("secret");
		JWTVerifier verifirer = JWT.require(algorithm).withIssuer("auth0").build();
		DecodedJWT jwt = verifirer.verify(headerContent[1]);
		Claim userIdClaim = jwt.getClaim("userId");

		return userIdClaim.as(Long.class);
	}

	@Override
	public MusicDto addMusicToFavorites(String authorizationHeader, String spotifyId)
	        throws IllegalArgumentException, UnsupportedEncodingException {
		Long userId = authorizeUser(authorizationHeader);
		Validate.notBlank(spotifyId);
		MusicEntity musicEntity = MusicEntity.builder().spotifyId(spotifyId).build();
		UserEntity userEntity = userEntityRepository.findOne(userId);
		userEntity.getFavorites().add(musicEntity);
		userEntityRepository.save(userEntity);
		musicEntityRepository.save(musicEntity);
		MusicDto result = conversionService.convert(musicEntity, MusicDto.class);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MusicDto> getMusics(String authorizationHeader)
	        throws IllegalArgumentException, UnsupportedEncodingException {
		Long useId = authorizeUser(authorizationHeader);
		UserEntity userEntity = userEntityRepository.findOne(useId);
		List<MusicEntity> musicEntites = musicEntityRepository.findByUserEntity(userEntity);

		return (List<MusicDto>) conversionService.convert(musicEntites,
		        TypeDescriptor.collection(List.class, TypeDescriptor.forObject(MusicEntity.class)),
		        TypeDescriptor.collection(List.class, TypeDescriptor.forObject(MusicDto.class)));
	}

}
