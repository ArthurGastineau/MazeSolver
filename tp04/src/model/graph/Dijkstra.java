package model.graph;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public class Dijkstra {
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex,
			ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance,
			ShortestPaths shortestPaths) {

		processedVertexes.add(startVertex);
		Vertex pivotVertex = startVertex;
		minDistance.setShortestPath(pivotVertex, 0);

		// for all vertexes except the start one we have minDistance(vertex) = infinity
		minDistance.initValuations(graph);
		while (!processedVertexes.present(endVertex)) {
			for (Vertex succVertex : graph.getSuccessor(pivotVertex)) {
				if (!processedVertexes.present(succVertex)) {
					if ((minDistance.getShortestPath(pivotVertex)
							+ distance.getValuation(pivotVertex, succVertex)) < (minDistance
									.getShortestPath(succVertex))) {
						minDistance.setShortestPath(succVertex, (minDistance.getShortestPath(pivotVertex)
								+ distance.getValuation(pivotVertex, succVertex)));
						shortestPaths.setPrevious(succVertex, pivotVertex);
					}

				}
			}

			double temp = Double.POSITIVE_INFINITY;
			for (Vertex nextVertex : graph.getAllVertexes()) {
				if (!processedVertexes.present(nextVertex) && (minDistance.getShortestPath(nextVertex) < temp)) {
					temp = minDistance.getShortestPath(nextVertex);
					pivotVertex = nextVertex;
				}
			}
			processedVertexes.add(pivotVertex);
		}
		return shortestPaths;
	}

	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
		ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
		MinDistance minDistance = new MinDistanceImpl(startVertex);
		Distance distance = new Distance();
		ShortestPaths shortestPaths = new ShortestPathsImpl();

		return dijkstra(graph, startVertex, endVertex, processedVertexes, minDistance, distance, shortestPaths);
	}
}
