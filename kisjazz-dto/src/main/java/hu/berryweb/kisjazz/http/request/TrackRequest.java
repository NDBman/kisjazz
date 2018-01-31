package hu.berryweb.kisjazz.http.request;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Nandi on 2018. 01. 24..
 */
@Data
@Builder
public class TrackRequest {

    private String spotifyId;

}
