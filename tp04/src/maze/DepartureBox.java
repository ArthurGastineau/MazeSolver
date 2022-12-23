package maze;

public class DepartureBox extends MazeBox {

	public DepartureBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public String typeOfBox() {
		return super.departure;
	}
}
