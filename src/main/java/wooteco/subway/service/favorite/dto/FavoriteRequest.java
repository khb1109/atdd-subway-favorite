package wooteco.subway.service.favorite.dto;

import wooteco.subway.domain.favorite.Favorite;

public class FavoriteRequest {
	private Long sourceStationId;
	private Long targetStationId;

	private FavoriteRequest() {
	}

	public FavoriteRequest(Long sourceStationId, Long targetStationId) {
		this.sourceStationId = sourceStationId;
		this.targetStationId = targetStationId;
	}

	static public FavoriteRequest of(Favorite favorite) {
		//todo
		return null;
		// return new FavoriteRequest(favorite.getSourceStation(), favorite.getTargetStation());
	}

	public Long getSourceStationId() {
		return sourceStationId;
	}

	public Long getTargetStationId() {
		return targetStationId;
	}

	public Favorite toEntity(Long memberId) {
		return null;
		//todo
		// return new Favorite(memberId, sourceStationId, targetStationId);
	}
}
