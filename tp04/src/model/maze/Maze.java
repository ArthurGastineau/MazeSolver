package model.maze;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

import model.MazeConstants;
import model.graph.*;

/**
 * 
 * The Maze class represents a maze as a 2D grid of MazeBoxes. The Maze class
 * implements the {@link Graph} interface, making it possible to apply graph
 * algorithms, such as Dijkstra, to find paths in the maze.
 * <p>
 * The Maze class provides methods to load a maze from a text file, to create a
 * new, empty maze, and to add various types of MazeBoxes to the maze (walls,
 * empty boxes, departure boxes, and arrival boxes).
 * </p>
 * <p>
 * A Maze object can also remember the start and end vertices of the maze as
 * Vertex objects, making it easy to run path finding algorithms on the maze.
 * </p>
 * <p>
 * Finally, the Maze class provides a {@link setSelectedBox()} method to select
 * a specific {@link MazeBox} in the maze. This feature is useful in the maze
 * editor.
 *
 * @see Graph
 * @see Vertex
 * @see MazeBox
 * @see EmptyBox
 * @see Wallbox
 * @see EmptyBox
 * @see DepartureBox
 * @see ArrivalBox
 * 
 * @author Arthur Gastineau
 */

public class Maze implements Graph {
	private MazeBox[][] maze;
	private int length;
	private int width;
	private Vertex startVertex;
	private Vertex endVertex;

	private MazeBox selectedBox;

	private String fileName;

	/**
	 * Creates a new, empty maze of the specified size.
	 *
	 * @param length the number of rows in the maze.
	 * @param width  the number of columns in the maze.
	 */
	public Maze(int length, int width) {
		setSize(length, width);
		initEmptyMaze(length, width);
	}

	/**
	 * Creates a new, empty maze with the default size (see {@link MazeConstants}).
	 */
	public Maze() {
		setSize(MazeConstants.MAX_NUM_ROWS, MazeConstants.MAX_NUM_COLS);
		initEmptyMaze(length, width);
	}

	/**
	 * Creates a new maze by loading it from a text file.
	 *
	 * @param fileName the name of the text file containing the maze.
	 */
	public Maze(String fileName) {
		initFromTextFile(fileName);
	}

	/**
	 * Sets the size of the maze.
	 *
	 * @param length the number of rows in the maze.
	 * @param width  the number of columns in the maze.
	 */
	public void setSize(int length, int width) {
		this.length = length;
		this.width = width;
		this.maze = new MazeBox[length][width];
	}

	/**
	 * Adds an empty box to the maze at the specified position.
	 *
	 * @param row the row in which to add the box.
	 * @param col the column in which to add the box.
	 */
	public void addEmptyBox(int row, int col) {
		if (row >= 0 && row < length && col >= 0 && col < width)
			maze[row][col] = new EmptyBox(this, row, col);
	}

	/**
	 * Adds an arrival box to the maze at the specified position.
	 *
	 * @param row the row in which to add the box.
	 * @param col the column in which to add the box.
	 */
	public void addArrivalBox(int row, int col) {
		if (row >= 0 && row < length && col >= 0 && col < width) {
			maze[row][col] = new ArrivalBox(this, row, col);
			endVertex = (Vertex) maze[row][col];
		}
	}

	/**
	 * Adds a departure box at the specified location and sets it as the start
	 * vertex.
	 * 
	 * @param row the row in which to add the box.
	 * @param col the column in which to add the box.
	 */
	public void addDepartureBox(int row, int col) {
		if (row >= 0 && row < length && col >= 0 && col < width) {
			maze[row][col] = new DepartureBox(this, row, col);
			startVertex = (Vertex) maze[row][col];
		}
	}

	/**
	 * Adds a wall box at the specified location.
	 * 
	 * @param row the row in which to add the box.
	 * @param col the column in which to add the box.
	 */
	public void addWallBox(int row, int col) {
		if (row >= 0 && row < length && col >= 0 && col < width)
			maze[row][col] = new WallBox(this, row, col);
	}

	/**
	 * Returns a list of all vertices adjacent to the specified vertex.
	 * 
	 * @param vertex the vertex to find neighbors of
	 * 
	 * @return a list of vertices adjacent to the specified vertex
	 */
	public List<Vertex> getSuccessor(Vertex vertex) {

		int row = vertex.getRow();
		int col = vertex.getCol();
		List<Vertex> neighbors = new ArrayList<Vertex>();

		// Use the possibleNeighbors matrix to check for valid neighbors
		// top left, top right, left, right, bottom left, bottom right
		int[][] possibleNeighbors = (row % 2 == 0)
				? new int[][] { { -1, -1 }, { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 } }
				: new int[][] { { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };

		// Check if the new row and column are valid and not a wall
		for (int[] pos : possibleNeighbors) {
			int r = row + pos[0];
			int c = col + pos[1];
			if (r >= 0 && r < length && c >= 0 && c < width && !maze[r][c].isWall()) {
				neighbors.add(maze[r][c]);
			}
		}

		return neighbors;
	}

	/**
	 * Returns a list of all vertices in the maze.
	 * 
	 * @return a list of all vertices in the maze
	 */
	public List<Vertex> getAllVertexes() {
		List<Vertex> allVertexes = new ArrayList<Vertex>();
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				if (getBox(row, col) != null) {
					allVertexes.add(getBox(row, col));
				}
			}
		}
		return allVertexes;
	}

	/**
	 * Returns the distance between two vertices in the maze.
	 * 
	 * @param src the source vertex
	 * @param dst the destination vertex
	 * 
	 * @return the distance between the two vertices
	 */
	public int getDistance(Vertex src, Vertex dst) {
		return 0;
	}

	/**
	 * Returns the start vertex of the maze.
	 * 
	 * @return the start vertex of the maze
	 */
	public Vertex getStartVertex() {
		return startVertex;
	}

	/**
	 * Returns the end vertex of the maze.
	 * 
	 * @return the end vertex of the maze
	 */
	public Vertex getEndVertex() {
		return endVertex;
	}

	/**
	 * Initializes the maze from a text file.
	 * 
	 * @param fileName the name of the file to read the maze from
	 */
	public final void initFromTextFile(String fileName) {
		if (fileName == null || !fileName.endsWith(".maze")) {
			return;
		} else {
			try {
				int[] vals = fromFileGetMazeSize(fileName);
				setSize(vals[0], vals[1]);

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
							throw new MazeReadingException(fileName, lineNumber,
									"Invalid character in maze definition");
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
						default:
							break;
						}
					}
				}
				this.fileName = fileName;
				// reader.close();
			} catch (MazeReadingException e) {
				System.out.println(e.getMessage());
				System.out.println("Error reading file " + e.getFileName() + " at line " + e.getLineNumber() + ": "
						+ e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getStackTrace());
				System.out.println("Error reading file " + fileName + ": " + e.getMessage());
			}
		}
	}

	/**
	 * Saves the maze to a text file.
	 * 
	 * @param fileName the name of the file to save the maze to
	 */
	public void saveToTextFile(String fileName) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (int row = 0; row < this.length; row++) {
				for (int col = 0; col < this.width; col++) {
					MazeBox box = this.getBox(row, col);
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

	/**
	 * Marks the shortest path found in the maze.
	 * 
	 * @param shortestPath the shortest path found in the maze
	 */
	public void markShortestPath(List<Vertex> shortestPath) {
		deleteShortestPath();
		// Iterating through the shortest path
		for (Vertex v : shortestPath) {
			this.maze[v.getRow()][v.getCol()].setHasCrossed(true);
		}
	}

	/**
	 * Deletes the marked shortest path in the maze.
	 */
	public void deleteShortestPath() {
		// Iterating through the maze and delete the one's marked
		for (MazeBox[] rowBox : maze) {
			for (MazeBox box : rowBox) {
				box.setHasCrossed(false);
			}
		}
	}

	/**
	 * Displays the maze stored in a text file.
	 * 
	 * @param fileName the name of the file that stores the maze to display
	 */
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

	/**
	 * Displays the maze in the console
	 */
	public void displayMaze() {
		for (int row = 0; row < getLength(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				MazeBox box = this.getBox(row, col);
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

	/**
	 * Selects a box in the maze.
	 * 
	 * @param rowClosest the row number of the closest box to select
	 * @param colClosest the column number of the closest box to select
	 */
	public void setSelected(int rowClosest, int colClosest) {
		if (selectedBox != null) {
			// desactivate the last selected
			selectedBox.setSelected(false);
		}
		if ((rowClosest >= 0 && colClosest >= 0 && rowClosest < getLength() && colClosest < getWidth())) {
			this.selectedBox = getBox(rowClosest, colClosest);
			selectedBox.setSelected(true);
		}
	}

	/**
	 * Gets the currently selected box in the maze.
	 * 
	 * @return the selected box
	 */
	public MazeBox getSelected() {
		return selectedBox;
	}

	/**
	 * Gets the maze as a 2D array of MazeBoxes.
	 * 
	 * @return the maze as a 2D array of MazeBoxes
	 */
	public MazeBox[][] getMaze() {
		return maze;
	}

	/**
	 * Gets a box from the maze.
	 * 
	 * @param row the row number of the box to get
	 * @param col the column number of the box to get
	 * @return the box at the given position in the maze
	 */
	public MazeBox getBox(int row, int col) {
		return maze[row][col];
	}

	/**
	 * Gets the size of the maze stored in a text file.
	 * 
	 * @param fileName the name of the file that stores the maze
	 * @return an array containing the number of rows and columns in the maze
	 */
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
			return new int[] { lineNumber, sizeMaxLine };
		} catch (IOException e) {
			System.out.println("Error reading file " + fileName + ": " + e.getMessage());
		}
		return null;
	}

	/**
	 * Gets the length of the maze.
	 * 
	 * @return the length of the maze
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Gets the width of the maze.
	 * 
	 * @return the width of the maze
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Initializes an empty maze.
	 * 
	 * @param rows the number of rows in the maze
	 * @param cols the number of columns in the maze
	 */
	public void initEmptyMaze(int rows, int cols) {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				addEmptyBox(row, col);
			}
		}
	}

	/**
	 * Returns true if there is at least one departure box in the maze, false
	 * otherwise.
	 *
	 * @return true if there is a departure box, false otherwise
	 */
	public boolean hasDepartureBox() {
		// Iterate through the boxes in the maze
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				// Check if the current box is a departure box
				if (maze[row][col].isDeparture()) {
					return true;
				}
			}
		}
		// If no departure box was found, return null
		return false;
	}

	/**
	 * Returns true if there is at least one arrival box in the maze, false
	 * otherwise.
	 *
	 * @return true if there is an arrival box, false otherwise
	 */
	public boolean hasArrivalBox() {
		// Iterate through the boxes in the maze
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < width; col++) {
				// Check if the current box is an arrival box
				if (maze[row][col].isArrival()) {
					return true;
				}
			}
		}
		// If no arrival box was found, return null
		return false;
	}

	/**
	 * Resets the maze, clearing the start, end and goal cells, as well as
	 * reinitializing the maze cells.
	 */
	public void resetMaze() {
		if (fileName == null)
			initFromTextFile("data/labyrinthe.maze");
		else
			initFromTextFile(fileName);
	}

}
