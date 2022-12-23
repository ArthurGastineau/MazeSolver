import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;

import graph.*;

public class Maze implements Graph{
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
		maze[y][x] = new EmptyBox(this, x, y);
	}
	
	public void addArrivalBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new ArrivalBox(this, x, y);
	}
	
	public void addDepartureBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new DepartureBox(this, x, y);
	}
	
	public void addWallBox (int x, int y) {
		// test if in the maze
		maze[y][x] = new WallBox(this, x, y);
	}
	
	// get successors of a vertex
	public List<Vertex> getSuccessor (Vertex vertex){
		int x = vertex.getX();
		int y = vertex.getY();
		List<Vertex> neighbors = new ArrayList<Vertex>();
		
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
	//return all the vertexes
	public List<Vertex> getAllVertexes() {
		List<Vertex> allVertexes = new ArrayList<Vertex>();
		for (int x = 0; x < xSize; x++) {
			for (int y = 0; y < ySize; y++) {
				if (maze[x][y] != null) {
					allVertexes.add(maze[x][y]);
				}
			}
		}
		return allVertexes;
	}
	//get the distance between 2 vertexes
	public int getDistance(Vertex src,Vertex dst) {
		return 0;
	}

	public void init() {
		// TODO Auto-generated method stub
	}
	
	public final void initFromTextFile(String fileName) throws IOException{
		try{
		
			Path path = Paths.get(fileName);
			
			BufferedReader reader = Files.newBufferedReader(path);
			
			String line = null;
			
			while ( (line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (Exception ex) {
			String st = ex.getMessage();
			if (st.equals(fileName)) {
				System.out.println("ERROR !! : The path of the file is incorrect");
			}
			else {
				System.out.println(st);
			}
		}
	}
}
