package controller;

import controller.listeners.MazeBoxSelectionRadioListener;
import controller.listeners.MazeResetListener;
import controller.listeners.MazeSolverListener;
import model.maze.Maze;
import model.BoxType;
import model.MazeSolver;
import model.MazeState;
import view.MazeView;

/**
 * @author arthur 
 * 
 * 		   The controller of the maze (i.e. the controller in the MVC
 *         design pattern). This is the main orchestrator of the application,
 *         initializing and controlling the maze and the maze view, handling GUI
 *         interactions,
 */

public class MazeController {
	private final static String labyrinthFileName = "data/labyrinthe2.maze";
	// Model
	private Maze maze;
	// View
	private final MazeView view;
	// Buttons
	private final MazeBoxSelectionRadioListener mazeBoxSelectionRadioListener;
	private final MazeSolverListener mazeSolverListener;
	private final MazeResetListener mazeResetListener;
	//
	private MazeState state;
	// Listeners
	private MazeSolver solver;
	private int numRows;
	private int numCols;
	private BoxType boxType;

	public MazeController() {
		this.state = MazeState.INIT;

		this.maze = new Maze();
		int[] vals = maze.fromFileGetMazeSize(labyrinthFileName);
		maze.setSize(vals[0], vals[1]);
		maze.initFromTextFile(labyrinthFileName);
		this.boxType = null;
		
		this.mazeBoxSelectionRadioListener = new MazeBoxSelectionRadioListener(this);
		this.mazeSolverListener = new MazeSolverListener(this);
		this.mazeResetListener = new MazeResetListener(this);

		this.view = new MazeView(maze, this);

		this.numRows = maze.getLength();
		this.numCols = maze.getWidth();
	}
	
	public MazeState getState() {
		return state;
	}
	
	public BoxType getBoxType() {
		return boxType;
	}
	
	public void setBoxType(BoxType boxType) {
		this.boxType = boxType;
	}

	public void setMazeNumRows(int numRows) {
		this.numRows = numRows;
	}

	public void setMazeNumCols(int numCols) {
		this.numCols = numCols;
	}
	
	public MazeBoxSelectionRadioListener getMazeBoxSelectionRadioListener() {
		return mazeBoxSelectionRadioListener;
	}

	public MazeSolverListener getMazeSolverListener() {
		return mazeSolverListener;
	}
	
	public MazeResetListener getMazeResetListener() {
		return mazeResetListener;
	}

	public void solveMaze() {
		solver = new MazeSolver(maze, this);
		solver.initMazeSolver();
		view.repaintMaze(maze);
		state = MazeState.SOLVED;
	}

	/**
	 * Resets the maze to its initial state. This includes resetting the maze cells,
	 * and repainting the view.
	 * 
	 */
	public void reset() {

		state = MazeState.INIT;

		maze.resetMaze();
		view.resetView();
	}

}
