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
 * @see Maze
 * @see MazeController
 * @see Vertex
 * @see ShortestPaths
 * @see Dijkstra
 * 
 * @author Arthur Gastineau
 */
public class MazeSolver {

	private final Maze maze;
	private final MazeController mazeController;

	/**
	 * Constructs a {@link MazeSolver} object with the specified maze and maze
	 * controller.
	 * 
	 * @param maze           the maze to be solved.
	 * @param mazeController the maze controller that controls the maze.
	 */

	public MazeSolver(Maze maze, MazeController mazeController) {
		this.maze = maze;
		this.mazeController = mazeController;
	}

	/**
	 * Initializes the maze solver to solve the maze, and draws the solution path on
	 * the maze panel.
	 * 
	 */

	public void initMazeSolver() {
		if (maze.hasArrivalBox() && maze.hasDepartureBox()) {
			Vertex startVertex = maze.getStartVertex();
			Vertex endVertex = maze.getEndVertex();
			ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, startVertex, endVertex);
			List<Vertex> path = shortestPaths.getShortestPath(endVertex);
			maze.markShortestPath(path);
		} else {
			maze.deleteShortestPath();
		}
	}
}
