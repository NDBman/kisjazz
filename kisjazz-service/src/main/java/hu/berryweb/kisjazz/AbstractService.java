package hu.berryweb.kisjazz;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Nandi on 2018. 01. 24..
 */
public abstract class AbstractService {

    @Autowired
    protected IValidatorService validatorService;

}
