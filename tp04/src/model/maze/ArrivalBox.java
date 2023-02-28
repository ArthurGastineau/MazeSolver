package model.maze;

import java.awt.Color;

/**
 * The ArrivalBox class represents a type of {@link MazeBox} that represents the
 * arrival point in a maze. It extends the {@link MazeBox} class and overrides
 * some of its methods to provide specific behavior.
 *
 * @see MazeBox
 * @see Color
 *
 * @author Arthur Gastineau
 */

public class ArrivalBox extends MazeBox {

	/**
	 * Constructs an ArrivalBox object with the specified maze, row, and column.
	 *
	 * @param maze the Maze object that contains the box
	 * @param row  the row of the box in the maze
	 * @param col  the column of the box in the maze
	 */
	public ArrivalBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	/**
	 * Determines whether the box is a wall.
	 *
	 * @return always false since this box is not a wall
	 */
	@Override
	public boolean isWall() {
		return false;
	}

	/**
	 * Determines whether the box is empty.
	 *
	 * @return always false since this box is not empty
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Determines whether the box is the arrival point.
	 *
	 * @return always true since this box is the arrival point
	 */
	@Override
	public boolean isArrival() {
		return true;
	}

	/**
	 * Determines whether the box is the departure point.
	 *
	 * @return always false since this box is not the departure point
	 */
	@Override
	public boolean isDeparture() {
		return false;
	}

	/**
	 * Returns the color of the box based on whether it is selected or not.
	 *
	 * @return the color of the box (gray if selected, red if not selected)
	 */
	@Override
	public final Color getColor() {
		if (isSelected)
			return Color.gray;
		else
			return Color.red;
	}
}
