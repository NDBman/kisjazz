package hu.berryweb.kisjazz.impl;

import hu.berryweb.kisjazz.IValidatorService;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.validator.IEmailValidator;
import hu.berryweb.kisjazz.validator.IPasswordValidator;
import hu.berryweb.kisjazz.validator.IUserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Nandi on 2018. 01. 21..
 */
@Slf4j
@Service
public class ValidatorServiceImpl implements IValidatorService {

    @Autowired
    private IUserValidator userValidator;

    @Autowired
    private IEmailValidator emailValidator;

    @Autowired
    private IPasswordValidator passwordValidator;

    @Override
    public void validateUser(UserEntity userEntity) {
        userValidator.validateUser(userEntity);
    }

    @Override
    public void validateEmail(String email) {
        checkEmailNotBlank(email);
        checkEmailPattern(email);
        checkEmailNotExists(email);
    }

    @Override
    public void checkEmailPattern(String email) {
        emailValidator.checkEmailPattern(email);
    }

    @Override
    public void checkEmailNotExists(String email) {
        emailValidator.checkEmailNotExists(email);
    }

    @Override
    public void checkEmailNotBlank(String email) {
    emailValidator.checkEmailNotBlank(email);
    }

    @Override
    public void checkPasswordIsValid(String password) {
        passwordValidator.checkPasswordIsValid(password);
    }
}
