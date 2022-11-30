package graph;


import java.util.List;

import java.util.ArrayList;


public interface Graph {
	// Initialization of the valuations (to infinity) except for the root
	public void init();
	// get successors of a vertex
	public List<Vertex> getSuccessor (Vertex vertex);
	//return all the vertexes
	public List<Vertex> getAllVertexes() ;
	//get the distance between 2 vertexes
	public int getDistance(Vertex src,Vertex dst) ;
}
