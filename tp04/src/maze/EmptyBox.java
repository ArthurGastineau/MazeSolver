package maze;

public class EmptyBox extends MazeBox {

	public EmptyBox(Maze maze, int row, int col) {
		super(maze, row, col);
	}

	public String typeOfBox() {
		return super.empty;
	}

}
