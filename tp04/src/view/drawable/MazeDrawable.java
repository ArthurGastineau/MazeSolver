/**
 *
 */
package view.drawable;

import java.awt.Graphics;

import model.maze.Maze;
import model.maze.MazeBox;

/**
 *
 * Class responsible for the drawing of the maze. The maze is drawn by iterating
 * through each cell, and drawing each cell.
 * </p>
 * This class provides functionality to draw the entire maze by iterating over
 * each cell of the maze and calling the {@link drawMazeBox()} method from the
 * {@link MazeBoxDrawable} class.
 * <p>
 * The size of each box can be set using the setBoxSize() method, and its value
 * can be retrieved using the getBoxSize() method.
 *
 * @see Maze
 * @see Graphics
 * @see MazeBox
 * @see MazeBoxDrawable
 *
 *
 * @author Arthur Gastineau
 */
public class MazeDrawable {
	private static final double SQRT_3 = Math.sqrt(3);
	public static final int DEFAULT_BOX_SIZE = 30;
	private static int BOX_SIZE = DEFAULT_BOX_SIZE;

	/**
	 * Iterates through and draws individual cells of the maze to draw the entire
	 * maze.
	 *
	 * @param maze    A maze instance
	 * @param g       A Graphics instance
	 * @param xOffset The x offset
	 * @param yOffset The y offset
	 */
	public void drawMaze(Maze maze, Graphics g, int xOffset, int yOffset) {
		Graphics graphics = g;
		for (int r = 0; r < maze.getLength(); r++) {
			for (int c = 0; c < maze.getWidth(); c++) {
				int xCenter = xOffset + (int) ((c * SQRT_3 * BOX_SIZE) + ((r % 2) * BOX_SIZE * SQRT_3 / 2));
				int yCenter = yOffset + (int) ((1.5 * BOX_SIZE * r));
				MazeBox box = maze.getBox(r, c);
				MazeBoxDrawable.drawMazeBox(box, graphics, xCenter, yCenter, BOX_SIZE);
			}
		}
	}

	/**
	 * Returns the current size of each box in the maze.
	 *
	 * @return The current size of each box
	 */
	public static int getBoxSize() {
		return BOX_SIZE;
	}

	/**
	 * Sets the size of each box in the maze.
	 *
	 * @param size The new size of each box
	 */
	public void setBoxSize(int size) {
		BOX_SIZE = size;
	}
}
