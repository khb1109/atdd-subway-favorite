package wooteco.subway.service.station;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import wooteco.subway.domain.station.Station;
import wooteco.subway.domain.station.StationRepository;
import wooteco.subway.service.favorite.FavoriteService;
import wooteco.subway.service.line.LineStationService;

@Service
@RequiredArgsConstructor
public class StationService {
    private final LineStationService lineStationService;
    private final StationRepository stationRepository;
    private final FavoriteService favoriteService;

    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public List<Station> findStations() {
        return stationRepository.findAll();
    }

    @Transactional
    public void deleteStationById(Long id) {
        // Station station = stationRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        // lineStationService.deleteLineStationByStationId(station);
        // favoriteService.deleteByStation(station);

        stationRepository.deleteById(id);
    }
}
