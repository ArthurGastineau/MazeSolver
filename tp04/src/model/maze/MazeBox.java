package model.maze;

import java.awt.Color;

import model.graph.Vertex;

/**
 *
 * The MazeBox class represents a single hexagon in a maze. It is an abstract
 * class that is extended by specific types of MazeBoxes such as walls, empty
 * spaces, departures, and arrivals. It implements the {@link Vertex} interface
 * from the model.graph package.
 *
 * Each MazeBox has a boolean indicating whether it has been crossed by the maze
 * solver, whether it is currently selected, and its row and column number in
 * the maze. It also contains a reference to the maze it is a part of.
 *
 * This class provides several abstract methods that are implemented in its
 * subclasses.
 *
 * @see Maze
 * @see Vertex
 *
 * @author Arthur Gastineau
 */

public abstract class MazeBox implements Vertex {

	/**
	 * Whether this box has been crossed by the maze solver.
	 */
	private boolean hasCrossed;

	/**
	 * Whether this box is currently selected.
	 */
	protected boolean isSelected;

	/**
	 * The row number of this box in the maze.
	 */
	private int row;

	/**
	 * The column number of this box in the maze.
	 */
	private int col;

	/**
	 * The maze this box is a part of.
	 */
	private Maze maze;

	/**
	 * Constructor for creating a MazeBox object with a given maze, row, and column.
	 *
	 * @param maze The maze the MazeBox is in.
	 * @param row  The row number of the MazeBox in the maze.
	 * @param col  The column number of the MazeBox in the maze.
	 */
	public MazeBox(Maze maze, int row, int col) {
		this.row = row;
		this.col = col;
		this.maze = maze;
		hasCrossed = false;
	}

	/**
	 * Sets whether this box has been crossed by the maze solver.
	 *
	 * @param state The new crossed state of the box.
	 */
	public void setHasCrossed(boolean state) {
		hasCrossed = state;
	}

	/**
	 * Gets whether this box has been crossed by the maze solver.
	 *
	 * @return Whether the box has been crossed.
	 */
	public boolean getHasCrossed() {
		return hasCrossed;
	}

	/**
	 * Gets the row number of this box in the maze.
	 *
	 * @return The row number.
	 */
	@Override
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row number of this box in the maze.
	 *
	 * @param row The new row number.
	 */
	@Override
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the column number of this box in the maze.
	 *
	 * @return The column number.
	 */
	@Override
	public int getCol() {
		return col;
	}

	/**
	 * Sets the column number of this box in the maze.
	 *
	 * @param col The new column number.
	 */
	@Override
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Returns a String representation of this vertex, indicating its row and column
	 * numbers.
	 *
	 * @return A String representation of the vertex.
	 */
	@Override
	public String toString() {
		return "Vertex at (" + row + ", " + col + ")";
	}

	/**
	 * Gets the maze that this box is a part of.
	 *
	 * @return The maze.
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the maze that the box belongs to.
	 *
	 * @param maze The maze that the box belongs to.
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * Determines if the box is a wall.
	 *
	 * @return True if the box is a wall, false otherwise.
	 */
	public abstract boolean isWall();

	/**
	 * Determines if the box is empty.
	 *
	 * @return True if the box is empty, false otherwise.
	 */
	public abstract boolean isEmpty();

	/**
	 * Determines if the box is an arrival point.
	 *
	 * @return True if the box is an arrival point, false otherwise.
	 */
	public abstract boolean isArrival();

	/**
	 * Determines if the box is a departure point.
	 *
	 * @return True if the box is a departure point, false otherwise.
	 */
	public abstract boolean isDeparture();

	/**
	 * Gets the color of the box.
	 *
	 * @return The color of the box.
	 */
	public abstract Color getColor();

	/**
	 * Determines if the box is currently selected.
	 *
	 * @return True if the box is selected, false otherwise.
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * Sets the selected state of the box.
	 *
	 * @param isSelected The new selected state of the box.
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
