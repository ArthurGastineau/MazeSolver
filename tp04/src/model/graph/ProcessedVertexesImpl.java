package model.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public class ProcessedVertexesImpl implements ProcessedVertexes {

	private final Set<Vertex> vertexes;

	public ProcessedVertexesImpl() {
		this.vertexes = new HashSet<Vertex>();
	}

	public void add(Vertex vertex) {
		vertexes.add(vertex);
	}

	public boolean present(Vertex vertex) {
		return vertexes.contains(vertex);
	}
}
