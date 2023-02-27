package model.graph;

/**
 * 
 * Interface for a set of processed vertices in a graph.
 * 
 * Implementations of this interface should provide methods for adding a vertex
 * to the set and checking if a vertex is already present in the set.
 * 
 * Processing of a vertex typically means that the vertex has been visited or
 * processed in some way during a graph traversal algorithm.
 *
 * @see Vertex
 * 
 * @author Arthur Gastineau
 */

public interface ProcessedVertexes {

	/**
	 * Adds a vertex to the set of processed vertices.
	 * 
	 * @param vertex the vertex to add
	 */

	public void add(Vertex vertex);

	/**
	 * Returns whether the given vertex is present in the set of processed vertices.
	 * 
	 * @param vertex the vertex to check
	 * 
	 * @return true if the vertex is present in the set, false otherwise
	 */

	public boolean present(Vertex vertex);
}
