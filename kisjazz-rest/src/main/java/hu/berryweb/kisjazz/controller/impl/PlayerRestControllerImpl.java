package hu.berryweb.kisjazz.controller.impl;

import hu.berryweb.kisjazz.IPlayerService;
import hu.berryweb.kisjazz.controller.IPlayerRestController;
import hu.berryweb.kisjazz.dto.PlayItemDto;
import hu.berryweb.kisjazz.http.request.PlayItemRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@Slf4j
@RestController
public class PlayerRestControllerImpl implements IPlayerRestController{

    @Autowired
    private IPlayerService service;

    @Override
    public PlayItemDto addPlayItem(PlayItemRequest request) {
        log.debug("start");
        PlayItemDto playItemDto = service.addPlayItem(request.getSpotifyId(), request.getRequestDate());
        log.debug("stop");
        return playItemDto;
    }

    @Override
    public List<PlayItemDto> findAllPlayItem() {
        log.debug("start");
        List<PlayItemDto> playItemDtos = service.findAllPlayItem();
        log.debug("stop");
        return playItemDtos;
    }

    @Override
    public PlayItemDto playPlayItem() {
        log.debug("start");
        PlayItemDto playItemDto = service.playPlayItem();
        log.debug("stop");
        return playItemDto;
    }
}
