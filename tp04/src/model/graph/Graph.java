package model.graph;

import java.util.List;

/**
 * 
 * A graph interface that defines operations on a graph.
 *
 * @author Arthur Gastineau
 */

public interface Graph {
	/**
	 * Gets the successor vertices of a given vertex in the graph.
	 * 
	 * @param vertex the vertex to get the successors of
	 * @return a list of the successor vertices of the given vertex
	 */

	public List<Vertex> getSuccessor(Vertex vertex);

	/**
	 * Returns a list of all vertices in the graph.
	 * 
	 * @return a list of all vertices in the graph
	 */

	public List<Vertex> getAllVertexes();

	/**
	 * Gets the distance between two vertices in the graph.
	 * 
	 * @param src the source vertex
	 * @param dst the destination vertex
	 * @return the distance between the source and destination vertices
	 */

	public int getDistance(Vertex src, Vertex dst);
}
