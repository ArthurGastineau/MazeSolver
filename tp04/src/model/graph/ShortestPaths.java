package model.graph;

import java.util.List;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public interface ShortestPaths {
	// set the vertex son having the lowest valuation of his arc going to father
	public void setPrevious(Vertex son, Vertex father);

	// get the vertex son having the lowest valuation of his arc going to father
	public Vertex getPrevious(Vertex father);

	// get the shortest path from the start vertex to the end vertex
	public List<Vertex> getShortestPath(Vertex endVertex);
}
