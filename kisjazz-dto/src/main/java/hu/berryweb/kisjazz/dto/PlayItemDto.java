package hu.berryweb.kisjazz.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@Builder
@Data
public class PlayItemDto {

    @SerializedName("id")
    private Long id;

    @SerializedName("spotifyId")
    private String spotifyId;

    @SerializedName("requestDate")
    private Date requestDate;

    @SerializedName("playing")
    private Boolean playing;
}
