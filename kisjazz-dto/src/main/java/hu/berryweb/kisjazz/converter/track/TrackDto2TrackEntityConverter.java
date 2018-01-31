package hu.berryweb.kisjazz.converter.track;

import hu.berryweb.kisjazz.dto.TrackDto;
import hu.berryweb.kisjazz.entity.TrackEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class TrackDto2TrackEntityConverter implements Converter<TrackDto, TrackEntity> {

	@Override
	public TrackEntity convert(TrackDto source) {
		return TrackEntity.builder()
				.spotifyId(source.getSpotifyId())
				.build();
	}

}
