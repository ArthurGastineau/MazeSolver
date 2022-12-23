package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Implémentation de l'interface Distance qui permet de définir la distance entre deux sommets dans un graphe.
 */
public class DistanceImpl implements Distance {

    private Map<Vertex, Map<Vertex, Double>> distances;

    public DistanceImpl() {
        distances = new HashMap<>();
    }

    /**
     * Définit la distance entre les deux sommets donnés.
     * 
     * @param vertex1 le premier sommet
     * @param vertex2 le deuxième sommet
     * @param distance la distance entre les deux sommets
     */
    public void setDistance(Vertex vertex1, Vertex vertex2, double distance) {
        if (!distances.containsKey(vertex1)) {
            distances.put(vertex1, new HashMap<>());
        }
        distances.get(vertex1).put(vertex2, distance);
    }

    @Override
    public double getValuation(Vertex vertex1, Vertex vertex2) {
        if (!distances.containsKey(vertex1) || !distances.get(vertex1).containsKey(vertex2)) {
            return Double.POSITIVE_INFINITY;
        }
        return distances.get(vertex1).get(vertex2);
    }
}

