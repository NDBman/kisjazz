package hu.berryweb.kisjazz;

import hu.berryweb.kisjazz.dto.PlayItemDto;

import java.util.Date;
import java.util.List;

/**
 * Created by Nandi on 2018. 03. 04..
 */
public interface IPlayerService {

    PlayItemDto addPlayItem(String spotifyId, Date requestDate);

    List<PlayItemDto> findAllPlayItem();

    PlayItemDto playPlayItem();
}
