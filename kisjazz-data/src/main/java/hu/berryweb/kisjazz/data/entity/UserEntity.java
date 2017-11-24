package hu.berryweb.kisjazz.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@Table(name = "user_entity")
public class UserEntity {

	private Long id;
	private String username;
	private String email;
	private String passwordHash;

}
