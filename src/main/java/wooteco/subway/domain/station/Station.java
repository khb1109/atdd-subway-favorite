package wooteco.subway.domain.station;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import wooteco.subway.domain.BaseEntity;

@Entity
@Getter
@Setter
public class Station extends BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	public Station() {
	}

	public Station(String name) {
		this.name = name;
	}

	public Station(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Station station = (Station)o;
		return Objects.equals(getId(), station.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
