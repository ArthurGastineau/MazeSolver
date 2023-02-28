package controller;

import javax.swing.SwingUtilities;

import controller.listeners.MazeBoxSelectionRadioListener;
import controller.listeners.MazeCustomNumColsListener;
import controller.listeners.MazeCustomNumRowsListener;
import controller.listeners.MazeGeneratorListener;
import controller.listeners.MazeLoadListener;
import controller.listeners.MazeSaveListener;
import controller.listeners.MazeSolverListener;
import model.BoxType;
import model.MazeSolver;
import model.MazeState;
import model.maze.Maze;
import view.MazeView;

/**
 *
 * The controller of the maze (i.e. the controller in the MVC design pattern).
 * This is the main orchestrator of the application, initializing and
 * controlling the maze and the maze view, handling GUI interactions,
 *
 * <p>
 * This class holds references to the model and view components of the maze, as
 * well as various listeners for the GUI buttons and components. It is
 * responsible for updating the state of the model and view based on user
 * interactions, such as generating and solving the maze, and updating the
 * dimensions of the maze.
 * </p>
 *
 * <p>
 * This class also holds the state of the maze, such as whether it has been
 * generated, or solved, and the type of boxes used in the maze.
 * </p>
 *
 * <p>
 * The main public methods of this class are used for generating and solving the
 * maze, loading and saving the maze from/to a file, and resetting the maze to
 * its initial state.
 * </p>
 *
 * @see Maze
 * @see MazeView
 * @see MazeSolver
 * @see MazeState
 * @see BoxType
 * @see MazeGeneratorListener
 * @see MazeBoxSelectionRadioListener
 * @see MazeSolverListener
 * @see MazeLoadListener
 * @see MazeSaveListener
 * @see MazeCustomNumRowsListener
 * @see MazeCustomNumColsListener
 *
 * @author Arthur Gastineau
 */

public class MazeController {

	private final static String initialLabyrinthFileName = "data/labyrinthe2.maze";

	// Model
	private final Maze maze;
	private MazeState state;

	// View
	private final MazeView view;

	//// Custom Maze Dimensions
	private final MazeCustomNumRowsListener mazeCustomNumRowsListener;
	private final MazeCustomNumColsListener mazeCustomNumColsListener;

	//// Buttons
	private final MazeGeneratorListener mazeGeneratorListener;
	private final MazeBoxSelectionRadioListener mazeBoxSelectionRadioListener;
	private final MazeSolverListener mazeSolverListener;
	private final MazeLoadListener mazeLoadListener;
	private final MazeSaveListener mazeSaveListener;

	// Listeners
	private MazeSolver solver;
	private int numRows;
	private int numCols;
	private BoxType boxType;

	/**
	 * Constructor for the {@link MazeController} class. Initializes the state,
	 * maze, and other components such as listeners and views.
	 *
	 */

	public MazeController() {

		this.state = MazeState.INIT;

		this.maze = new Maze(initialLabyrinthFileName);
		this.boxType = null;

		this.mazeCustomNumRowsListener = new MazeCustomNumRowsListener(this);
		this.mazeCustomNumColsListener = new MazeCustomNumColsListener(this);

		this.mazeGeneratorListener = new MazeGeneratorListener(this);
		this.mazeBoxSelectionRadioListener = new MazeBoxSelectionRadioListener(this);
		this.mazeSolverListener = new MazeSolverListener(this);
		this.mazeLoadListener = new MazeLoadListener(this);
		this.mazeSaveListener = new MazeSaveListener(this);

		this.view = new MazeView(maze, this);

		this.numRows = maze.getLength();
		this.numCols = maze.getWidth();
	}

	/**
	 * Returns the current state of the maze game.
	 *
	 * @return the current state of the maze game.
	 */

	public MazeState getState() {
		return state;
	}

	/**
	 * Returns the type of box being used.
	 *
	 * @return the type of box being used.
	 */

	public BoxType getBoxType() {
		return boxType;
	}

	/**
	 * Sets the type of box being used.
	 *
	 * @param boxType the type of box to set.
	 */

	public void setBoxType(BoxType boxType) {
		this.boxType = boxType;
	}

	/**
	 * Sets the number of rows for the maze.
	 *
	 * @param numRows the number of rows to set
	 */

	public void setMazeNumRows(int numRows) {
		this.numRows = numRows;
	}

	/**
	 * Sets the number of columns for the maze.
	 *
	 * @param numCols the number of columns to set
	 */

	public void setMazeNumCols(int numCols) {
		this.numCols = numCols;
	}

	/**
	 * Returns the maze generator listener.
	 *
	 * @return the maze generator listener
	 */

	public MazeGeneratorListener getMazeGeneratorListener() {
		return mazeGeneratorListener;
	}

	/**
	 * Returns the maze box selection radio listener.
	 *
	 * @return the maze box selection radio listener
	 */

	public MazeBoxSelectionRadioListener getMazeBoxSelectionRadioListener() {
		return mazeBoxSelectionRadioListener;
	}

	/**
	 * Returns the maze solver listener.
	 *
	 * @return the maze solver listener
	 */

	public MazeSolverListener getMazeSolverListener() {
		return mazeSolverListener;
	}

	/**
	 * Returns the maze load listener.
	 *
	 * @return the maze load listener
	 */

	public MazeLoadListener getMazeLoadListener() {
		return mazeLoadListener;
	}

	/**
	 * Returns the maze save listener.
	 *
	 * @return the maze save listener
	 */

	public MazeSaveListener getMazeSaveListener() {
		return mazeSaveListener;
	}

	/**
	 * Returns the maze custom number of rows listener.
	 *
	 * @return the maze custom number of rows listener
	 */

	public MazeCustomNumRowsListener getMazeCustomNumRowsListener() {
		return mazeCustomNumRowsListener;
	}

	/**
	 * Returns the maze custom number of columns listener.
	 *
	 * @return the maze custom number of columns listener
	 */

	public MazeCustomNumColsListener getMazeCustomNumColsListener() {
		return mazeCustomNumColsListener;
	}

	/**
	 * Starts the maze solving algorithm, and updates the maze state to SOLVED upon
	 * completion.
	 */

	public void solveMaze() {
		solver = new MazeSolver(maze, this);
		solver.initMazeSolver();
		view.repaintMaze(maze);
		state = MazeState.SOLVED;
		setInstructions();
	}

	/**
	 * Loads a maze from a file and updates the view to reflect the new maze.
	 *
	 * @param fileName the name of the file to load the maze from
	 */

	public void load(String fileName) {
		maze.initFromTextFile(fileName);
		view.repaintMaze(maze);
		view.resize();
		state = MazeState.LOADED;
		setInstructions();
	}

	/**
	 * Saves the current maze to a file.
	 *
	 * @param fileName the name of the file to save the maze to
	 */

	public void save(String fileName) {
		maze.saveToTextFile(fileName);
	}

	/**
	 * Generates a new maze with the current number of rows and columns, and updates
	 * the view to reflect the new maze.
	 */

	public void generate() {
		maze.setSize(numRows, numCols);
		maze.initEmptyMaze(numRows, numCols);
		view.resize();
		state = MazeState.GENERATED;
		setInstructions();
	}

	/**
	 * Updates instructions for maze on the GUI (based on the maze state).
	 */

	public void setInstructions() {
		SwingUtilities.invokeLater(view::setInstructions);
	}

}
