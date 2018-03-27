package hu.berryweb.kisjazz.converter.playitem;

import hu.berryweb.kisjazz.dto.PlayItemDto;
import hu.berryweb.kisjazz.entity.PlayItemEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@Slf4j
@Service
public class PlayItemDto2PlayItemEntityConverter implements Converter<PlayItemDto, PlayItemEntity> {
    @Override
    public PlayItemEntity convert(PlayItemDto source) {
        log.debug("start");
        PlayItemEntity playItemEntity = PlayItemEntity
                .builder()
                .id(source.getId())
                .requestDate(source.getRequestDate())
                .spoitfyId(source.getSpotifyId())
                .playing(source.getPlaying())
                .build();
        log.debug("stop");
        return playItemEntity;
    }
}
