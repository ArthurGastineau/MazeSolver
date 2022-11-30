package graph;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git

public interface Graph {
<<<<<<< HEAD
=======
	// get a vertex from the Graph from his id
	public Vertex getVertex(int id);

	// get all the vertex from the graph
	public ArrayList<Vertex> getAllVertexes();

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
	// Initialization of the valuations (to infinity) except for the root
	public void init();
	// get successors of a vertex
	public List<Vertex> getSuccessor (Vertex vertex);
	//return all the vertexes
	public List<Vertex> getAllVertexes() ;
	//get the distance between 2 vertexes
	public int getDistance(Vertex src,Vertex dst) ;
}
