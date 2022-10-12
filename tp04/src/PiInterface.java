
public interface PiInterface {
	// initialisation of the valuations
	public void initValuations(Graph graph);
	// get the arc with the lowest valuation of an arc going to 'to'
	public int[] getLowestArc (Graph graph, Vertex to);
	// get the shortestpath from root to pivot
	public double getShortestPath (Vertex root, Vertex pivot);
	// set the shortestpath from root to pivot
	public void setShortestPath (Vertex root, Vertex pivot, int value);
}
