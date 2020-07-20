package wooteco.subway.web;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wooteco.subway.domain.station.Station;
import wooteco.subway.service.station.StationService;
import wooteco.subway.service.station.dto.StationCreateRequest;
import wooteco.subway.service.station.dto.StationResponse;

@RestController
@RequestMapping("/api/stations")
public class StationController {
	private final StationService stationService;

	public StationController(StationService stationService) {
		this.stationService = stationService;
	}

	@GetMapping
	public ResponseEntity<List<StationResponse>> showStations() {
		return ResponseEntity.ok().body(StationResponse.listOf(stationService.findStations()));
	}

	@PostMapping
	public ResponseEntity<StationResponse> createStation(@RequestBody StationCreateRequest view) {
		Station persistStation = stationService.createStation(view.toStation());

		return ResponseEntity
			.created(URI.create("/stations/" + persistStation.getId()))
			.body(StationResponse.of(persistStation));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
		stationService.deleteStationById(id);
		return ResponseEntity.ok().build();
	}
}
