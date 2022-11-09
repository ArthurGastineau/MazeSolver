package graph;

import java.util.ArrayList;

public interface Graph {
	// get a vertex from the Graph from his id
	public Vertex getVertex(int id);

	// get all the vertex from the graph
	public ArrayList<Vertex> getAllVertexes();

	// Initialization of the valuations (to infinity) except for the root
	public void init();
}
