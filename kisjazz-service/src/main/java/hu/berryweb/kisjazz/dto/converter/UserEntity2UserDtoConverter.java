package hu.berryweb.kisjazz.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

@Service
public class UserEntity2UserDtoConverter implements Converter<UserEntity, UserDto> {

	@Override
	public UserDto convert(UserEntity source) {
		UserDto userDto = UserDto
				.builder()
				.id(source.getId())
				.username(source.getUsername())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		return userDto;
	}

}
