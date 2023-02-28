package model.graph;

/**
 *
 * A class representing the distance between two vertexes in a graph.
 *
 * This class assumes that the distance between any two vertexes in the graph is
 * equal to 1.
 *
 * @see Vertex
 *
 * @author Arthur Gastineau
 */

public class Distance {

	/**
	 * Returns the valuation of the arc between two vertexes.
	 *
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 *
	 * @return the valuation of the arc between the two vertexes (which is always
	 *         equal to 1 in this implementation)
	 */

	public double getValuation(Vertex vertex1, Vertex vertex2) {
		return 1;
	}
}
