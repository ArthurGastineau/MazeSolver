package maze;

public class ArrivalBox extends MazeBox {

	public ArrivalBox(Maze maze, int x, int y) {
		super(maze, x, y);
	}

	public String typeOfBox() {
		return super.arrival;
	}
}
