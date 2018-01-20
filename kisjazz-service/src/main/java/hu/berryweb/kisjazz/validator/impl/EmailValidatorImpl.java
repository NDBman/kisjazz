package hu.berryweb.kisjazz.validator.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.repository.UserEntityRepository;
import hu.berryweb.kisjazz.validator.IEmailValidator;

@Service
public class EmailValidatorImpl implements IEmailValidator {

	@Autowired
	private UserEntityRepository userEntityRepository;

	private final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
	        + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
	        + "\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25"
	        + "[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0"
	        + "-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-"
	        + "\\x7f])+)\\])";

	@Override
	public void checkEmailPattern(String email) {
		Validate.matchesPattern(email, EMAIL_PATTERN, "Invalid email.");
	}

	@Override
	public void checkEmailNotExists(String email) {
		if (userEntityRepository.findByEmail(email) != null) {
			throw new DataIntegrityViolationException("Email is already in use.");
		}
	}

	@Override
	public void checkEmailNotBlank(String email) {
		Validate.notBlank(email, "Email cannot be blank!");
	}

}
