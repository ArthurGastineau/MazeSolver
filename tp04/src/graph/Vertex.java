package graph;

import java.util.List;

public interface Vertex {
	// get the valuation of the smallest circuit
	public double getValuation();

	// set the valuation of the smallest circuit
	public void setValuation(double valuation);

	// get his predecessor s
<<<<<<< HEAD
	public List<Vertex> getPredecessor ();
	// add a predecessor 
	public void addPredecessor (Vertex from);
=======
	public List<Vertex> getPredecessors();

	// add a predecessor
	public void addPredecessor(Vertex from);

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
	// get his successors
<<<<<<< HEAD
	public List<Vertex> getSuccessor ();
=======
	public List<Vertex> getSuccessors();

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
	// add a successor
<<<<<<< HEAD
	public void addPSuccessor (Vertex to);
	
	//
	public int getX();
	//
	public void setX(int x);
	//
	public int getY();
	//
	public void setY(int y);
	
=======
	public void addPSuccessor(Vertex to);

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
	// get his id
<<<<<<< HEAD
	public String getLabel ();
=======
	public int getId();

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
	// set his id
<<<<<<< HEAD
	public void setLabel (String label);
=======
	public void setId(int id);
>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
}
