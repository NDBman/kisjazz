package hu.berryweb.kisjazz.dto.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

@Service
public class UserDto2UserEntity implements Converter<UserDto, UserEntity> {

	private final Logger LOG = LoggerFactory.getLogger("hu.berryweb.kisjazz");
	
	@Override
	public UserEntity convert(UserDto source) {
		LOG.debug("strart");
		UserEntity userEntity = UserEntity
				.builder()
				.id(source.getId())
				.username(source.getUsername())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		LOG.debug("stop");
		return userEntity;
	}

}
