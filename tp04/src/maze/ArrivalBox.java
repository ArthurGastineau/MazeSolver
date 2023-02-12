package maze;

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
}
