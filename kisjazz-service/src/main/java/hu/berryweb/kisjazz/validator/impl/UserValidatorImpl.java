package hu.berryweb.kisjazz.validator.impl;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.UserEntityRepository;
import hu.berryweb.kisjazz.validator.IUserValidator;

@Service
public class UserValidatorImpl implements IUserValidator {

	private final Logger LOG = LoggerFactory.getLogger(UserValidatorImpl.class);

	@Autowired
	private UserEntityRepository userEntityRepository;

	private final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
	        + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
	        + "\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25"
	        + "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0"
	        + "-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
	        + "\\x7f])+)\\])";

	@Override
	public void validateUser(UserEntity userEntity) {
		LOG.debug("start");
		LOG.debug("validate username");
		Validate.inclusiveBetween(5, 30, userEntity.getUsername().length(),
		        "Username cannot be shorter than 5 or longer than 30 character.");
		LOG.debug("validate email");
		Validate.notBlank(userEntity.getEmail(), "Email cannot be blank.");
		Validate.matchesPattern(userEntity.getEmail(), EMAIL_PATTERN, "Invalid email.");
		emailNotExist(userEntity.getEmail());
		LOG.debug("stop");
	}

	private void emailNotExist(String email) {
		if (userEntityRepository.findByEmail(email) != null) {
			throw new DataIntegrityViolationException("Email is already in user.");
		}
	}

}
