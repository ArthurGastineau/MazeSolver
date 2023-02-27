package model.graph;

/**
 * 
 * Interface for the minimum distance from a source vertex to all other vertices
 * in a graph.
 * 
 * The implementation of this interface can use any algorithm to calculate the
 * minimum distance, but it should provide methods to initialize the valuations
 * of all vertices in the graph except the root, to get the shortest path from
 * the root vertex to a given vertex, and to set the shortest path from the root
 * vertex to a given vertex.
 * 
 * @see Graph
 * @see Vertex
 *
 * @author Arthur Gastineau
 */

public interface MinDistance {

	/**
	 * Initializes the valuations of all vertices in the graph except for the root.
	 * 
	 * @param graph the graph whose vertices' valuations will be initialized
	 */

	public void initValuations(Graph graph);

	/**
	 * Gets the shortest path from the root vertex to the given pivot vertex.
	 * 
	 * @param pivot the vertex to get the shortest path to
	 * 
	 * @return the shortest path from the root vertex to the pivot vertex
	 */

	public double getShortestPath(Vertex pivot);

	/**
	 * Sets the shortest path from the root vertex to the given pivot vertex.
	 * 
	 * @param pivot the vertex to set the shortest path to
	 * 
	 * @param value the value of the shortest path from the root vertex to the pivot
	 *              vertex
	 */

	public void setShortestPath(Vertex pivot, double value);
}
