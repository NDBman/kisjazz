package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by Nandi on 2018. 01. 21..
 */
public interface IAuthService {

    AuthenticationToken authenticateUser(String email, String password) throws UnsupportedEncodingException;

    AuthenticationToken refreshAuthentication(RefreshTokenRequest request) throws UnsupportedEncodingException;

    Long authorizeUser(String authorizationHeader) throws UnsupportedEncodingException;

}
