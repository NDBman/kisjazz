package hu.berryweb.kisjazz.impl;

import hu.berryweb.kisjazz.AbstractDtoService;
import hu.berryweb.kisjazz.ITrackService;
import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.ITrackEntityRepository;
import hu.berryweb.kisjazz.repository.IUserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nandi on 2018. 01. 23..
 */
@Slf4j
@Service
public class TrackServiceImpl extends AbstractDtoService<TrackEntity, TrackDto> implements ITrackService {

    @Autowired
    private ITrackEntityRepository trackEntityRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    public TrackServiceImpl() {
        super(TrackEntity.class, TrackDto.class);
    }

    @Override
    public List<TrackDto> createTrack(String spotifyId, UserEntity userEntity) {
        log.debug("start");
        TrackEntity trackEntity = TrackEntity.builder()
                .spotifyId(spotifyId)
                .userEntity(userEntity)
                .build();
        userEntity.getFavorites().add(trackEntity);
        trackEntity = trackEntityRepository.save(trackEntity);
        userEntityRepository.save(userEntity);
        List<TrackDto> trackDtos = convertEntityList(userEntity.getFavorites());
        log.debug("stop");
        return trackDtos;

    }

    @Override
    public List<TrackDto> removeTrack(String spotifyId, Long userId) {
        log.debug("start");
        TrackEntity trackEntity = trackEntityRepository.findBySpotifyId(spotifyId);
        log.debug("delete track entity. spotifyId - " + spotifyId);
        log.debug("find user by id.");
        UserEntity userEntity = userEntityRepository.findOne(userId);
        userEntity.getFavorites().remove(trackEntity);
        userEntityRepository.save(userEntity);
        trackEntityRepository.delete(trackEntity);
        log.debug("get users' tracks. email - " + userEntity.getEmail());
        List<TrackEntity> trackEntityList = trackEntityRepository.findByUserEntity(userEntity);
        log.debug("stop");
        return convertEntityList(trackEntityList);
    }

}
