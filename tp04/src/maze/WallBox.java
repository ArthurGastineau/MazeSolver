package maze;

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
}
