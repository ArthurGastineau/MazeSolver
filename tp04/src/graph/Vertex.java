package graph;

import java.util.List;

public interface Vertex {
	// get the valuation of the smallest circuit
	public double getValuation();

	// set the valuation of the smallest circuit
	public void setValuation(double valuation);

	// get his predecessor s
	public List<Vertex> getPredecessors();

	// add a predecessor
	public void addPredecessor(Vertex from);

	// get his successors
	public List<Vertex> getSuccessors();

	// add a successor
	public void addPSuccessor(Vertex to);

	// get his id
	public int getId();

	// set his id
	public void setId(int id);
}
