package wooteco.subway.domain.favorite;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Getter;
import lombok.Setter;
import wooteco.subway.domain.BaseEntity;
import wooteco.subway.domain.member.Member;
import wooteco.subway.domain.station.Station;
import wooteco.subway.service.favorite.dto.FavoriteRequest;

@Entity
@Getter
@Setter
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station sourceStation;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Station targetStation;

    public Favorite() {
    }

    public Favorite(Long id, Member member) {
        this.id = id;
        this.member = member;
    }

    public Favorite(Member member, Station sourceStation, Station targetStation) {
        this.member = member;
        this.sourceStation = sourceStation;
        this.targetStation = targetStation;
    }

    public Favorite(Long id, Member member, Station sourceStation, Station targetStation) {
        this.id = id;
        this.member = member;
        this.sourceStation = sourceStation;
        this.targetStation = targetStation;
    }

    public boolean isNotSameMember(Member member) {
        return !Objects.equals(this.member.getId(), member.getId());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Favorite favorite = (Favorite)o;
        return Objects.equals(sourceStation, favorite.sourceStation) &&
            Objects.equals(targetStation, favorite.targetStation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceStation, targetStation);
    }

    public boolean isSameValue(FavoriteRequest favoriteRequest) {
        return Objects.equals(sourceStation, favoriteRequest.getSourceStationId())
            && Objects.equals(targetStation, favoriteRequest.getTargetStationId());
    }

    @Override
    public String toString() {
        return "Favorite{" +
            "id=" + id +
            ", member=" + member +
            ", sourceStation=" + sourceStation +
            ", targetStation=" + targetStation +
            '}';
    }
}
