package hu.berryweb.kisjazz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.berryweb.kisjazz.entity.UserEntity;

@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
}
