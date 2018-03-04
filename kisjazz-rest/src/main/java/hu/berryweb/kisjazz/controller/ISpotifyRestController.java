package hu.berryweb.kisjazz.controller;

import java.io.IOException;

import hu.berryweb.kisjazz.http.request.RefreshTokenRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.berryweb.kisjazz.http.AuthenticationToken;

@RequestMapping(value = "/spotify", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_VALUE})
public interface ISpotifyRestController {

	@CrossOrigin
	@GetMapping("/authorize")
	AuthenticationToken authorize() throws IOException;

}
