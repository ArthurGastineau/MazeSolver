package graph;

public interface Graph {
	// get a vertex from the Graph from his id
	public Vertex getVertex (int id);
	// Initialization of the valuations (to infinity) except for the root
	public void init();
}
