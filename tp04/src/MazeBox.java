import graph.Vertex;

public abstract class MazeBox implements Vertex{
	
	private boolean hasCrossed;
	private int x;
	private int y;
	private Maze maze;

	public final String departure = "Departure";
	public final String arrival = "Arrival";
	public final String empty = "Empty";
	public final String wall = "Wall";

	public MazeBox(Maze maze, int x, int y) {
		this.x = x;
		this.y = y;
		hasCrossed = false;
		this.setMaze(maze);
	}

	public void setHasCrossed(boolean state) {
		hasCrossed = state;
	}

	public boolean getHasCrossed() {
		return hasCrossed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// get his Label
	public String getLabel () {
		return x + ":" + y;
	}
	

	public abstract String typeOfBox();

	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
	}

}
