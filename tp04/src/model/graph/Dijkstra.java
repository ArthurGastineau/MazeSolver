package model.graph;

/**
 * 
 * Dijkstra's algorithm implementation for finding the shortest path in a graph.
 *
 * @author Arthur Gastineau
 */

public class Dijkstra {

	/**
	 * Finds the shortest path between two vertices in a graph using Dijkstra's
	 * algorithm.
	 * 
	 * @param graph             the graph in which to search for the shortest path
	 * @param startVertex       the vertex from which to start the search
	 * @param endVertex         the vertex to which the shortest path should lead
	 * @param processedVertexes a set of already processed vertexes
	 * @param minDistance       the minimum distance from the start vertex to each
	 *                          vertex
	 * @param distance          the distance between each pair of vertices in the
	 *                          graph
	 * @param shortestPaths     the current set of shortest paths
	 * @return the shortest paths from the start vertex to all other vertices in the
	 *         graph
	 */

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

	/**
	 * Finds the shortest path between two vertices in a graph using Dijkstra's
	 * algorithm.
	 * 
	 * @param graph       the graph in which to search for the shortest path
	 * @param startVertex the vertex from which to start the search
	 * @param endVertex   the vertex to which the shortest path should lead
	 * @return the shortest paths from the start vertex to all other vertices in the
	 *         graph
	 */

	public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex) {
		ProcessedVertexes processedVertexes = new ProcessedVertexesImpl();
		MinDistance minDistance = new MinDistanceImpl(startVertex);
		Distance distance = new Distance();
		ShortestPaths shortestPaths = new ShortestPathsImpl();

		return dijkstra(graph, startVertex, endVertex, processedVertexes, minDistance, distance, shortestPaths);
	}
}
