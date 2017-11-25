package hu.berryweb.kisjazz.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.berryweb.kisjazz.dto.UserDto;

@RequestMapping("/users")
public interface IUserRestController {

	@PostMapping
	UserDto createUser(@RequestAttribute("username") String username, @RequestAttribute("email") String email,
	        @RequestAttribute("password") String password);
}
