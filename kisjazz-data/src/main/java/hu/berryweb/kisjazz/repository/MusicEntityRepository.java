package hu.berryweb.kisjazz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.berryweb.kisjazz.entity.MusicEntity;
import hu.berryweb.kisjazz.entity.UserEntity;

@Repository
public interface MusicEntityRepository extends JpaRepository<MusicEntity, Long> {

	List<MusicEntity> findByUserEntity(UserEntity userEntity);
}
