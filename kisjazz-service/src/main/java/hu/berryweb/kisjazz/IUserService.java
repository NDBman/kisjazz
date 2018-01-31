package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

public interface IUserService {

    /**
     * @param name
     * @param email
     * @param password
     * @return
     */
    UserDto createUser(String name, String email, String password);

    /**
     * @param userId
     * @return
     */
    UserDto findUser(Long userId);

    /**
     * @param userId
     * @param email
     * @param password
     * @return
     */
    UserDto editUser(Long userId, String email, String password);

    /**
     * @param spotifyId
     * @param userId
     * @return
     * @throws UnsupportedEncodingException
     */
    TrackDto addTrackToFavorites(String spotifyId, Long userId) throws UnsupportedEncodingException;

    /**
     * @param userId
     * @return
     */
    List<TrackDto> getFavorites(Long userId);


}
