package controller;

import java.awt.event.ActionListener;

import controller.listeners.MazeBoxSelectionRadioListener;
import controller.listeners.MazeCustomNumColsListener;
import controller.listeners.MazeCustomNumRowsListener;
import controller.listeners.MazeGeneratorListener;
import controller.listeners.MazeLoadListener;
import controller.listeners.MazeResetListener;
import controller.listeners.MazeSaveListener;
import controller.listeners.MazeSolverListener;
import model.maze.Maze;
import model.BoxType;
import model.MazeSolver;
import model.MazeState;
import view.MazeView;

/**
 * @author arthur
 * 
 *         The controller of the maze (i.e. the controller in the MVC design
 *         pattern). This is the main orchestrator of the application,
 *         initializing and controlling the maze and the maze view, handling GUI
 *         interactions,
 */

public class MazeController {
	private final static String labyrinthFileName = "data/labyrinthe2.maze";
	// Model
	private Maze maze;
	private MazeState state;
	// View
	private final MazeView view;
	////Custom Maze Dimensions
	private final MazeCustomNumRowsListener mazeCustomNumRowsListener;
	private final MazeCustomNumColsListener mazeCustomNumColsListener;
	//// Buttons
	private final MazeGeneratorListener mazeGeneratorListener;
	private final MazeBoxSelectionRadioListener mazeBoxSelectionRadioListener;
	private final MazeSolverListener mazeSolverListener;
	private final MazeResetListener mazeResetListener;
	private final MazeLoadListener mazeLoadListener;
	private final MazeSaveListener mazeSaveListener;
	// Listeners
	private MazeSolver solver;
	private int numRows;
	private int numCols;
	private BoxType boxType;

	public MazeController() {
		this.state = MazeState.INIT;

		this.maze = new Maze(labyrinthFileName);
		this.boxType = null;
		
		this.mazeCustomNumRowsListener = new MazeCustomNumRowsListener(this);
		this.mazeCustomNumColsListener = new MazeCustomNumColsListener(this);

		this.mazeGeneratorListener = new MazeGeneratorListener(this);
		this.mazeBoxSelectionRadioListener = new MazeBoxSelectionRadioListener(this);
		this.mazeSolverListener = new MazeSolverListener(this);
		this.mazeResetListener = new MazeResetListener(this);
		this.mazeLoadListener = new MazeLoadListener(this);
		this.mazeSaveListener = new MazeSaveListener(this);

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
	
	public MazeGeneratorListener getMazeGeneratorListener() {
		return mazeGeneratorListener;
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

	public MazeLoadListener getMazeLoadListener() {
		return mazeLoadListener;
	}
	
	public MazeSaveListener getMazeSaveListener() {
		return mazeSaveListener;
	}
	
	public MazeCustomNumRowsListener getMazeCustomNumRowsListener() {
		return mazeCustomNumRowsListener;
	}

	public MazeCustomNumColsListener getMazeCustomNumColsListener() {
		return mazeCustomNumColsListener;
	}

	public void solveMaze() {
		solver = new MazeSolver(maze, this);
		solver.initMazeSolver();
		view.repaintMaze(maze);
		state = MazeState.SOLVED;
	}

	public void load(String fileName) {
		maze.initFromTextFile(fileName);
		view.repaintMaze(maze);
		view.resize();
	}
	
	public void save(String fileName) {
		maze.saveToTextFile(fileName);
	}
	
	public void generate() {
		maze.setSize(numRows, numCols);
		maze.initEmptyMaze(numRows,  numCols);
		view.resize();
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
