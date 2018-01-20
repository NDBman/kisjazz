package hu.berryweb.kisjazz;

import java.io.UnsupportedEncodingException;
import java.util.List;

import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.dto.UserDto;

public interface IUserService {

	UserDto createUser(String name, String email, String password);

	UserDto findUser(Long userId);
	
	UserDto editUser(Long userId, String email, String password);
	
	AuthenticationTokenDto authenticateUser(String email, String password) throws IllegalArgumentException, UnsupportedEncodingException;
	
	MusicDto addMusicToFavorites(String authorizationHeader, String spotifyId) throws IllegalArgumentException, UnsupportedEncodingException;
	
	List<MusicDto> getMusics(String authorizationHeader) throws IllegalArgumentException, UnsupportedEncodingException;
	
}
