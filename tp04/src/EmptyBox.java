
public class EmptyBox extends MazeBox {

	public EmptyBox(Maze maze, int x, int y) {
		super(maze, x, y);
	}

	public String typeOfBox() {
		return super.empty;
	}

}
