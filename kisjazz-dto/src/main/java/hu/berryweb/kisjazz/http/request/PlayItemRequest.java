package hu.berryweb.kisjazz.http.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by Nandi on 2018. 03. 13..
 */
@Data
public class PlayItemRequest {

    private String spotifyId;

    private Date requestDate;
}
