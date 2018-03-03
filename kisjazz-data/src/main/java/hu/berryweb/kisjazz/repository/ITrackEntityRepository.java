package hu.berryweb.kisjazz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.berryweb.kisjazz.entity.TrackEntity;
import hu.berryweb.kisjazz.entity.UserEntity;

@Repository
public interface ITrackEntityRepository extends JpaRepository<TrackEntity, Long> {

	List<TrackEntity> findByUserEntity(UserEntity userEntity);

	TrackEntity findBySpotifyId(String spotifyId);
}
