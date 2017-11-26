package hu.berryweb.kisjazz.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.controller.IUserRestController;
import hu.berryweb.kisjazz.dto.UserDto;

@RestController
public class UserRestController implements IUserRestController {

	@Autowired
	private IUserService service;

	private final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	@Override
	public UserDto createUser(String username, String email, String password) {
		LOG.debug("start");
		LOG.debug("Create user -  Username: " + username + " Email: " + email);
		UserDto userDto = service.createUser(username, email, password);
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

}
