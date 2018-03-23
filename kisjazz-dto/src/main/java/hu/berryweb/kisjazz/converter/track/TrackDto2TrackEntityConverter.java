package hu.berryweb.kisjazz.converter.track;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrackDto2TrackEntityConverter implements Converter<TrackDto, TrackEntity> {

	@Override
	public TrackEntity convert(TrackDto source) {
		log.debug("start");

		TrackEntity trackEntity =  TrackEntity.builder()
				.spotifyId(source.getSpotifyId())
				.build();
		log.debug("stop");
		return trackEntity;
	}

}
