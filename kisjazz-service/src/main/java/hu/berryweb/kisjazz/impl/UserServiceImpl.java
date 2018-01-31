package hu.berryweb.kisjazz.impl;

import hu.berryweb.kisjazz.AbstractDtoService;
import hu.berryweb.kisjazz.ITrackService;
import hu.berryweb.kisjazz.IUserService;
import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import hu.berryweb.kisjazz.entity.UserEntity;
import hu.berryweb.kisjazz.repository.IUserEntityRepository;
import hu.berryweb.kisjazz.util.PasswordHasher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl extends AbstractDtoService<UserEntity, UserDto> implements IUserService {

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private ITrackService trackService;

    public UserServiceImpl() {
        super(UserEntity.class, UserDto.class);
    }

    @Override
    public UserDto createUser(String name, String email, String password) {
        log.debug("start");
        UserEntity userEntity = UserEntity.builder().name(name).email(email).passwordHash(PasswordHasher.hash(password))
                .build();
        validatorService.validateUser(userEntity);
        userEntity = userEntityRepository.save(userEntity);
        UserDto userDto = convertEntity(userEntity);
        log.debug("stop");
        return userDto;
    }

    @Override
    public UserDto findUser(Long userId) {
        log.debug("start");
        UserEntity userEntity = userEntityRepository.findOne(userId);
        UserDto userDto = convertEntity(userEntity);
        log.debug("stop");
        return userDto;
    }

    @Override
    public UserDto editUser(Long userId, String email, String password) {
        log.debug("start");
        UserEntity userEntity = userEntityRepository.findOne(userId);
        userEntity.setEmail(email);
        userEntity.setPasswordHash(PasswordHasher.hash(password));
        validatorService.validateUser(userEntity);
        userEntity = userEntityRepository.save(userEntity);
        UserDto userDto = convertEntity(userEntity);
        log.debug("stop");
        return userDto;
    }

    @Override
    public TrackDto addTrackToFavorites(String spotifyId, Long userId) throws UnsupportedEncodingException {
        log.debug("start");
        UserEntity userEntity = userEntityRepository.findOne(userId);
        log.debug("create trackDto");
        TrackDto trackDto = trackService.createTrack(spotifyId, userEntity);
        log.debug("stop");
        return trackDto;
    }

    @Override
    public List<TrackDto> getFavorites(Long userId) {
        log.debug("start");
        UserEntity userEntity = userEntityRepository.findOne(userId);
        List<TrackEntity> favoriteEntites = userEntity.getFavorites();
        log.debug("convert track dtos");
        List<TrackDto> favoriteDtos = (List<TrackDto>) conversionService.convert(favoriteEntites,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TrackEntity.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(TrackDto.class)));
        log.debug("stop");
        return favoriteDtos;
    }

}
