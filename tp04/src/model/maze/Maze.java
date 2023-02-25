package model.maze;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

import model.graph.*;

public class Maze implements Graph {
	private MazeBox[][] maze;
	private int length;
	private int width;
	private Vertex startVertex;
	private Vertex endVertex;

	public static final int MAX_LENGTH = 50;
	public static final int MAX_WIDTH = 50;
	
	private String fileName;

	public Maze(int length, int width) {
		this.length = length;
		this.width = width;
		maze = new MazeBox[length][width];
		initEmptyMaze(length, width);
	}

	public Maze() {
		length = MAX_LENGTH;
		width = MAX_WIDTH;
		maze = new MazeBox[length][width];
		initEmptyMaze(length, width);
	}

	public void setSize(int length, int width) {
		this.length = length;
		this.width = width;
		this.maze = new MazeBox[length][width];
	}

	public void addEmptyBox(int row, int col) {
		// test if in the maze
		maze[row][col] = new EmptyBox(this, row, col);
	}

	public void addArrivalBox(int row, int col) {
		// test if in the maze
		maze[row][col] = new ArrivalBox(this, row, col);
		endVertex = (Vertex) maze[row][col];
	}

	public void addDepartureBox(int row, int col) {
		// test if in the maze
		maze[row][col] = new DepartureBox(this, row, col);
		startVertex = (Vertex) maze[row][col];
	}

	public void addWallBox(int row, int col) {
		// test if in the maze
		maze[row][col] = new WallBox(this, row, col);
	}

	// get successors of a vertex
	// get successors of a vertex
	public List<Vertex> getSuccessor(Vertex vertex) {
		int row = vertex.getRow();
		int col = vertex.getCol();
		List<Vertex> neighbors = new ArrayList<Vertex>();

		// check top-left neighbor
		if ((row - 1) >= 0 && (col - 1) >= 0 && row % 2 == 0
				&& (maze[row - 1][col - 1].isWall()) == false) {
			neighbors.add(maze[row - 1][col - 1]);
		}
		if ((row - 1) >= 0 && (col) >= 0 && row % 2 == 1 && (maze[row - 1][col].isWall() == false)) {
			neighbors.add(maze[row - 1][col]);
		}

		// check top-right neighbor
		if ((row - 1) >= 0 && (col) < width && row % 2 == 0
				&& (maze[row - 1][col].isWall() == false)) {
			neighbors.add(maze[row - 1][col]);
		}
		if ((row - 1) >= 0 && (col + 1) < width && row % 2 == 1
				&& (maze[row - 1][col + 1].isWall() == false)) {
			neighbors.add(maze[row - 1][col + 1]);
		}

		// check left neighbor
		if ((col - 1) >= 0 && (maze[row][col - 1].isWall() == false)) {
			neighbors.add(maze[row][col - 1]);
		}

		// check right neighbor
		if ((col + 1) < width && (maze[row][col + 1].isWall() == false)) {
			neighbors.add(maze[row][col + 1]);
		}

		// check bottom-left neighbor
		if ((row + 1) < length && (col - 1) >= 0 && row % 2 == 0
				&& (maze[row + 1][col - 1].isWall() == false)) {
			neighbors.add(maze[row + 1][col - 1]);
		}
		if ((row + 1) < length && (col) >= 0 && row % 2 == 1
				&& (maze[row + 1][col].isWall() == false)) {
			neighbors.add(maze[row + 1][col]);
		}

		// check bottom-right neighbor
		if ((row + 1) < length && (col) < width && row % 2 == 0
				&& (maze[row + 1][col].isWall() == false)) {
			neighbors.add(maze[row + 1][col]);
		}
		if ((row + 1) < length && (col + 1) < width && row % 2 == 1
				&& (maze[row + 1][col + 1].isWall() == false)) {
			neighbors.add(maze[row + 1][col + 1]);
		}

		return neighbors;
	}

	// return all the vertexes
	public List<Vertex> getAllVertexes() {
		List<Vertex> allVertexes = new ArrayList<Vertex>();
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				if (getMaze()[row][col] != null) {
					allVertexes.add(getMaze()[row][col]);
				}
			}
		}
		return allVertexes;
	}

	// get the distance between 2 vertexes
	public int getDistance(Vertex src, Vertex dst) {
		return 0;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}

	public Vertex getEndVertex() {
		return endVertex;
	}

	public final void initFromTextFile(String fileName) {
		try {

			Path path = Paths.get(fileName);

			BufferedReader reader = Files.newBufferedReader(path);

			String line = null;

			int lineNumber = 0;

			while ((line = reader.readLine()) != null) {
				lineNumber++;
				if (line.length() != this.length) {
					throw new MazeReadingException(fileName, lineNumber, "Incorrect number of columns");
				}
				for (int col = 0; col < this.width; col++) {
					char c = line.charAt(col);
					if (c != 'A' && c != 'D' && c != 'E' && c != 'W') {
						throw new MazeReadingException(fileName, lineNumber, "Invalid character in maze definition");
					}
					int row = lineNumber - 1;
					switch (c) {
					case 'A':
						addArrivalBox(row, col);
						break;
					case 'D':
						addDepartureBox(row, col);
						break;
					case 'E':
						addEmptyBox(row, col);
						break;
					case 'W':
						addWallBox(row, col);
						break;
					}
				}
			}
			this.fileName = fileName;
			reader.close();
		}

		catch (MazeReadingException e) {
			System.out.println(e.getMessage());
			System.out.println(
					"Error reading file " + e.getFileName() + " at line " + e.getLineNumber() + ": " + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			System.out.println("Error reading file " + fileName + ": " + e.getMessage());
		}
	}

	public void saveToTextFile(String fileName) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (int row = 0; row < this.length; row++) {
				for (int col = 0; col < this.width; col++) {
					MazeBox box = this.getMaze()[row][col];
					if (box.isArrival()) {
						writer.print('A');

					} else if (box.isDeparture()) {
						writer.print('D');

					} else if (box.isEmpty()) {
						writer.print('E');
					} else if (box.isWall()) {
						writer.print('W');
					}
				}
				if (row < length - 1)
					writer.println(); // éviter d'avoir un '\n' à la fin du fichier
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error saving file " + fileName + ": " + e.getMessage());
		}
	}

	public void saveShortestPath(String fileName, List<Vertex> shortestPath) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (int row = 0; row < this.length; row++) {
				for (int col = 0; col < this.width; col++) {
					MazeBox box = this.getMaze()[row][col];
					if (box.isArrival()) {
						writer.print('A');

					} else if (box.isDeparture()) {
						writer.print('D');

					} else if (shortestPath.contains((Vertex) box)) {
						writer.print('.');
					} else if (box.isEmpty()) {
						writer.print('E');
					} else if (box.isWall()) {
						writer.print('W');
					}
				}
				if (row < length - 1)
					writer.println(); // éviter d'avoir un '\n' à la fin du fichier
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error saving file " + fileName + ": " + e.getMessage());
		}
	}
	
    public void markShortestPath(List<Vertex> shortestPath) {

        // Iterating through the shortest path
        for (Vertex v : shortestPath) {
            this.maze[v.getRow()][v.getCol()].setHasCrossed(true);
        }
    }
    
	public void displayMaze(String fileName) {
		Path path = Paths.get(fileName);

		BufferedReader reader;
		try {
			reader = Files.newBufferedReader(path);
			String line = null;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void displayMaze() {
		for (int r = 0; r < getLength(); r++) {
			for (int c = 0; c < getWidth(); c++) {
				MazeBox box = this.getBox(r, c);
				if (box.isArrival()) {
					System.out.print('A');

				} else if (box.isDeparture()) {
					System.out.print('D');

				} else if (box.isEmpty() && box.getHasCrossed()) {
					System.out.print('.');
				} else if (box.isEmpty() && !box.getHasCrossed()) {
					System.out.print('E');
				} else if (box.isWall()) {
					System.out.print('W');
				}
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	public MazeBox[][] getMaze() {
		return maze;
	}
	
	public MazeBox getBox(int row, int col) {
		return maze[row][col];
	}

	public int[] fromFileGetMazeSize(String fileName) {
		try {
			Path path = Paths.get(fileName);

			BufferedReader reader = Files.newBufferedReader(path);

			String line = null;

			int lineNumber = 0;

			int sizeMaxLine = 0;

			while ((line = reader.readLine()) != null) {
				lineNumber++;
				if (line.length() > sizeMaxLine)
					sizeMaxLine = line.length();
			}
			reader.close();
			int[] size = { lineNumber, sizeMaxLine };
			return size;
		} catch (IOException e) {
			System.out.println("Error reading file " + fileName + ": " + e.getMessage());
		}
		return null;
	}

	public void modifyBox(int col, int row, char c) {
		switch (c) {
		/*
		 * case 'A': addArrivalBox(row, col); endVertex = (Vertex) getMaze()[row][col];
		 * break; case 'D': addDepartureBox(row, col); startVertex = (Vertex)
		 * getMaze()[row][col]; break;
		 */
		case 'E':
			addEmptyBox(row, col);
			break;
		case 'W':
			addWallBox(row, col);
			break;
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public void initEmptyMaze(int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				addEmptyBox(i, j);
			}
		}
	}

	public boolean hasDepartureBox() {
		// Iterate through the boxes in the maze
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				// Check if the current box is a departure box
				if (maze[i][j].isDeparture()) {
					// Return the departure box
					return true;
				}
			}
		}
		// If no departure box was found, return null
		return false;
	}

	public boolean hasArrivalBox() {
		// Iterate through the boxes in the maze
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				// Check if the current box is a departure box
				if (maze[i][j].isArrival()) {
					// Return the departure box
					return true;
				}
			}
		}
		// If no departure box was found, return null
		return false;
	}
	
	/**
	 * Resets the maze, clearing the start, end and goal cells, as well as reinitializing the maze cells.
	 */
	public void resetMaze() {
		if (fileName == null) initFromTextFile("data/labyrinthe.maze");
		else initFromTextFile(fileName);
	}

}
