package graph;

import java.util.List;

public interface Vertex {
	// get the valuation of the smallest circuit 
	public double getValuation();
	// set the valuation of the smallest circuit 
	public void setValuation(double valuation);
	// get his predecessor s
	public List<Vertex> getPredecessor ();
	// add a predecessor 
	public void addPredecessor (Vertex from);
	// get his successors
	public List<Vertex> getSuccessor ();
	// add a successor
	public void addPSuccessor (Vertex to);
	
	//
	public int getX();
	//
	public void setX(int x);
	//
	public int getY();
	//
	public void setY(int y);
	
	// get his id
	public String getLabel ();
	// set his id
	public void setLabel (String label);
}
