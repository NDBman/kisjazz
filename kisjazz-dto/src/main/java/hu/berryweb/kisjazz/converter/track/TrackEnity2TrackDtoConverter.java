package hu.berryweb.kisjazz.converter.track;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrackEnity2TrackDtoConverter implements Converter<TrackEntity, TrackDto>{

	@Override
	public TrackDto convert(TrackEntity source) {
		log.debug("start");
		TrackDto trackDto =  TrackDto.builder()
				.spotifyId(source.getSpotifyId())
				.build();
		log.debug("stop");
		return trackDto;
	}

}
