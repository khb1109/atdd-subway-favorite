package wooteco.subway.service.path;

import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.springframework.stereotype.Service;

import wooteco.subway.domain.line.Line;
import wooteco.subway.domain.path.PathType;

@Service
public class GraphService {
	public List<Long> findPath(List<Line> lines, Long source, Long target, PathType type) {
		WeightedMultigraph<Long, DefaultWeightedEdge> graph
			= new WeightedMultigraph(DefaultWeightedEdge.class);

		//todo
		//
		// lines.stream()
		// 	.flatMap(it -> it.getStationIds().stream())
		// 	.forEach(graph::addVertex);
		//
		// lines.stream()
		// 	.flatMap(it -> it.getStations().stream())
		// 	.filter(it -> Objects.nonNull(it.getPreStationId()))
		// 	.forEach(
		// 		it -> graph.setEdgeWeight(graph.addEdge(it.getPreStationId(), it.getStationId()),
		// 			type.findWeightOf(it)));

		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
		return dijkstraShortestPath.getPath(source, target).getVertexList();
	}
}
