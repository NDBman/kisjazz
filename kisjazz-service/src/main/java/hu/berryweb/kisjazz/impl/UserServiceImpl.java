package hu.berryweb.kisjazz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.UserEntityRepository;
import hu.berryweb.kisjazz.util.PasswordHasher;
import hu.berryweb.kisjazz.validator.IUserValidator;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private IUserValidator validator;

	private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public UserDto createUser(String username, String email, String password) {
		LOG.debug("start");
		UserEntity userEntity = UserEntity.builder().username(username).email(email)
		        .passwordHash(PasswordHasher.hash(password)).build();
		validator.validateUser(userEntity);
		userEntity = userEntityRepository.save(userEntity);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto findUser(Long userId) {
		LOG.debug("start");
		UserEntity userEntity = userEntityRepository.findOne(userId);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

	@Override
	public UserDto editUser(Long userId, String email, String password) {
		LOG.debug("start");
		UserEntity userEntity = userEntityRepository.findOne(userId);
		userEntity.setEmail(email);
		userEntity.setPasswordHash(PasswordHasher.hash(password));
		validator.validateUser(userEntity);
		userEntity = userEntityRepository.save(userEntity);
		UserDto userDto = conversionService.convert(userEntity, UserDto.class);
		LOG.debug("stop");
		return userDto;
	}

}
