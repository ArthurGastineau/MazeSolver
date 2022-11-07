package graph;

public interface MinDistance {
	// initialization of the valuations of the vertexes different from root
	public void initValuations(Graph graph, Vertex root);
	// get the shortest path from root to pivot
	public double getShortestPath (Vertex root, Vertex pivot);
	// set the shortest path from root to pivot
	public void setShortestPath (Vertex root, Vertex pivot, int value);
}
