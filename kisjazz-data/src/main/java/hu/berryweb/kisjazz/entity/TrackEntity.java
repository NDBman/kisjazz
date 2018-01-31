package hu.berryweb.kisjazz.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TRACK", uniqueConstraints = {@UniqueConstraint(name = "UC", columnNames = "SPOTIFY_ID")})
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPOTIFY_ID",nullable = false, unique = true)
    private String spotifyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}
