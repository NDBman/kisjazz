package hu.berryweb.kisjazz.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "MUSIC")
public class MusicEntity extends AbstractEntity {

	@Column(nullable = false)
	private String spotifyId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private UserEntity userEntity;
}
