package hu.berryweb.kisjazz.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.dto.UserDto;

@RequestMapping("/users")
public interface IUserRestController {

	@CrossOrigin
	@PostMapping
	UserDto createUser(@RequestAttribute("name") String name, @RequestAttribute("email") String email,
	        @RequestAttribute("password") String password);

	@GetMapping("/{userId}")
	UserDto findUser(@PathVariable("userId") Long userId);

	@PutMapping("/{userId}")
	UserDto editUser(@PathVariable("userId") Long userId, @RequestAttribute("email") String email,
	        @RequestAttribute("password") String password);
	
	@CrossOrigin
	@PostMapping("/authenticate")
	AuthenticationTokenDto authenticatieUser(@RequestAttribute("email") String email, @RequestAttribute("password") String password)
	        throws IllegalArgumentException, UnsupportedEncodingException;
	
	@CrossOrigin
	@PostMapping("/favorites/add/{spotifyId}")
	MusicDto addMusicToFavorites(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("spotifyId") String spotifyId) throws IllegalArgumentException, UnsupportedEncodingException;
	
	@CrossOrigin
	@GetMapping("/favorites")
	List<MusicDto> getMusicsOfUser(@RequestHeader String Authorization) throws IllegalArgumentException, UnsupportedEncodingException;
}
