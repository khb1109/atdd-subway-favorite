package wooteco.subway.domain.line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wooteco.subway.domain.station.Station;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class LineStations {
	@OneToMany
	@JoinColumn(name = "line")
	private Set<LineStation> lineStations = new HashSet<>();

	public LineStations(Set<LineStation> lineStations) {
		this.lineStations = lineStations;
	}

	public static LineStations empty() {
		return new LineStations(new HashSet<>());
	}

	public void add(LineStation targetLineStation) {
		updatePreStationOfNextLineStation(targetLineStation.getPreStation(),
			targetLineStation.getStation());
		lineStations.add(targetLineStation);
	}

	private void remove(LineStation targetLineStation) {
		updatePreStationOfNextLineStation(targetLineStation.getStation(),
			targetLineStation.getPreStation());
		lineStations.remove(targetLineStation);
	}

	public void removeById(Long targetStationId) {
		extractByStationId(targetStationId)
			.ifPresent(this::remove);
	}

	public List<Long> getStationIds() {
		List<Long> result = new ArrayList<>();
		extractNext(null, result);
		//
		// return result;
		return null;
	}

	private void extractNext(Long preStationId, List<Long> ids) {
		// stations.stream()
		// 	.filter(it -> Objects.equals(it.getPreStation(), preStationId))
		// 	.findFirst()
		// 	.ifPresent(it -> {
		// 		Long nextStationId = it.getStation();
		// 		ids.add(nextStationId);
		// 		extractNext(nextStationId, ids);
		// 	});
	}

	private void updatePreStationOfNextLineStation(Station targetStation, Station newPreStation) {
		extractByPreStationId(targetStation)
			.ifPresent(it -> it.updatePreLineStation(newPreStation));
	}

	private Optional<LineStation> extractByStationId(Long stationId) {
		return lineStations.stream()
			.filter(it -> Objects.equals(it.getStation(), stationId))
			.findFirst();
	}

	private Optional<LineStation> extractByPreStationId(Station preStation) {
		return lineStations.stream()
			.filter(it -> Objects.equals(it.getPreStation(), preStation))
			.findFirst();
	}

}
