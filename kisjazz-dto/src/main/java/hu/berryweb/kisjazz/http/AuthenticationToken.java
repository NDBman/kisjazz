package hu.berryweb.kisjazz.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationToken {

	@Expose
	@SerializedName("access_token")
	private String accessToken;

	@Expose
	@SerializedName("token_type")
	private String tokenType;

	@Expose
	@SerializedName("expires_in")
	private Integer expiresIn;

	@Expose
	@SerializedName("refresh_token")
	private String refreshToken;
}
