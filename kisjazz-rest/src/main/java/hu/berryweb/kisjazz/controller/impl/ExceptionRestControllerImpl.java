package hu.berryweb.kisjazz.controller.impl;

import hu.berryweb.kisjazz.controller.IExceptionController;
import hu.berryweb.kisjazz.http.response.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionRestControllerImpl implements IExceptionController {

    @Override
    public ErrorInfo handleBadRequest(Exception e) {
        log.debug("Bad request. Message: " + e.getMessage());
        return new ErrorInfo(e);

    }

    @Override
    public void handleConflict(Exception e) {
        log.debug("Conflict. Message: " + e.getMessage());
    }

    @Override
    public void handleRefreshTokenExpiredException(Exception e) {
        //Do nothing
    }

    @Override
    public void handleAccessTokenExpiredException(Exception e) {
        //Do nothing
    }

}
