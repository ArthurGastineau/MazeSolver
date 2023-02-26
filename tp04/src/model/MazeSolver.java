/**
 * 
 */
package model;

import java.util.List;

import controller.MazeController;
import model.graph.Dijkstra;
import model.graph.ShortestPaths;
import model.graph.Vertex;
import model.maze.Maze;

/**
 * 
 * The maze solver that draws the solution path of the maze from the start box
 * to the end box
 * 
 * @author Arthur Gastineau
 */
public class MazeSolver {
	private final Maze maze;
	private final MazeController mazeController;

	public MazeSolver(Maze maze, MazeController mazeController) {
		this.maze = maze;
		this.mazeController = mazeController;
	}

	public void initMazeSolver() {
		if (maze.hasArrivalBox() && maze.hasDepartureBox()) {
			// maze.displayMaze();
			Vertex startVertex = maze.getStartVertex();
			Vertex endVertex = maze.getEndVertex();
			ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, startVertex, endVertex);
			List<Vertex> path = shortestPaths.getShortestPath(endVertex);
			maze.markShortestPath(path);
			// maze.displayMaze();
		} else {
			maze.deleteShortestPath();
		}
	}
}
