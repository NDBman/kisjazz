package hu.berryweb.kisjazz.controller.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import hu.berryweb.kisjazz.ISpotifyService;
import hu.berryweb.kisjazz.controller.ISpotifyRestController;
import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import retrofit2.Response;

@RestController
public class SpotifyRestControllerImpl implements ISpotifyRestController{

	@Autowired
	private ISpotifyService service;
	
	private final Logger LOG = LoggerFactory.getLogger(SpotifyRestControllerImpl.class);
	
	@Override
	public AuthenticationTokenDto authorize() throws IOException {
		LOG.debug("start");
		Response<AuthenticationTokenDto> response = service.authorize();
		LOG.debug("stop");
		return response.body();
	}

}
