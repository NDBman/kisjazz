package hu.berryweb.kisjazz.impl;

import hu.berryweb.kisjazz.AbstractDtoService;
import hu.berryweb.kisjazz.IPlayerService;
import hu.berryweb.kisjazz.dto.PlayItemDto;
import hu.berryweb.kisjazz.entity.PlayItemEntity;
import hu.berryweb.kisjazz.exception.TooEarlyPlayItemRequestException;
import hu.berryweb.kisjazz.repository.IPlayItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Nandi on 2018. 03. 12..
 */
@Service
@Slf4j
public class PlayerServiceImpl extends AbstractDtoService<PlayItemEntity, PlayItemDto> implements IPlayerService {

    @Autowired
    private IPlayItemRepository playItemRepository;

    public PlayerServiceImpl() {
        super(PlayItemEntity.class, PlayItemDto.class);
    }

    @Override
    public PlayItemDto addPlayItem(String spotifyId, Date requestDate) {
        log.debug("start");
        PlayItemEntity newPlayItemEntity = PlayItemEntity
                .builder()
                .spoitfyId(spotifyId)
                .requestDate(requestDate)
                .playing(false)
                .build();
        List<PlayItemEntity> playItemEntities = playItemRepository.findAllByOrderByOrderNumber();

        log.debug("is play item request already taken?");
        isPLayItemAlreadyRequested(playItemEntities, newPlayItemEntity);

        log.debug("insert play item entity randomly into list");
        insertPlayItemRandomly(playItemEntities, newPlayItemEntity);

        playItemRepository.save(playItemEntities);
        newPlayItemEntity = playItemRepository.save(newPlayItemEntity);
        log.debug("convert entity");
        PlayItemDto playItemDto = convertEntity(newPlayItemEntity);
        log.debug("stop");
        return playItemDto;
    }

    private void isPLayItemAlreadyRequested(List<PlayItemEntity> playItemEntities, PlayItemEntity newPlayItemEntity) {
        log.debug("start");
        for(PlayItemEntity playItemEntity : playItemEntities) {
            if(playItemEntity.getSpoitfyId().equals(newPlayItemEntity.getSpoitfyId())) {
                long diffInMillis = newPlayItemEntity.getRequestDate().getTime() - playItemEntity.getRequestDate().getTime();
                long diff = TimeUnit.MINUTES.convert(diffInMillis, TimeUnit.MILLISECONDS);
                log.debug("Time difference in minutes: " + diff);
                if(diff <= 20) {
                    throw new TooEarlyPlayItemRequestException();
                }
            }
        }
        log.debug("stop");
    }

    private void insertPlayItemRandomly(List<PlayItemEntity> playItemEntities, PlayItemEntity newPlayItemEntity) {
        log.debug("start");
        if(playItemEntities.isEmpty()) {
            log.debug("playlist was empty");
            newPlayItemEntity.setOrderNumber(0);
        } else if(playItemEntities.size() == 1) {
            log.debug("playlist has one item");
            newPlayItemEntity.setOrderNumber(1);
        } else {
            Random randomizer = new Random();
            int randomPosition = randomizer.nextInt(playItemEntities.size()) + 1;
            newPlayItemEntity.setOrderNumber(randomPosition);
            log.debug("random position: " + randomPosition);
            log.debug("size of playlist: " + playItemEntities.size());
            for(int i = randomPosition; i < playItemEntities.size(); i++) {
                log.debug("playItemEntity's order number: " + playItemEntities.get(i).getOrderNumber());
                playItemEntities.get(i).setOrderNumber(playItemEntities.get(i).getOrderNumber() + 1);
                log.debug("playItemEntity's new order number: " + playItemEntities.get(i).getOrderNumber());
            }
        }
        log.debug("stop");
    }

    @Override
    public List<PlayItemDto> findAllPlayItem() {
        log.debug("start");
        List<PlayItemEntity> playItemEntities = playItemRepository.findAllByOrderByOrderNumber();

        log.debug("convert");
        List<PlayItemDto> playItemDtos = convertEntityList(playItemEntities);
        log.debug("stop");
        return playItemDtos;
    }

    @Override
    public PlayItemDto playPlayItem() {
        log.debug("start");
        removeExistingPlayItemIfExists();
        PlayItemEntity newPlayItemToBePlayed = setUpNewPlayItemToBePlayed();
        PlayItemDto playItemDto = convertEntity(newPlayItemToBePlayed);
        log.debug("stop");
        return playItemDto;
    }

    private void removeExistingPlayItemIfExists() {
        log.debug("start");
        List<PlayItemEntity> playItemEntitiesToBeModified = playItemRepository.findAll();
        PlayItemEntity playItemEntityToBeRemoved = playItemEntitiesToBeModified.stream()
                .filter(p -> p.getPlaying() == true)
                .findFirst().orElse(null);
        if(playItemEntityToBeRemoved != null) {
            playItemRepository.delete(playItemEntityToBeRemoved);
        }
        log.debug("stop");
    }

    private PlayItemEntity setUpNewPlayItemToBePlayed() {
        log.debug("start");
        PlayItemEntity newPlayItemToBePlayed = playItemRepository.findAllByOrderByOrderNumber().stream().findFirst().orElse(null);
        if(newPlayItemToBePlayed != null) {
            newPlayItemToBePlayed.setPlaying(true);
        }
        newPlayItemToBePlayed = playItemRepository.save(newPlayItemToBePlayed);
        log.debug("stop");
        return newPlayItemToBePlayed;
    }

}
