import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public final void initFromTextFile(String fileName){
		try{
		
			Path path = Paths.get(fileName);
			
			BufferedReader reader = Files.newBufferedReader(path);
			
			String line = null;
			
			int lineNumber = 0;
			
			while ( (line = reader.readLine()) != null) {
				lineNumber ++;
				if (line.length() != this.ySize) {
	                throw new MazeReadingException(fileName, lineNumber, "Incorrect number of columns");
	            }
				for (int i = 0; i < this.ySize; i++) {
	                char c = line.charAt(i);
	                if (c != 'A' && c != 'D' && c != 'E' && c != 'W') {
	                    throw new MazeReadingException(fileName, lineNumber, "Invalid character in maze definition");
	                }
	                int x = lineNumber - 1;
	                int y = i;
	                switch (c) {
	                    case 'A':
	                        this.maze[x][y] = new ArrivalBox(this, x, y);
	                        break;
	                    case 'D':
	                        this.maze[x][y] = new DepartureBox(this, x, y);
	                        break;
	                    case 'E':
	                        this.maze[x][y] = new EmptyBox(this, x, y);
	                        break;
	                    case 'W':
	                        this.maze[x][y] = new WallBox(this, x, y);
	                        break;
	                }
	            }
			}
			reader.close();
		}
		
		catch (MazeReadingException e) {
			System.out.println("Error reading file " + e.getFileName() + " at line " + e.getLineNumber() + ": " + e.getMessage());
		}
		catch (IOException e) {
	        System.out.println("Error reading file " + fileName + ": " + e.getMessage());
	    }
	}
	
	public void saveToTextFile(String fileName) {
	    try {
	        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
	        for (int i = 0; i < this.xSize; i++) {
	            for (int j = 0; j < this.ySize; j++) {
	                MazeBox box = this.maze[i][j];
	                if (box instanceof ArrivalBox) {
	                    writer.print('A');
	                } else if (box instanceof DepartureBox) {
	                    writer.print('D');
	                } else if (box instanceof EmptyBox) {
	                    writer.print('E');
	                } else if (box instanceof WallBox) {
	                    writer.print('W');
	                }
	            }
	            if (i < xSize - 1) writer.println(); // éviter d'avoir un '\n' à la fin du fichier
	        }
	        writer.close();
	    } catch (IOException e) {
	        System.out.println("Error saving file " + fileName + ": " + e.getMessage());
	    }
	}
}
