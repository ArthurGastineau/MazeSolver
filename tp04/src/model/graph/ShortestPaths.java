package model.graph;

import java.util.List;

/**
 *
 * An interface for finding the shortest path between two vertices in a graph.
 * Implementations of this interface should use a shortest path algorithm, such
 * as Dijkstra's algorithm or Bellman-Ford algorithm, to calculate the shortest
 * path between two vertices.
 *
 * Implementations should store the previous vertex with the lowest valuation of
 * its arc going to each vertex in the graph. This information is used to
 * construct the shortest path between two vertices.
 *
 * @see Vertex
 *
 * @author Arthur Gastineau
 */

public interface ShortestPaths {

	/**
	 * Sets the previous vertex of a given son vertex that has the lowest valuation
	 * of its arc going to the given father vertex.
	 *
	 * @param son    the son vertex whose previous vertex to set
	 * @param father the father vertex whose arc valuation is being compared
	 */

	public void setPrevious(Vertex son, Vertex father);

	/**
	 * Gets the previous vertex of the given father vertex that has the lowest
	 * valuation of its arc going to any of the father's son vertices.
	 *
	 * @param father the father vertex whose previous vertex to get
	 *
	 * @return the previous vertex of the father vertex with the lowest arc
	 *         valuation
	 */

	public Vertex getPrevious(Vertex father);

	/**
	 * Gets the shortest path from a start vertex to an end vertex in the graph.
	 *
	 * @param endVertex the end vertex of the path to find
	 *
	 * @return a list of vertices representing the shortest path from the start
	 *         vertex to the end vertex
	 */

	public List<Vertex> getShortestPath(Vertex endVertex);
}
