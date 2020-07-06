package wooteco.subway.domain.favorite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	// @Query("SELECT * FROM favorite WHERE member_id =:id")
	List<Favorite> findByMemberId(@Param("id") Long id);
}
