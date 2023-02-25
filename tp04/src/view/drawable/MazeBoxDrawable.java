/**
 * 
 */
package view.drawable;

import java.awt.Color;
import java.awt.Graphics;

import model.maze.MazeBox;

/**
 * @author arthur Class responsible for drawing an individual box in the maze.
 */
public class MazeBoxDrawable {

	private static final double SQRT_3 = Math.sqrt(3);

	/**
	 * Draws the current cells walls, and fills it with the appropriate fill based
	 * on the cell properties and the current state of the maze.
	 *
	 * @param cell       The cell to be drawn
	 * @param graphics2D A Graphics2D instance
	 * @param mazeState  The current state of the maze
	 * @param xOffset    The x offset
	 * @param yOffset    The y offset
	 */
	public static void drawMazeBox(MazeBox box, Graphics g, int xOffset, int yOffset, int size) {

		int[] xPoints = { xOffset, (int) ((xOffset + (SQRT_3 / 2) * size)), (int) ((xOffset + SQRT_3 / 2 * size)),
				xOffset, (int) ((xOffset - SQRT_3 / 2 * size)), (int) ((xOffset - (SQRT_3 / 2) * size)) };
		int[] yPoints = { size + yOffset, (int) (0.5 * size + yOffset), (int) (yOffset - 0.5 * size),
				yOffset - 1 * size, (int) (yOffset - 0.5 * size), (int) (yOffset + 0.5 * size) };

		g.setColor(box.getColor());
		g.fillPolygon(xPoints, yPoints, 6);
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 6);

	}

}
