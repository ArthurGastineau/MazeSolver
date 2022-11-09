package graph;

import java.util.Vector;

public interface Vertex {
	// get the valuation of the smallest circuit 
	public double getValuation();
	// set the valuation of the smallest circuit 
	public void setValuation(double valuation);
	// get his predecessor s
	public Vector<Vertex> getPredecessor ();
	// add a predecessor 
	public void addPredecessor (Vertex from);
	// get his successors
	public Vector<Vertex> getSuccessor ();
	// add a successor
	public void addPSuccessor (Vertex to);
	// get his id
	public int getId ();
	// set his id
	public void setId (int id);
}
