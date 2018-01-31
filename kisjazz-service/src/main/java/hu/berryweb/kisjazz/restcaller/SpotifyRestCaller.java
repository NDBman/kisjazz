package hu.berryweb.kisjazz.restcaller;

import hu.berryweb.kisjazz.http.AuthenticationToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotifyRestCaller {

	@FormUrlEncoded
	@POST("/api/token")
	Call<AuthenticationToken> authorize(@Field("grant_type") String grantType, @Header("Authorization") String authorization);

}
