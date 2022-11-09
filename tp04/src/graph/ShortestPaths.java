package graph;

public interface ShortestPaths {
	// set the vertex son having the lowest valuation of his arc going to father
	public void setPrevious(Vertex son, Vertex father);

	// get the vertex son having the lowest valuation of his arc going to father
	public Vertex getPrevious(Vertex father);
}
