package model.graph;

import java.util.ArrayList;

/**
 * 
 *
 *		@author Arthur Gastineau 
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathsImpl implements ShortestPaths {

	private final Map<Vertex, Vertex> predecessors;

	public ShortestPathsImpl() {
		this.predecessors = new HashMap<Vertex, Vertex>();
	}

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

	public void setPrevious(Vertex son, Vertex father) {
		predecessors.put(son, father);
	}

	public Vertex getPrevious(Vertex father) {
		return predecessors.get(father);
	}
}
