package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.dto.UserDto;

public interface IUserService {

	UserDto createUser(String username, String email, String password);

	UserDto findUser(Long userId);
	
	UserDto editUser(Long userId, String email, String password);
}
