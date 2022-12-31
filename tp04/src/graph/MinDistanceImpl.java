package graph;

import java.util.HashMap;
import java.util.Map;

public class MinDistanceImpl implements MinDistance {

    private Map<Vertex, Integer> distanceMap;
    private Vertex root;

    public MinDistanceImpl(Vertex startVertex) {
        distanceMap = new HashMap<Vertex, Integer>();
        root = startVertex;
    }

    @Override
    public void initValuations(Graph graph) {
        for (Vertex vertex : graph.getAllVertexes()) {
            if (!vertex.equals(root)) {
                distanceMap.put(vertex, Integer.MAX_VALUE);
            }
        }
    }

    @Override
    public double getShortestPath(Vertex pivot) {
        return distanceMap.get(pivot);
    }

    @Override
    public void setShortestPath(Vertex pivot, double value) {
        distanceMap.put(pivot, (int) value);
    }
}

