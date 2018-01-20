package hu.berryweb.kisjazz.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String passwordHash;
}
