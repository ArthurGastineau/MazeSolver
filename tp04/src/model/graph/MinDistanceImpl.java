package model.graph;

import java.util.HashMap;
import java.util.Map;

public class MinDistanceImpl implements MinDistance {

	private Map<Vertex, Integer> distanceMap;
	private Vertex root;

	public MinDistanceImpl(Vertex startVertex) {
		distanceMap = new HashMap<Vertex, Integer>();
		root = startVertex;
	}

	public void initValuations(Graph graph) {
		for (Vertex vertex : graph.getAllVertexes()) {
			if (!vertex.equals(root)) {
				distanceMap.put(vertex, Integer.MAX_VALUE);
			}
		}
	}

	public double getShortestPath(Vertex pivot) {
		return distanceMap.get(pivot);
	}

	public void setShortestPath(Vertex pivot, double value) {
		distanceMap.put(pivot, (int) value);
	}
}
