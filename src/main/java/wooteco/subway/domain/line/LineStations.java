package wooteco.subway.domain.line;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
		List<Station> result = new ArrayList<>();
		extractNext(null, result);

		return result.stream()
			.map(Station::getId)
			.collect(Collectors.toList());
	}

	private void extractNext(Station preStation, List<Station> stations) {
		lineStations.stream()
			.filter(it -> Objects.equals(it.getPreStation(), preStation))
			.findFirst()
			.ifPresent(it -> {
				Station nextStation = it.getStation();
				stations.add(nextStation);
				extractNext(nextStation, stations);
			});
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
