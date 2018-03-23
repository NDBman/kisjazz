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
public class PlayItemEntity2PlayItemDtoConverter implements Converter<PlayItemEntity, PlayItemDto>{

    @Override
    public PlayItemDto convert(PlayItemEntity source) {
        log.debug("start");
        PlayItemDto playItemDto = PlayItemDto
                .builder()
                .id(source.getId())
                .spotifyId(source.getSpoitfyId())
                .requestDate(source.getRequestDate())
                .playing(source.getPlaying())
                .build();
        log.debug("stop");
        return playItemDto;
    }
}
