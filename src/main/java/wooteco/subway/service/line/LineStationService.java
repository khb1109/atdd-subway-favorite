package wooteco.subway.service.line;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wooteco.subway.domain.line.Line;
import wooteco.subway.domain.line.LineRepository;
import wooteco.subway.domain.line.Lines;
import wooteco.subway.domain.station.Station;
import wooteco.subway.domain.station.StationRepository;
import wooteco.subway.service.line.dto.LineDetailResponse;
import wooteco.subway.service.line.dto.WholeSubwayResponse;

@Service
@RequiredArgsConstructor
public class LineStationService {
    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public LineDetailResponse findLineWithStationsById(Long lineId) {
        Line line = lineRepository.findById(lineId).orElseThrow(RuntimeException::new);
        List<Long> lineStationIds = line.findOrderedLineStationIds(); // 순차적이어야함.

        List<Station> stations = stationRepository.findAllById(lineStationIds);

        return LineDetailResponse.of(line, mapStations(lineStationIds, stations));
    }

    public WholeSubwayResponse findLinesWithStations() {
        Lines lines = new Lines(lineRepository.findAll());
        List<Station> stations = stationRepository.findAllByIdIn(lines.getStationIds());

        List<LineDetailResponse> lineDetailResponses = lines.getLines().stream()
            .map(it -> LineDetailResponse.of(it, mapStations(it.findOrderedLineStationIds(), stations)))
            .collect(Collectors.toList());

        return WholeSubwayResponse.of(lineDetailResponses);
    }

    private List<Station> mapStations(List<Long> stationsIds, List<Station> stations) {
        return stations.stream()
            .filter(it -> stationsIds.contains(it.getId()))
            .collect(Collectors.toList());
    }

    @Transactional
    public void deleteLineStationByStationId(Station station) {
        List<Line> lines = lineRepository.findAll();
        lines.forEach(it -> it.removeLineStationById(station.getId()));

        lineRepository.saveAll(lines);
    }
}
