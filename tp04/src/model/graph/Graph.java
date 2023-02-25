package model.graph;

import java.util.List;

public interface Graph {
	// get successors of a vertex
	public List<Vertex> getSuccessor(Vertex vertex);

	// return all the vertexes
	public List<Vertex> getAllVertexes();

	// get the distance between 2 vertexes
	public int getDistance(Vertex src, Vertex dst);
}