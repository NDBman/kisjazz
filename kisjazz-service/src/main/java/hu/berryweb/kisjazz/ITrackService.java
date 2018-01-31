package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.UserEntity;

/**
 * Created by Nandi on 2018. 01. 23..
 */
public interface ITrackService {

    TrackDto createTrack(String spotifyId, UserEntity userEntity);
}
