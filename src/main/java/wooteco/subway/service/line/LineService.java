package wooteco.subway.service.line;

import java.util.List;

import org.springframework.stereotype.Service;

import wooteco.subway.domain.line.Line;
import wooteco.subway.domain.line.LineRepository;
import wooteco.subway.domain.line.LineStation;
import wooteco.subway.domain.station.Station;
import wooteco.subway.domain.station.StationRepository;
import wooteco.subway.service.line.dto.LineDetailResponse;
import wooteco.subway.service.line.dto.LineRequest;
import wooteco.subway.service.line.dto.LineStationCreateRequest;
import wooteco.subway.service.line.dto.WholeSubwayResponse;

@Service
public class LineService {
    private final LineStationService lineStationService;
    private final StationRepository stationRepository;
    private final LineRepository lineRepository;

    public LineService(LineStationService lineStationService, StationRepository stationRepository,
        LineRepository lineRepository) {
        this.lineStationService = lineStationService;
        this.stationRepository = stationRepository;
        this.lineRepository = lineRepository;
    }

    public Line save(Line line) {
        return lineRepository.save(line);
    }

    public List<Line> findLines() {
        return lineRepository.findAll();
    }

    public void updateLine(Long id, LineRequest request) {
        Line persistLine = lineRepository.findById(id).orElseThrow(RuntimeException::new);
        persistLine.update(request.toLine());
        lineRepository.save(persistLine);
    }

    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    public void addLineStation(Long id, LineStationCreateRequest request) {
        Line line = lineRepository.findById(id).orElseThrow(RuntimeException::new);

        Station preStation = stationRepository.findById(request.getPreStationId())
            .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 station 입니다." + request.getPreStationId()));
        Station station = stationRepository.findById(request.getStationId())
            .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 station 입니다." + request.getPreStationId()));

        LineStation lineStation = new LineStation(preStation, station, request.getDistance(), request.getDuration());
        line.addLineStation(lineStation);
        lineRepository.save(line);
    }

    public void removeLineStation(Long lineId, Long stationId) {
        Line line = lineRepository.findById(lineId).orElseThrow(RuntimeException::new);
        line.removeLineStationById(stationId);

        lineRepository.save(line);
    }

    public LineDetailResponse retrieveLine(Long id) {
        return lineStationService.findLineWithStationsById(id);
    }

    public WholeSubwayResponse findLinesWithStations() {
        return lineStationService.findLinesWithStations();
    }
}
