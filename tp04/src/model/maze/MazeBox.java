package model.maze;
import java.awt.Color;

import model.graph.Vertex;

public abstract class MazeBox implements Vertex{

	private boolean hasCrossed;
	private int row;
	private int col;
	private Maze maze;

	public MazeBox(Maze maze, int row, int col) {
		this.row = row;
		this.col = col;
		hasCrossed = false;
		this.setMaze(maze);
	}

	public void setHasCrossed(boolean state) {
		hasCrossed = state;
	}

	public boolean getHasCrossed() {
		return hasCrossed;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "Vertex at (" + row + ", " + col + ")";
	}

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	public abstract boolean isWall();
	
	public abstract boolean isEmpty();
	
	public abstract boolean isArrival();
	
	public abstract boolean isDeparture();
	
	public abstract Color getColor();

}
