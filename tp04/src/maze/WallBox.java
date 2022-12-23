package maze;

public class WallBox extends MazeBox {

	public WallBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public String typeOfBox() {
		return super.wall;
	}
}
