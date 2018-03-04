package hu.berryweb.kisjazz.controller.impl;

import hu.berryweb.kisjazz.ISpotifyService;
import hu.berryweb.kisjazz.controller.ISpotifyRestController;
import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@RestController
public class SpotifyRestControllerImpl implements ISpotifyRestController {

    @Autowired
    private ISpotifyService service;

    @Override
    public AuthenticationToken authorize() throws IOException {
        log.debug("start");
        Response<AuthenticationToken> response = service.authorize();
        log.debug("stop");
        return response.body();
    }

}
