package model.maze;

import java.awt.Color;

/**
 * 
 *
 * @author Arthur Gastineau
 */

public class WallBox extends MazeBox {

	public WallBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public boolean isWall() {
		return true;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean isArrival() {
		return false;
	}

	public boolean isDeparture() {
		return false;
	}

	public final Color getColor() {
		if (isSelected)
			return Color.gray;
		else
			return Color.black;
	}
}
