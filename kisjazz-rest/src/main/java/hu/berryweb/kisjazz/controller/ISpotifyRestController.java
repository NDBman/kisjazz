package hu.berryweb.kisjazz.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;

@RequestMapping("/spotify")
public interface ISpotifyRestController {

	@CrossOrigin
	@GetMapping("/authorize")
	AuthenticationTokenDto authorize() throws IOException;
}
