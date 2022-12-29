package graphics;

import java.util.List;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;

public class GraphicTest {

	public static void main(String[] args) {
		// creation of the maze
	    Maze myMaze = new Maze();
	    int [] vals = myMaze.fromFileGetMazeSize("data/labyrinthe4.maze");
	    myMaze.setSize(vals[0], vals[1]);
	    System.out.println(vals[0] + ":"  + vals[1]);
		myMaze.initFromTextFile("data/labyrinthe4.maze");
		myMaze.displayMaze("data/labyrinthe4.maze");
		Vertex startVertex = myMaze.getStartVertex();
		Vertex endVertex = myMaze.getEndVertex();
		System.out.println("Calculating shotest path from" + startVertex.toString() + " to " + endVertex.toString());
		ShortestPaths shortestPaths = Dijkstra.dijkstra(myMaze, startVertex, endVertex);
		List<Vertex> path = shortestPaths.getShortestPath(endVertex);
		myMaze.saveShortestPath("data/solution",path);
		
		// création de notre fenêtre principale
	    MainFrame mainFrame = new MainFrame(myMaze);
	    mainFrame.addMouseMotionListener(mainFrame);
	    
	    // affichage de la fenêtre
	    mainFrame.setVisible(true);
	}

}
