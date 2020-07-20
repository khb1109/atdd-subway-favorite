package wooteco.subway.domain.line;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wooteco.subway.domain.BaseEntity;
import wooteco.subway.domain.station.Station;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class LineStation extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station preStation;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
        return this.preStation.getId().equals(preStationId) && this.station.getId().equals(stationId)
            || this.preStation.getId().equals(stationId) && this.station.getId().equals(preStationId);
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
