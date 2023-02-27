package model.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Implementation of the {@link ShortestPaths} interface for finding the
 * shortest path between two vertices in a graph using Dijkstra's algorithm.
 *
 * @author Arthur Gastineau
 */

public class ShortestPathsImpl implements ShortestPaths {

	private final Map<Vertex, Vertex> predecessors;

	/**
	 * Constructs a new instance of {@code ShortestPathsImpl}.
	 */

	public ShortestPathsImpl() {
		this.predecessors = new HashMap<Vertex, Vertex>();
	}

	/**
	 * {@inheritDoc}
	 */

	public List<Vertex> getShortestPath(Vertex endVertex) {
		List<Vertex> path = new ArrayList<>();
		Vertex current = endVertex;
		while (current != null) {
			path.add(current);
			current = predecessors.get(current);
		}
		Collections.reverse(path);
		return path;
	}

	/**
	 * {@inheritDoc}
	 */

	public void setPrevious(Vertex son, Vertex father) {
		predecessors.put(son, father);
	}

	/**
	 * {@inheritDoc}
	 */

	public Vertex getPrevious(Vertex father) {
		return predecessors.get(father);
	}
}
