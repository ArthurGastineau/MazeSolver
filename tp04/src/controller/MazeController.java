package controller;

import java.awt.event.ActionListener;

import controller.listeners.MazeResetListener;
import controller.listeners.MazeSolverListener;
import maze.Maze;
import model.MazeSolver;
import model.MazeState;
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
	private final MazeResetListener mazeResetListener;
	//
	private MazeState state;
	// Listeners
	private MazeSolver solver;
	private int numRows;
	private int numCols;
	
	public MazeController (){
		this.state = MazeState.INIT;
		
		this.maze = new Maze();
		int [] vals = maze.fromFileGetMazeSize(labyrinthFileName);
		maze.setSize(vals[0], vals[1]);
		maze.initFromTextFile(labyrinthFileName);
		
		this.mazeSolverListener = new MazeSolverListener(this);
		this.mazeResetListener = new MazeResetListener(this);
		
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
		state = MazeState.SOLVED;
	}
	
	/**
	 * Resets the maze to its initial state. This includes resetting the maze cells, and repainting the view.
	 * 
	 */
	public void reset() {

		state = MazeState.INIT;

		maze.resetMaze();
		view.resetView();
	}

	/**
	 * @return
	 */
	public ActionListener getMazeResetListener() {
		// TODO Auto-generated method stub
		return mazeResetListener;
	}

}
