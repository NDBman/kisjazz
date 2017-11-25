package hu.berryweb.kisjazz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.UserEntityRepository;
import hu.berryweb.kisjazz.util.PasswordHasher;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public UserDto createUser(String username, String email, String password) {
		UserEntity userEntity = UserEntity
		        .builder()
		        .username(username)
		        .email(email)
		        .passwordHash(PasswordHasher.hash(password))
		        .build();
		userEntityRepository.save(userEntity);
		return conversionService.convert(userEntity, UserDto.class);
	}

}
