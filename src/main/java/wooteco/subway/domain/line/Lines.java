package wooteco.subway.domain.line;

import java.util.List;
import java.util.stream.Collectors;

public class Lines {
	private List<Line> lines;

	public Lines(List<Line> lines) {
		this.lines = lines;
	}

	public List<Long> getStationIds() {
		return lines.stream()
			.flatMap(it -> it.getLineStations().getLineStations().stream())
			.map(it -> it.getStation().getId())
			.collect(Collectors.toList());
	}

	public List<Line> getLines() {
		return lines;
	}
}
