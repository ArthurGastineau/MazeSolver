package maze;

import java.awt.Color;

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
	
	public final Color getColor(){
		return Color.black;
	}
}
