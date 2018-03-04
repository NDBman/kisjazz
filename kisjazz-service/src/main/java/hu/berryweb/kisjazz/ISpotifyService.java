package hu.berryweb.kisjazz;

import java.io.IOException;

import hu.berryweb.kisjazz.http.AuthenticationToken;
import retrofit2.Response;

public interface ISpotifyService {

	Response<AuthenticationToken> authorize() throws IOException;
}
