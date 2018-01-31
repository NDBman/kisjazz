package hu.berryweb.kisjazz.controller;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Nandi
 */
@RequestMapping("/users")
public interface IUserRestController {

    /**
     *
     * @param name
     * @param email
     * @param password
     * @return
     */
    @CrossOrigin
    @PostMapping
    UserDto createUser(@RequestAttribute("name") String name, @RequestAttribute("email") String email,
                       @RequestAttribute("password") String password);

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    UserDto findUser(@PathVariable("userId") Long userId);

    /**
     *
     * @param userId
     * @param email
     * @param password
     * @return
     */
    @PutMapping("/{userId}")
    UserDto editUser(@PathVariable("userId") Long userId, @RequestAttribute("email") String email,
                     @RequestAttribute("password") String password);

    /**
     *
     * @param Authorization
     * @param spotifyId
     * @return
     * @throws UnsupportedEncodingException
     */
    @CrossOrigin
    @PostMapping("/favorites/add/{spotifyId}")
    TrackDto addTrackToFavorites(@RequestHeader String Authorization, @PathVariable("spotifyId") String spotifyId) throws UnsupportedEncodingException;

    /**
     * @param Authorization
     * @return
     */
    @CrossOrigin
    @GetMapping("/favorites")
    List<TrackDto> getFavorites(@RequestHeader String Authorization) throws UnsupportedEncodingException;


}
