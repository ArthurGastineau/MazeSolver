package maze;

public class ArrivalBox extends MazeBox {

	public ArrivalBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public String typeOfBox() {
		return super.arrival;
	}
}
