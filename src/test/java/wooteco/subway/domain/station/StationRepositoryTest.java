package wooteco.subway.domain.station;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;

@DataJpaTest
public class StationRepositoryTest {
	@Autowired
	private StationRepository stationRepository;

	@Test
	void saveStation() {
		String stationName = "강남역";
		stationRepository.save(new Station(stationName));

		assertThrows(DbActionExecutionException.class,
			() -> stationRepository.save(new Station(stationName)));
	}
}
