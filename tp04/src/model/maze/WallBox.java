package model.maze;

import java.awt.Color;

/**
 *
 * The WallBox class represents a type of {@link MazeBox} that is a wall. It
 * extends the {@link MazeBox} class and overrides some of its methods to
 * provide specific behavior. A WallBox is not a possible position for a player
 * to be in. The color of a WallBox is always black, or gray if it is selected.
 *
 * @see MazeBox
 * @see Color
 *
 * @author Arthur Gastineau
 */

public class WallBox extends MazeBox {

	/**
	 * Constructs a new WallBox with the given maze, row, and column coordinates.
	 *
	 * @param maze the maze this WallBox belongs to
	 * @param row  the row coordinate of this WallBox
	 * @param col  the column coordinate of this WallBox
	 */
	public WallBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	/**
	 * Determines if the box is a wall.
	 *
	 * @return always true since this box is a wall
	 */
	@Override
	public boolean isWall() {
		return true;
	}

	/**
	 * Determines if the box is empty.
	 *
	 * @return always false since this box is not empty
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * Determines if the box is an arrival box.
	 *
	 * @return always false since this box is not the arrival point
	 */
	@Override
	public boolean isArrival() {
		return false;
	}

	/**
	 * Determines if the box is a departure box.
	 *
	 * @return always false since this box is not the departure point
	 */
	@Override
	public boolean isDeparture() {
		return false;
	}

	/**
	 * Returns the color of this WallBox, which is always black unless it is
	 * selected, in which case it is gray.
	 *
	 * @return the color of this WallBox
	 */
	@Override
	public final Color getColor() {
		if (isSelected)
			return Color.gray;
		else
			return Color.black;
	}
}
