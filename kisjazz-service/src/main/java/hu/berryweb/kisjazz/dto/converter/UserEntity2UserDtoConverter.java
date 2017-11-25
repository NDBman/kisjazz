package hu.berryweb.kisjazz.dto.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

@Service
public class UserEntity2UserDtoConverter implements Converter<UserEntity, UserDto> {

	private final Logger LOG = LoggerFactory.getLogger(UserEntity2UserDtoConverter.class);
	
	@Override
	public UserDto convert(UserEntity source) {
		LOG.debug("start");
		UserDto userDto = UserDto
				.builder()
				.id(source.getId())
				.username(source.getUsername())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		LOG.debug("stop");
		return userDto;
	}

}
