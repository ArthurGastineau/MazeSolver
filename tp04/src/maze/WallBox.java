package maze;

public class WallBox extends MazeBox {

	public WallBox(Maze maze, int x, int y) {
		super(maze, x, y);
	}

	public String typeOfBox() {
		return super.wall;
	}
}
