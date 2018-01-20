package hu.berryweb.kisjazz.dto.converter.music;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.dto.MusicDto;
import hu.berryweb.kisjazz.entity.MusicEntity;

@Service
public class MusicEnity2MusicDtoConverter implements Converter<MusicEntity, MusicDto>{

	@Override
	public MusicDto convert(MusicEntity source) {
		return MusicDto.builder()
				.spotifyId(source.getSpotifyId())
				.build();
	}

}
