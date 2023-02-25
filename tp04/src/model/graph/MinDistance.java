package model.graph;

public interface MinDistance {
	// initialization of the valuations of the vertexes different from root
	public void initValuations(Graph graph);

	// get the shortest path from root to pivot
	public double getShortestPath(Vertex pivot);

	// set the shortest path from root to pivot
	public void setShortestPath(Vertex pivot, double value);
}
