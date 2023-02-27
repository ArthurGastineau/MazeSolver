package model.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * An implementation of the {@link ProcessedVertexes} interface using a
 * {@link HashSet} to store the set of processed vertices.
 * 
 * @see Vertex
 * @see ProcessedVertexes
 * @see HashSet
 *
 * @author Arthur Gastineau
 */

public class ProcessedVertexesImpl implements ProcessedVertexes {

	/**
	 * A set to store the processed vertices.
	 */

	private final Set<Vertex> vertexes;

	/**
	 * Constructs a new instance of {@code ProcessedVertexesImpl}.
	 */

	public ProcessedVertexesImpl() {
		this.vertexes = new HashSet<Vertex>();
	}

	/**
	 * {@inheritDoc}
	 */

	public void add(Vertex vertex) {
		vertexes.add(vertex);
	}

	/**
	 * {@inheritDoc}
	 */

	public boolean present(Vertex vertex) {
		return vertexes.contains(vertex);
	}
}
