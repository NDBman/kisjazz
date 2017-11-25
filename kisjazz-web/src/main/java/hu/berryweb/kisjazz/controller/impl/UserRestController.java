package hu.berryweb.kisjazz.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		UserDto userDto = service.createUser(username, email, password);
		LOG.debug("stop");
		return userDto;
	}

}
