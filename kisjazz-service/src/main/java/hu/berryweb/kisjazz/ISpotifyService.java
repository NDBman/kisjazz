package hu.berryweb.kisjazz;

import java.io.IOException;

import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import retrofit2.Response;

public interface ISpotifyService {

	Response<AuthenticationTokenDto> authorize() throws IOException;
}
