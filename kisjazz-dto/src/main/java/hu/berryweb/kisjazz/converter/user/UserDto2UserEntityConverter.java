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
public class UserDto2UserEntityConverter implements Converter<UserDto, UserEntity> {
	
	@Override
	public UserEntity convert(UserDto source) {
		log.debug("strart");
		UserEntity userEntity = UserEntity
				.builder()
				.id(source.getId())
				.name(source.getName())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		log.debug("stop");
		return userEntity;
	}

}
