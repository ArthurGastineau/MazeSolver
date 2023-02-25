/**
 * 
 */
package view.drawable;

import java.awt.Graphics;

import maze.Maze;
import maze.MazeBox;

/**
 * @author arthur
 *
 * Class responsible for the drawing of the maze. The maze is drawn by iterating through each cell, and drawing each
 * cell.
 */
public class MazeDrawable {
	private static final double SQRT_3 = Math.sqrt(3);
	private static final int BOX_SIZE = 30;
	/**
	 * Iterates through and draws individual cells of the maze to draw the entire maze.
	 *
	 * @param maze      A maze instance
	 * @param g  A Graphics instance
	 * @param xOffset   The x offset
	 * @param yOffset   The y offset
	 */
	public void drawMaze(Maze maze, Graphics g, int xOffset, int yOffset) {
		Graphics graphics = (Graphics) g;

		for (int r = 0; r < maze.getLength(); r++) {
			for (int c = 0; c < maze.getWidth(); c++) {
				int xCenter = xOffset + (int) ((c * SQRT_3 * BOX_SIZE) + ((r%2) * BOX_SIZE * SQRT_3 / 2));
				int yCenter = yOffset + (int) ((1.5 * BOX_SIZE * r) );
				MazeBox box = maze.getBox(r, c);
				MazeBoxDrawable.drawMazeBox(box, graphics, xCenter, yCenter, BOX_SIZE);
			}
		}
	}
	
	public static int getBoxSize() {
		return BOX_SIZE;
	}
}
