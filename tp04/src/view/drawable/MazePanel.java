/**
 * 
 */
package view.drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.MazeController;
import maze.Maze;

/**
 * @author arthur
 * A JPanel of the maze. This panel is where the maze is drawn, and where the user
 * can pick the start and end points.
 */
@SuppressWarnings("serial")
public class MazePanel extends JPanel{
	private static final Color BACKGROUND = new Color(0, 100, 0);
	private final MazeController mazeController;
	private final MazeDrawable mazeDrawable;
	private Maze maze;
	private int yOffset, xOffset;
	private Dimension mazeDimension;
	private static final double SQRT_3 = Math.sqrt(3);
	
	public MazePanel(Maze maze, MazeController mazeController) {
		this.maze = maze;
		this.mazeController = mazeController;
		this.mazeDrawable = new MazeDrawable();
		this.yOffset = this.xOffset = 50;
		this.mazeDimension = new Dimension();

		initMazePanel();
	}
	
	private void initMazePanel() {
		int mazeWidth = (int) (maze.getWidth() * MazeDrawable.getBoxSize() * SQRT_3 + xOffset * 1.5) ;
		int mazeLength = (int) (maze.getLength() * MazeDrawable.getBoxSize() * 1.5 + yOffset * 1.3);

		mazeDimension = new Dimension(mazeWidth, mazeLength);
		setMinimumSize(mazeDimension);
		setPreferredSize(mazeDimension);
		setBackground(BACKGROUND);

		repaint();
	}
	
	/**
	 * Calculate the x and y offsets to account for a change in the number of rows and columns.
	 *
	 * @param panelWidth  The maze panel width
	 * @param panelHeight The maze panel height
	 */
	public void setOffset(int panelWidth, int panelHeight) {
		int widthDifference = (int) (panelWidth - mazeDimension.getWidth());
		int heightDifference = (int) (panelHeight - mazeDimension.getHeight());

		if (widthDifference > 0) {
			xOffset = widthDifference / 2;
		} else {
			xOffset = 0;
		}

		if (heightDifference > 0) {
			yOffset = heightDifference / 2;
		} else {
			yOffset = 0;
		}
	}
	
	public void repaintMaze(Maze maze) {
		System.out.println("Repaint");
		this.maze = maze;
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		//get state
		mazeDrawable.drawMaze(maze, graphics, xOffset, yOffset);
	}
	
	/**
	 * Resizes the maze panel view based on the number of columns and the number of rows. This method is called on each
	 * iteration of generating the maze, and enables the user to be able to customize the dimensions of the maze.
	 */
	public void resize() {
		int mazeWidth = (int) (maze.getWidth() * MazeDrawable.getBoxSize() * SQRT_3);
		int mazeLength = (int)( maze.getLength() * MazeDrawable.getBoxSize() * 1.5);

		mazeDimension = new Dimension(mazeWidth, mazeLength);
		setMinimumSize(mazeDimension);
		setPreferredSize(mazeDimension);
	}
}
