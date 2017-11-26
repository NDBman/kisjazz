package hu.berryweb.kisjazz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.berryweb.kisjazz.dto.UserDto;

@RequestMapping("/users")
public interface IUserRestController {

	@PostMapping
	UserDto createUser(@RequestAttribute("username") String username, @RequestAttribute("email") String email,
	        @RequestAttribute("password") String password);

	@GetMapping("/{userId}")
	UserDto findUser(@PathVariable("userId") Long userId);

	@PutMapping("/{userId}")
	UserDto editUser(@PathVariable("userId") Long userId, @RequestAttribute("email") String email,
	        @RequestAttribute("password") String password);
}
