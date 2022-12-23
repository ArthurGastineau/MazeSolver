package maze;

public class DepartureBox extends MazeBox {

	public DepartureBox(Maze maze, int x, int y) {
		super(maze, x, y);
	}

	public String typeOfBox() {
		return super.departure;
	}
}
