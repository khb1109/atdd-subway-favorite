package wooteco.subway.domain.line;

import java.util.List;

public class Lines {
	private List<Line> lines;

	public Lines(List<Line> lines) {
		this.lines = lines;
	}

	public List<Long> getStationIds() {
		// todo
		return null;

		// return lines.stream()
		//     .flatMap(it -> it.getStations().stream())
		//     .map(LineStation::getStationId)
		//     .collect(Collectors.toList());
	}

	public List<Line> getLines() {
		return lines;
	}
}
