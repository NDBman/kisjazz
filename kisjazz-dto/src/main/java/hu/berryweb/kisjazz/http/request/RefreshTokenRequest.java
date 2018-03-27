package hu.berryweb.kisjazz.http.request;

import lombok.Data;

/**
 * Created by Nandi on 2018. 01. 25..
 */
@Data
public class RefreshTokenRequest {

    private String grantType;

    private String refreshToken;
}
