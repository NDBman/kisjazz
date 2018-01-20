package hu.berryweb.kisjazz.validator.impl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.validator.IEmailValidator;
import hu.berryweb.kisjazz.validator.IUserValidator;

@Service
public class UserValidatorImpl implements IUserValidator {

	private final Logger LOG = LoggerFactory.getLogger(UserValidatorImpl.class);

	
	@Autowired
	private IEmailValidator emailValidator;

	@Override
	public void validateUser(UserEntity userEntity) {
		LOG.debug("start");
		LOG.debug("validate username");
		Validate.inclusiveBetween(3, 30, userEntity.getName().length(),
		        "Name cannot be shorter than 3 or longer than 30 character.");
		LOG.debug("validate email");
		emailValidator.checkEmailNotBlank(userEntity.getEmail());
		emailValidator.checkEmailPattern(userEntity.getEmail());
		emailValidator.checkEmailNotExists(userEntity.getEmail());
		LOG.debug("stop");
	}



}
