package hu.berryweb.kisjazz.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationTokenDto {

	@SerializedName("access_token")
	@Expose
	private String accessToken;

	@SerializedName("token_type")
	@Expose
	private String tokenType;

	@SerializedName("expires_in")
	@Expose
	private Integer expiresIn;

	@SerializedName("scope")
	@Expose
	private String scope;
}
