package model.maze;

import java.awt.Color;

/**
 * The DepartureBox class represents a type of {@link MazeBox} that represents
 * the arrival point in a maze. It extends the {@link MazeBox} class and
 * overrides some of its methods to provide specific behavior.
 *
 * @author Arthur Gastineau
 */

public class DepartureBox extends MazeBox {

	/**
	 * Constructor for creating a DepartureBox object with a given maze, row, and
	 * column.
	 * 
	 * @param maze The maze the DepartureBox is in.
	 * @param row  The row number of the DepartureBox in the maze.
	 * @param col  The column number of the DepartureBox in the maze.
	 */
	public DepartureBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	/**
	 * Determines if the box is a wall.
	 * 
	 * @return False since the box is not a wall.
	 */
	public boolean isWall() {
		return false;
	}

	/**
	 * Determines if the box is empty.
	 * 
	 * @return False since the box is not empty.
	 */
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Determines if the box is an arrival box.
	 * 
	 * @return False since the box is not an arrival box.
	 */
	public boolean isArrival() {
		return false;
	}

	/**
	 * Determines if the box is a departure box.
	 * 
	 * @return True since the box is a departure box.
	 */
	public boolean isDeparture() {
		return true;
	}

	/**
	 * Retrieves the color of the box based on whether it is selected or not.
	 * 
	 * @return The color of the box (blue if not selected, gray if selected).
	 */
	public final Color getColor() {
		if (isSelected)
			return Color.gray;
		else
			return Color.blue;
	}
}
