package hu.berryweb.kisjazz.controller.impl;

import hu.berryweb.kisjazz.IAuthService;
import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.controller.IUserRestController;
import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.http.request.TrackRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.http.Body;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
public class UserRestControllerImpl implements IUserRestController {

    @Autowired
    private IUserService service;

    @Autowired
    private IAuthService authService;

    /**
     *
     * @param name
     * @param email
     * @param password
     * @return
     */
    @Override
    public UserDto createUser(String name, String email, String password) {
        log.debug("start");
        log.debug("Create user -  Name: " + name + " Email: " + email);
        UserDto userDto = service.createUser(name, email, password);
        log.debug("stop");
        return userDto;
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public UserDto findUser(@PathVariable("userId") Long userId) {
        log.debug("start");
        log.debug("Find user with id: " + userId);
        UserDto userDto = service.findUser(userId);
        log.debug("stop");
        return userDto;
    }

    /**
     *
     * @param userId
     * @param email
     * @param password
     * @return
     */
    @Override
    public UserDto editUser(@PathVariable("userId") Long userId, String email, String password) {
        log.debug("start");
        log.debug("Editing user with id: " + userId + ", set Email to: " + email);
        UserDto userDto = service.editUser(userId, email, password);
        log.debug("stop");
        return userDto;
    }


    /**
     *
     * @param Authorization
     * @param spotifyId
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public TrackDto addTrackToFavorites(@RequestHeader String Authorization, @PathVariable("spotifyId") String spotifyId) throws UnsupportedEncodingException {
        log.debug("start");
        Long userId = authService.authorizeUser(Authorization);
        TrackDto trackDto = service.addTrackToFavorites(spotifyId, userId);
        log.debug("stop");
        return trackDto;
    }

    /**
     * @param Authorization
     * @return
     * @throws UnsupportedEncodingException
     */
    @Override
    public List<TrackDto> getFavorites(@RequestHeader String Authorization) throws UnsupportedEncodingException {
        log.debug("start");
        Long userId = authService.authorizeUser(Authorization);
        List<TrackDto> trackDtos = service.getFavorites(userId);
        log.debug("stop");
        return trackDtos;
    }


}
