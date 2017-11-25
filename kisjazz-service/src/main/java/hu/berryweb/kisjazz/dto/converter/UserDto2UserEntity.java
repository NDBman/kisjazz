package hu.berryweb.kisjazz.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

@Service
public class UserDto2UserEntity implements Converter<UserDto, UserEntity> {

	@Override
	public UserEntity convert(UserDto source) {
		UserEntity userEntity = UserEntity
				.builder()
				.id(source.getId())
				.username(source.getUsername())
				.email(source.getEmail())
				.passwordHash(source.getPasswordHash())
				.build();
		
		return userEntity;
	}

}
