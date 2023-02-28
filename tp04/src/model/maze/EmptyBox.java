package model.maze;

import java.awt.Color;

/**
 *
 * The EmptyBox class represents a type of {@link MazeBox} that is empty. It
 * extends the {@link MazeBox} class and overrides some of its methods to
 * provide specific behavior. An empty box can be crossed by the player to
 * traverse the maze. The color of the box changes depending on whether it is
 * selected, crossed, or not.
 *
 * @see MazeBox
 * @see Color
 *
 * @author Arthur Gastineau
 */

public class EmptyBox extends MazeBox {

	/**
	 * Constructor for creating an EmptyBox object with a given maze, row, and
	 * column.
	 *
	 * @param maze The maze the EmptyBox is in.
	 * @param row  The row number of the EmptyBox in the maze.
	 * @param col  The column number of the EmptyBox in the maze.
	 */
	public EmptyBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	/**
	 * Determines if the box is a wall.
	 *
	 * @return False since the box is not a wall.
	 */
	@Override
	public boolean isWall() {
		return false;
	}

	/**
	 * Determines if the box is empty.
	 *
	 * @return True since the box is empty.
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}

	/**
	 * Determines if the box is an arrival box.
	 *
	 * @return False since the box is not an arrival box.
	 */
	@Override
	public boolean isArrival() {
		return false;
	}

	/**
	 * Determines if the box is a departure box.
	 *
	 * @return False since the box is not a departure box.
	 */
	@Override
	public boolean isDeparture() {
		return false;
	}

	/**
	 * Retrieves the color of the box based on whether it is selected, crossed, or
	 * not.
	 *
	 * @return The color of the box (yellow if crossed, gray if selected and not
	 *         crossed, white if not selected and not crossed).
	 */
	@Override
	public final Color getColor() {
		if (isSelected) {
			return Color.gray;
		} else if (super.getHasCrossed()) {
			return Color.yellow;
		} else {
			return Color.white;
		}
	}

}
