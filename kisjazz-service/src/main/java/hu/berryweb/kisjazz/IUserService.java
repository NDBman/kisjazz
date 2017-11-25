package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.dto.UserDto;

public interface IUserService {

	UserDto createUser(String username, String email, String password);
}
