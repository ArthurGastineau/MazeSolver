package maze;

public class DepartureBox extends MazeBox {

	public DepartureBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}
	
	public boolean isWall() {
		return false;
	}
	
	public boolean isEmpty() {
		return false;
	}
	
	public boolean isArrival() {
		return false;
	}
	
	public boolean isDeparture() {
		return true;
	}
}
