package graph;

public class Dijkstra {
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex,
			ProcessedVertexes processedVertexes, MinDistance minDistance, Distance distance,
			ShortestPaths shortestPaths) {

		processedVertexes.add(startVertex);
		Vertex pivotVertex = startVertex;
		minDistance.setShortestPath(startVertex, pivotVertex, 0);

		for (Vertex vertex : graph.getAllVertexes()) {
			if (vertex.getLabel() != startVertex.getLabel()) {
				minDistance.setShortestPath(startVertex, vertex, Double.POSITIVE_INFINITY);
			}
		}

		while (processedVertexes.present(endVertex)) {
			for (Vertex succVertex : graph.getSuccessor(pivotVertex)) {
				if (processedVertexes.present(succVertex) == false) {
					if ((minDistance.getShortestPath(startVertex, pivotVertex)
							+ distance.getValuation(pivotVertex, succVertex)) < (minDistance
									.getShortestPath(startVertex, succVertex))) {
						minDistance.setShortestPath(startVertex, succVertex,
								(minDistance.getShortestPath(startVertex, pivotVertex)
										+ distance.getValuation(pivotVertex, succVertex)));
						shortestPaths.setPrevious(pivotVertex, succVertex);
					}

				}
			}

			double temp = Double.POSITIVE_INFINITY;
			for (Vertex nextVertex : graph.getAllVertexes()) {
				if (processedVertexes.present(nextVertex) == false
						&& (minDistance.getShortestPath(startVertex, nextVertex) < temp)) {
					temp = minDistance.getShortestPath(startVertex, nextVertex);
					pivotVertex = nextVertex;
				}
			}
			processedVertexes.add(pivotVertex);
		}
		return shortestPaths;
	}
	
	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
	    ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
	    MinDistance minDistance = new MinDistanceImpl();
	    Distance distance = new DistanceImpl();
	    ShortestPaths shortestPaths = new ShortestPathsImpl();

	    return dijkstra(graph, startVertex, endVertex, processedVertexes, minDistance, distance, shortestPaths);
	}
}
