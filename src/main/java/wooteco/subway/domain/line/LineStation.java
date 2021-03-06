package wooteco.subway.domain.line;

import java.util.Objects;

import wooteco.subway.domain.BaseEntity;

public class LineStation extends BaseEntity {
	private Long preStationId;
	private Long stationId;
	private int distance;
	private int duration;

	public LineStation(Long preStationId, Long stationId, int distance, int duration) {
		this.preStationId = preStationId;
		this.stationId = stationId;
		this.distance = distance;
		this.duration = duration;
	}

	public void updatePreLineStation(Long preStationId) {
		this.preStationId = preStationId;
	}

	public boolean isLineStationOf(Long preStationId, Long stationId) {
		return this.preStationId.equals(preStationId) && this.stationId.equals(stationId)
			|| this.preStationId.equals(stationId) && this.stationId.equals(preStationId);
	}

	public Long getPreStationId() {
		return preStationId;
	}

	public Long getStationId() {
		return stationId;
	}

	public int getDistance() {
		return distance;
	}

	public int getDuration() {
		return duration;
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
			Objects.equals(preStationId, that.preStationId) &&
			Objects.equals(stationId, that.stationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(preStationId, stationId, distance, duration);
	}
}
