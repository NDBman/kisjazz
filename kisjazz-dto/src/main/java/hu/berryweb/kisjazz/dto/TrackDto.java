package hu.berryweb.kisjazz.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackDto {

	@SerializedName("id")
	private Long id;

	@SerializedName("spotifyId")
	private String spotifyId;

}
