package hu.berryweb.kisjazz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDto {

	private Long id;
	private String username;
	private String email;
	private String passwordHash;
}
