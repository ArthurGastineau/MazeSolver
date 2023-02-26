package model.graph;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * An implementation of the {@link MinDistance} interface using a
 * {@link HashMap} to store the shortest distance from the root vertex to each
 * vertex in the graph.
 * 
 * The shortest distance from the root vertex to a vertex that has not been
 * visited yet is set to {@code Integer.MAX_VALUE} at initialization.
 *
 * @author Arthur Gastineau
 */

public class MinDistanceImpl implements MinDistance {

	/**
	 * A map to store the shortest distance from the root vertex to each vertex in
	 * the graph.
	 */

	private final Map<Vertex, Integer> distanceMap;

	/**
	 * The root vertex.
	 */

	private final Vertex root;

	/**
	 * Constructs a new instance of {@code MinDistanceImpl} with the given root
	 * vertex.
	 * 
	 * @param startVertex the root vertex
	 */

	public MinDistanceImpl(Vertex startVertex) {
		distanceMap = new HashMap<Vertex, Integer>();
		root = startVertex;
	}

	/**
	 * Initializes the valuations of all the vertices in the graph except the root
	 * vertex to {@code Integer.MAX_VALUE}.
	 * 
	 * @param graph the graph
	 */

	public void initValuations(Graph graph) {
		for (Vertex vertex : graph.getAllVertexes()) {
			if (!vertex.equals(root)) {
				distanceMap.put(vertex, Integer.MAX_VALUE);
			}
		}
	}

	/**
	 * Returns the shortest path from the root vertex to the given pivot vertex.
	 * 
	 * @param pivot the pivot vertex
	 * @return the shortest path from the root vertex to the pivot vertex
	 */

	public double getShortestPath(Vertex pivot) {
		return distanceMap.get(pivot);
	}

	/**
	 * Sets the shortest path from the root vertex to the given pivot vertex.
	 * 
	 * @param pivot the pivot vertex
	 * @param value the shortest path from the root vertex to the pivot vertex
	 */

	public void setShortestPath(Vertex pivot, double value) {
		distanceMap.put(pivot, (int) value);
	}
}
