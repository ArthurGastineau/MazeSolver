package graph;

import java.util.HashMap;
import java.util.Map;

public class MinDistanceImpl implements MinDistance {

    private Map<Vertex, Integer> distanceMap;

    public MinDistanceImpl() {
        this.distanceMap = new HashMap<Vertex, Integer>();
    }

    @Override
    public void initValuations(Graph graph, Vertex root) {
        for (Vertex vertex : graph.getAllVertexes()) {
            if (!vertex.equals(root)) {
                distanceMap.put(vertex, Integer.MAX_VALUE);
            }
        }
    }

    @Override
    public double getShortestPath(Vertex root, Vertex pivot) {
        return distanceMap.get(pivot);
    }

    @Override
    public void setShortestPath(Vertex root, Vertex pivot, double value) {
        distanceMap.put(pivot, (int) value);
    }
}

