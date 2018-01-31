package hu.berryweb.kisjazz.impl;

import hu.berryweb.kisjazz.AbstractService;
import hu.berryweb.kisjazz.ISpotifyService;
import hu.berryweb.kisjazz.http.AuthenticationToken;
import hu.berryweb.kisjazz.http.types.GrantType;
import hu.berryweb.kisjazz.restcaller.SpotifyRestCaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;

@Service
@Slf4j
public class SpotifyServiceImpl extends AbstractService implements ISpotifyService {

    SpotifyRestCaller restCaller;

    private final static String CLIENT_ID = "ec4832d04b8f42f4b4b663cbcdb0c870";
    private final static String CLIENT_SECRET = "a42b75154bf44a41849e364cd92ecb9f";

    public SpotifyServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restCaller = retrofit.create(SpotifyRestCaller.class);
    }

    @Override
    public Response<AuthenticationToken> authorize() throws IOException {
        log.debug("start");
        String authorizationHeader = createBasicAuthorizationHeader();
        log.debug("stop");
        return restCaller.authorize(GrantType.CLIENT_CREDENTIALS.getValue(), authorizationHeader).execute();
    }

    private String createBasicAuthorizationHeader() {
        StringBuilder authorization = new StringBuilder();
        authorization.append("Basic ");
        authorization.append(Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()));
        return authorization.toString();
    }

}
