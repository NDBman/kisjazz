package hu.berryweb.kisjazz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.controller.IUserRestController;
import hu.berryweb.kisjazz.dto.UserDto;

@RestController
public class UserRestController implements IUserRestController{

	@Autowired
	private IUserService service;
	
	@Override
	public UserDto createUser(String username, String email, String password) {
		return service.createUser(username, email, password);
	}

}
