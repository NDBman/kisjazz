package hu.berryweb.kisjazz.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import hu.berryweb.kisjazz.exception.AccessTokenExpiredException;
import hu.berryweb.kisjazz.exception.RefreshTokenExpiredException;
import hu.berryweb.kisjazz.exception.AuthenticationException;
import hu.berryweb.kisjazz.exception.TooEarlyPlayItemRequestException;
import hu.berryweb.kisjazz.http.response.ErrorInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface IExceptionController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    ErrorInfo handleBadRequest(Exception e);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    void handleConflict(Exception e);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({RefreshTokenExpiredException.class, JWTDecodeException.class})
    void handleRefreshTokenExpiredException(Exception e);

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(AccessTokenExpiredException.class)
    void handleAccessTokenExpiredException(Exception e);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    ErrorInfo handleAuthenticationException(Exception e);

    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    @ExceptionHandler(TooEarlyPlayItemRequestException.class)
    void handleTooEarlyPlayItemRequest(Exception e);
}
