package hu.berryweb.kisjazz.validator.impl;

import hu.berryweb.kisjazz.validator.IPasswordValidator;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

/**
 * Created by Nandi on 2018. 01. 21..
 */
@Service
public class PasswordValidatorImpl implements IPasswordValidator {

    @Override
    public void checkPasswordIsValid(String password) {
        Validate.notBlank(password, "Password cannot be empty!");
        Validate.inclusiveBetween(6, 20, password.length(), "Password must be at least 6 characters long!");


    }
}
