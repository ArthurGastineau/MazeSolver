package controller;

import maze.Maze;
import view.MazeView;

/**
 * @author arthur
 * The controller of the maze (i.e. the controller in the MVC design pattern). This is the main orchestrator of the
 * application, initializing and controlling the maze and the maze view, handling GUI interactions,
*/

public class MazeController {
	private final static String labyrinthFileName = "data/labyrinthe.maze";
	// Model
	private final Maze maze;
	// View
	private final MazeView view;
	// Listeners
	private int numRows;
	private int numCols;
	
	public MazeController (){
		this.maze = new Maze();
		int [] vals = maze.fromFileGetMazeSize(labyrinthFileName);
		maze.setSize(vals[0], vals[1]);
		maze.initFromTextFile(labyrinthFileName);
		
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

}
