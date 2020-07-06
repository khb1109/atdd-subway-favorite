package wooteco.subway.domain.line;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wooteco.subway.domain.BaseEntity;
import wooteco.subway.domain.station.Station;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LineStation extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Station preStation;

	@OneToOne
	private Station station;

	private int distance;
	private int duration;

	public LineStation(Station preStation, Station station, int distance, int duration) {
		this.preStation = preStation;
		this.station = station;
		this.distance = distance;
		this.duration = duration;
	}

	public void updatePreLineStation(Station preStation) {
		this.preStation = preStation;
	}

	public boolean isLineStationOf(Long preStationId, Long stationId) {
		return this.preStation.equals(preStationId) && this.station.equals(stationId)
			|| this.preStation.equals(stationId) && this.station.equals(preStationId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LineStation that = (LineStation)o;
		return distance == that.distance &&
			duration == that.duration &&
			Objects.equals(preStation, that.preStation) &&
			Objects.equals(station, that.station);
	}

	@Override
	public int hashCode() {
		return Objects.hash(preStation, station, distance, duration);
	}
}
