package wooteco.subway.domain.station;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
	List<Station> findAllByIdIn(List<Long> ids);

	Optional<Station> findByName(String name);
}
