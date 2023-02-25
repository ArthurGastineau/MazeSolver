/**
 * 
 */
package model;

import java.util.List;

import controller.MazeController;
import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;
import maze.MazeBox;

/**
 * @author arthur
 *
 */
public class MazeSolver {
	private final Maze maze;
	private final MazeController mazeController;

	public MazeSolver(Maze maze, MazeController mazeController) {
		this.maze = maze;
		this.mazeController = mazeController;
	}
	
	public void initMazeSolver () {
		maze.displayMaze();
		Vertex startVertex = maze.getStartVertex();
		Vertex endVertex = maze.getEndVertex();
		ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, startVertex, endVertex);
		List<Vertex> path = shortestPaths.getShortestPath(endVertex);
		maze.markShortestPath(path);
		maze.displayMaze();
	}
}
