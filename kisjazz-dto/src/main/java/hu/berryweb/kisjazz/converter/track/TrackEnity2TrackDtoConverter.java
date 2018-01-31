package hu.berryweb.kisjazz.converter.track;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.dto.UserDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class TrackEnity2TrackDtoConverter implements Converter<TrackEntity, TrackDto>{

	@Override
	public TrackDto convert(TrackEntity source) {
		return TrackDto.builder()
				.spotifyId(source.getSpotifyId())
				.build();
	}

}
