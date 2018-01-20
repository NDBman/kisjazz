package hu.berryweb.kisjazz.impl;

import java.io.IOException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.berryweb.kisjazz.ISpotifyService;
import hu.berryweb.kisjazz.dto.AuthenticationTokenDto;
import hu.berryweb.kisjazz.restcaller.SpotifyRestCaller;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class SpotifyService implements ISpotifyService {

	SpotifyRestCaller restCaller;
	
	private final static String CLIENT_ID = "ec4832d04b8f42f4b4b663cbcdb0c870";
	private final static String CLIENT_SECRET = "a42b75154bf44a41849e364cd92ecb9f";
	
	private final Logger LOG = LoggerFactory.getLogger(SpotifyService.class);
	
	public SpotifyService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://accounts.spotify.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		restCaller = retrofit.create(SpotifyRestCaller.class);
	}
	
	@Override
	public Response<AuthenticationTokenDto> authorize() throws IOException {
		StringBuilder authorization = new StringBuilder();
		authorization.append("Basic ");
		authorization.append(Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()));
		LOG.debug(authorization.toString());
		return restCaller.authorize("client_credentials", authorization.toString()).execute();
	}

}
