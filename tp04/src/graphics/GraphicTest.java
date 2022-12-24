package graphics;

import java.util.List;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;

public class GraphicTest {

	public static void main(String[] args) {
		// creation of the maze
	    Maze myMaze = new Maze(10, 10);
		myMaze.initFromTextFile("data/" + "labyrinthe.maze");
		Vertex startVertex = myMaze.getStartVertex();
		Vertex endVertex = myMaze.getEndVertex();
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
