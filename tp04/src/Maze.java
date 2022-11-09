import java.util.ArrayList;

public class Maze {
	private MazeBox[][] maze;
	private int xSize;
	private int ySize;
	
	public Maze (int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.maze = new MazeBox[ySize][xSize];
	}
	
	public void addEmptyBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new EmptyBox(x, y);
	}
	
	public void addArrivalBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new ArrivalBox(x, y);
	}
	
	public void addDepartureBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new DepartureBox(x, y);
	}
	
	public void addWallBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new WallBox(x, y);
	}
	
	public ArrayList<MazeBox> getNeighbors (MazeBox Box) {
		int x = Box.getX();
		int y = Box.getY();
		ArrayList<MazeBox> neighbors = new ArrayList<MazeBox>();
		
		if ((x + 1) < xSize) {
			neighbors.add(maze[y][x + 1]);
		}
		
		if ((x - 1) > 0) {
			neighbors.add(maze[y][x - 1]);
		}
		
		if ((y + 1) < ySize) {
			neighbors.add(maze[y + 1][x]);
		}
		
		if ((y - 1) > 0) {
			neighbors.add(maze[y - 1][x]);
		}
		
		return neighbors;
	}
	
	
	
	
}
