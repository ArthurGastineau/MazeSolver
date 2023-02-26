/**
 * 
 */
package view.drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.MazeController;
import controller.listeners.MazeBoxClickListener;
import controller.listeners.MazeSelectedBoxListener;
import model.BoxType;
import model.maze.Maze;

/**
 * @author arthur A JPanel of the maze. This panel is where the maze is drawn,
 *         and where the user can pick the start and end points.
 */
@SuppressWarnings("serial")
public class MazePanel extends JPanel {
	private static final Color BACKGROUND = new Color(0, 100, 0);
	private final MazeController mazeController;
	private final MazeDrawable mazeDrawable;
	private Maze maze;
	private int yOffset, xOffset;
	private Dimension mazeDimension;
	private boolean outOfMaze = true;
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
		int mazeWidth = (int) (maze.getWidth() * MazeDrawable.getBoxSize() * SQRT_3 + xOffset * 1.5);
		int mazeLength = (int) (maze.getLength() * MazeDrawable.getBoxSize() * 1.5 + yOffset * 1.3);

		mazeDimension = new Dimension(mazeWidth, mazeLength);
		setMinimumSize(mazeDimension);
		setPreferredSize(mazeDimension);
		setBackground(BACKGROUND);

		addMouseMotionListener(new MazeSelectedBoxListener(this, mazeController));
		addMouseListener(new MazeBoxClickListener(this, mazeController));

		repaint();
	}

	/**
	 * Calculate the x and y offsets to account for a change in the number of rows
	 * and columns.
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
		this.maze = maze;
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		// get state
		mazeDrawable.drawMaze(maze, graphics, xOffset, yOffset);
	}

	public void setSelected(int mouseX, int mouseY) {
		double closestDistance = Double.MAX_VALUE;

		int size = MazeDrawable.getBoxSize();
		int rowClosest = -1;
		int colClosest = -1;

		for (int row = 0; row < maze.getLength(); row++) {
			for (int col = 0; col < maze.getWidth(); col++) {
				int xCenter = xOffset + (int) ((col * SQRT_3 * size) + ((row % 2) * size * SQRT_3 / 2));
				int yCenter = yOffset + (int) ((1.5 * size * row));

				double distance = Math.sqrt(Math.pow(xCenter - mouseX, 2) + Math.pow(yCenter - mouseY, 2));

				if (distance < closestDistance) {
					rowClosest = row;
					colClosest = col;
					closestDistance = distance;
				}
			}
		}
		// Check if the mouse is in the maze
		if (closestDistance < size) {
			outOfMaze = false;
			// Store the new selected row and column in the model
			maze.setSelected(rowClosest, colClosest);
			// Repaint the maze to show the new selected box color
			repaint();
		} else {
			outOfMaze = true;
			// Store the new selected row and column in the model
			maze.setSelected(-1, -1);
			// Repaint the maze to show the new selected box color
			repaint();
		}
	}

	public void setMazeBox() {
		if (!outOfMaze) {
			int row = maze.getSelected().getRow();
			int col = maze.getSelected().getCol();

			if (row >= 0 && row < maze.getLength() && col >= 0 && col < maze.getWidth()) {
				System.out.println(row + " : " + col);
				// test the state of radio button
				if (mazeController.getBoxType() == BoxType.EMPTY) {
					maze.addEmptyBox(row, col);
				} else if (mazeController.getBoxType() == BoxType.WALL) {
					maze.addWallBox(row, col);
				} else if (mazeController.getBoxType() == BoxType.DEPARTURE) {
					if (!maze.hasDepartureBox()) {
						maze.addDepartureBox(row, col);
					}
					else {
						maze.addEmptyBox(maze.getStartVertex().getRow(), maze.getStartVertex().getCol());
						maze.addDepartureBox(row, col);
					}
				} else if (mazeController.getBoxType() == BoxType.ARRIVAL) {
					if (!maze.hasArrivalBox()) {
						maze.addArrivalBox(row, col);
					}
					else {
						maze.addEmptyBox(maze.getEndVertex().getRow(), maze.getEndVertex().getCol());
						maze.addArrivalBox(row, col);
					}
				}
				repaint();
			}
		}
	}

	/**
	 * Resizes the maze panel view based on the number of columns and the number of
	 * rows. This method is called on each iteration of generating the maze, and
	 * enables the user to be able to customize the dimensions of the maze.
	 */
	public void resize() {
		int mazeWidth = (int) (maze.getWidth() * MazeDrawable.getBoxSize() * SQRT_3 + xOffset * 1.5);
		int mazeLength = (int) (maze.getLength() * MazeDrawable.getBoxSize() * 1.5 + yOffset * 1.3);

		mazeDimension = new Dimension(mazeWidth, mazeLength);
		setMinimumSize(mazeDimension);
		setPreferredSize(mazeDimension);
	}

}
