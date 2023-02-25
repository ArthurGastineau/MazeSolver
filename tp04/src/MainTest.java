import java.util.List;

import model.graph.Dijkstra;
import model.graph.ShortestPaths;
import model.graph.Vertex;
import model.maze.Maze;

public class MainTest {

	public static void main(String[] args) {
		Maze myMaze = new Maze(10, 10);
		myMaze.initFromTextFile("data/labyrinthe.maze");
		//myMaze.saveToTextFile("data/labyrinthe2.maze");
		Vertex startVertex = myMaze.getStartVertex();
		Vertex endVertex = myMaze.getEndVertex();
		System.out.println("Calculating shortest path from " + startVertex.toString() + " to " + endVertex.toString());
		ShortestPaths shortestPaths = Dijkstra.dijkstra(myMaze, startVertex, endVertex);
		List<Vertex> path = shortestPaths.getShortestPath(endVertex);
		if (path.size() == 1) {
			System.out.println("There is no path possible");
		}
		else {
			System.out.println("The shortest path is in " + path.size() + " moves");
			/*for (Vertex vertex : path) {
			    System.out.println(vertex.toString());
			}*/
			myMaze.saveShortestPath("data/solution",path);
			myMaze.displayMaze("data/solution");
		}
	}
}
