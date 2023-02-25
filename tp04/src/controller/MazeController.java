package controller;

import controller.listeners.MazeSolverListener;
import maze.Maze;
import model.MazeSolver;
import view.MazeView;

/**
 * @author arthur
 * The controller of the maze (i.e. the controller in the MVC design pattern). This is the main orchestrator of the
 * application, initializing and controlling the maze and the maze view, handling GUI interactions,
*/

public class MazeController {
	private final static String labyrinthFileName = "data/labyrinthe.maze";
	// Model
	private Maze maze;
	// View
	private final MazeView view;
	//Buttons
	private final MazeSolverListener mazeSolverListener;
	// Listeners
	private MazeSolver solver;
	private int numRows;
	private int numCols;
	
	public MazeController (){
		System.out.println("Init controller");
		this.maze = new Maze();
		int [] vals = maze.fromFileGetMazeSize(labyrinthFileName);
		maze.setSize(vals[0], vals[1]);
		maze.initFromTextFile(labyrinthFileName);
		
		this.mazeSolverListener = new MazeSolverListener(this);
		
		this.view = new MazeView(maze, this);
		
		this.numRows = maze.getLength();
		this.numCols = maze.getWidth();
	}
	
	public void setMazeNumRows(int numRows) {
		this.numRows = numRows;
	}

	public void setMazeNumCols(int numCols) {
		this.numCols = numCols;
	}
	
	public MazeSolverListener getMazeSolverListener() {
		return mazeSolverListener;
	}
	
	public void solveMaze() {
		solver = new MazeSolver(maze, this);
		solver.initMazeSolver();
		view.repaintMaze(maze);
		//Maze solvedMaze = solver.initMazeSolver();
		//maze.displayMaze();
		//solvedMaze.displayMaze();
		//view.repaintMaze(solvedMaze);
	}

}
