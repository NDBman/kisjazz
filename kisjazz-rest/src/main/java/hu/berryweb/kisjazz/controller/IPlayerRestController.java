package hu.berryweb.kisjazz.controller;

import hu.berryweb.kisjazz.dto.PlayItemDto;
import hu.berryweb.kisjazz.http.request.PlayItemRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@RequestMapping(value = "/player", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public interface IPlayerRestController {

    @CrossOrigin
    @PostMapping("/add")
    PlayItemDto addPlayItem(PlayItemRequest request);

    @CrossOrigin
    @GetMapping
    List<PlayItemDto> findAllPlayItem();

    @CrossOrigin
    @GetMapping("/play")
    PlayItemDto playPlayItem();
}
