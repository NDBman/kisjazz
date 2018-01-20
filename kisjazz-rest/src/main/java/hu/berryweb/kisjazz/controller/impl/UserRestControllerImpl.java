package hu.berryweb.kisjazz.controller.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.controller.IUserRestController;
import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.dto.UserDto;

@RestController
public class UserRestControllerImpl implements IUserRestController {

	@Autowired
	private IUserService service;

	private final Logger LOG = LoggerFactory.getLogger(UserRestControllerImpl.class);

	@Override
	public UserDto createUser(String name, String email, String password) {
		LOG.debug("start");
		LOG.debug("Create user -  Name: " + name + " Email: " + email);
		UserDto userDto = service.createUser(name, email, password);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto findUser(@PathVariable("userId") Long userId) {
		LOG.debug("start");
		LOG.debug("Find user with id: " + userId);
		UserDto userDto = service.findUser(userId);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto editUser(@PathVariable("userId") Long userId, String email, String password) {
		LOG.debug("start");
		LOG.debug("Editing user with id: " + userId + ", set Email to: " + email);
		UserDto userDto = service.editUser(userId, email, password);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public AuthenticationTokenDto authenticatieUser(String email, String password)
	        throws IllegalArgumentException, UnsupportedEncodingException {
		LOG.debug("start");
		LOG.debug("Authenticate user with email: " + email);
		AuthenticationTokenDto token = service.authenticateUser(email, password);
		LOG.debug("stop");
		return token;
	}

	@Override
	public MusicDto addMusicToFavorites(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("spotifyId") String spotifyId) throws IllegalArgumentException, UnsupportedEncodingException {
		LOG.debug("start");
		LOG.debug("Add music: " + spotifyId + " - To favorites");
		MusicDto result = service.addMusicToFavorites(authorizationHeader, spotifyId);
		LOG.debug("stop");
		return result;
	}

	@Override
	public List<MusicDto> getMusicsOfUser(@RequestHeader String Authorization) throws IllegalArgumentException, UnsupportedEncodingException {
		LOG.debug("start");
		LOG.debug("Get musicList");
		List<MusicDto> results = service.getMusics(Authorization);
		LOG.debug("stop");
		return results;
	}

	

	

}
