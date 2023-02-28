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
 * @see MinDistance
 * @see Vertex
 * @see Graph
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
		distanceMap = new HashMap<>();
		root = startVertex;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void initValuations(Graph graph) {
		for (Vertex vertex : graph.getAllVertexes()) {
			if (!vertex.equals(root)) {
				distanceMap.put(vertex, Integer.MAX_VALUE);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public double getShortestPath(Vertex pivot) {
		return distanceMap.get(pivot);
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void setShortestPath(Vertex pivot, double value) {
		distanceMap.put(pivot, (int) value);
	}
}
