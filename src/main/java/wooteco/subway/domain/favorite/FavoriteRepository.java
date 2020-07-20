package wooteco.subway.domain.favorite;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByMemberId(@Param("id") Long id);

    Optional<Favorite> findBySourceStationIdAndTargetStationId(Long sourceStationId, Long targetStationId);
}
