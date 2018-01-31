package hu.berryweb.kisjazz.controller;

import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.request.LoginRequest;
import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

/**
 * Created by Nandi on 2018. 01. 21..
 */
@RequestMapping(value = "/auth",
        consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public interface IAuthController {

    @CrossOrigin
    @PostMapping("/authenticate")
    AuthenticationToken authenticateUser(LoginRequest request) throws UnsupportedEncodingException;

    @CrossOrigin
    @PostMapping(value = "/refreshToken")
    AuthenticationToken refreshAuthentication(RefreshTokenRequest request) throws UnsupportedEncodingException;

}
