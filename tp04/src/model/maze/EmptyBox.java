package model.maze;

import java.awt.Color;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public class EmptyBox extends MazeBox {

	public EmptyBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public boolean isWall() {
		return false;
	}

	public boolean isEmpty() {
		return true;
	}

	public boolean isArrival() {
		return false;
	}

	public boolean isDeparture() {
		return false;
	}

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
