package wooteco.subway.domain.station;

import java.util.List;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Stations {
	private List<Station> stations;

	public Stations(List<Station> stations) {
		this.stations = stations;
	}

	public Station extractStationById(Long stationId) {
		return stations.stream()
			.filter(it -> it.getId().equals(stationId))
			.findFirst()
			.orElseThrow(RuntimeException::new);
	}
}
