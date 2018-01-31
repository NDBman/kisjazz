package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.entity.UserEntity;

/**
 * Created by Nandi on 2018. 01. 21..
 */
public interface IValidatorService {

    void validateUser(UserEntity userEntity);

    void validateEmail(String email);

    void checkEmailPattern(String email);

    void checkEmailNotExists(String email);

    void checkEmailNotBlank(String email);

    void checkPasswordIsValid(String password);
}
