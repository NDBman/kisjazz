package hu.berryweb.kisjazz.converter.user;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

@Slf4j
@Service
public class UserEntity2UserDtoConverter implements Converter<UserEntity, UserDto> {
	
	@Override
	public UserDto convert(UserEntity source) {
		log.debug("start");
		UserDto userDto = UserDto
				.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		log.debug("stop");
		return userDto;
	}

}
