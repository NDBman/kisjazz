package hu.berryweb.kisjazz.controller.impl;

import hu.berryweb.kisjazz.IAuthService;
import hu.berryweb.kisjazz.controller.IAuthRestController;
import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.request.LoginRequest;
import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Created by Nandi on 2018. 01. 21..
 */
@Slf4j
@RestController
public class AuthRestRestControllerImpl implements IAuthRestController {

    @Autowired
    private IAuthService authService;

    @Override
    public AuthenticationToken authenticateUser(LoginRequest request) throws UnsupportedEncodingException {
        log.debug("start");
        AuthenticationToken authToken = authService.authenticateUser(request.getEmail(), request.getPassword());
        log.debug("stop");
        return authToken;
    }

    @Override
    public AuthenticationToken refreshAuthentication(RefreshTokenRequest request) throws UnsupportedEncodingException {
        log.debug("start");
        AuthenticationToken authToken = authService.refreshAuthentication(request);
        log.debug("stop");
        return authToken;
    }
}
