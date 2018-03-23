package hu.berryweb.kisjazz.repository;

import hu.berryweb.kisjazz.entity.PlayItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Nandi on 2018. 03. 12..
 */
@Repository
public interface IPlayItemRepository extends JpaRepository<PlayItemEntity, Long> {

    List<PlayItemEntity> findAllByOrderByOrderNumber();
}
