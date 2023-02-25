package model.maze;

import java.awt.Color;

public class ArrivalBox extends MazeBox {

	public ArrivalBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public boolean isWall() {
		return false;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean isArrival() {
		return true;
	}

	public boolean isDeparture() {
		return false;
	}

	public final Color getColor() {
		if (isSelected)
			return Color.gray;
		else
			return Color.red;
	}
}
