package hu.berryweb.kisjazz.dto.converter.music;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.entity.MusicEntity;

@Service
public class MusicDto2MusicEntityConverter implements Converter<MusicDto, MusicEntity> {

	@Override
	public MusicEntity convert(MusicDto source) {
		return MusicEntity.builder()
				.spotifyId(source.getSpotifyId())
				.build();
	}

}
