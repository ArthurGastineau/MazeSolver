package graph;

public interface Vertex {
	// get the valuation of the smallest circuit 
	public double getValuation();
	// set the valuation of the smallest circuit 
	public void setValuation(double valuation);
	// get his predecessor 
	public Vertex getPredecessor ();
	// set his predecessor 
	public void setPredecessor (Vertex from);
	// get his id
	public int getId ();
	// set his id
	public void setId (int id);
}
