package model.graph;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public interface ProcessedVertexes {
	// add a vertex in the set
	public void add(Vertex vertex);

	// return if a vertex is present in the set
	public boolean present(Vertex vertex);
}
