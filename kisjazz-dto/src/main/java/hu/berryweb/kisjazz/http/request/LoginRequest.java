package hu.berryweb.kisjazz.http.request;

import lombok.Data;

/**
 * Created by Nandi on 2018. 01. 22..
 */
@Data
public class LoginRequest {

    private String email;

    private String password;
}
