/**
 * 
 */
package view.drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

import controller.MazeController;
import controller.listeners.MazeBoxClickListener;
import controller.listeners.MazeSelectedBoxListener;
import model.BoxType;
import model.maze.Maze;

/**
 * A JPanel of the maze. This panel is where the maze is drawn, and where the
 * user can pick the start and end points.
 * 
 * @author Arthur Gastineau
 */

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
				// test the state of radio button
				if (mazeController.getBoxType() == BoxType.EMPTY) {
					maze.addEmptyBox(row, col);
				} else if (mazeController.getBoxType() == BoxType.WALL) {
					maze.addWallBox(row, col);
				} else if (mazeController.getBoxType() == BoxType.DEPARTURE) {
					if (maze.hasDepartureBox()) {
						maze.addEmptyBox(maze.getStartVertex().getRow(), maze.getStartVertex().getCol());
						maze.addDepartureBox(row, col);
					} else {
						maze.addDepartureBox(row, col);
					}
				} else if (mazeController.getBoxType() == BoxType.ARRIVAL) {
					if (!maze.hasArrivalBox()) {
						maze.addEmptyBox(maze.getEndVertex().getRow(), maze.getEndVertex().getCol());
						maze.addArrivalBox(row, col);
					} else {
						maze.addArrivalBox(row, col);
					}
				}
				repaint();
			}
			//mazeController.solveMaze();
		}
	}

	/**
	 * Resizes the maze panel view based on the number of columns and the number of
	 * rows. This method is called on each iteration of generating the maze, and
	 * enables the user to be able to customize the dimensions of the maze.
	 */
	public void resize() {
		MazeDrawable.setBoxSize(MazeDrawable.DEFAULT_BOX_SIZE);

		double boxSize = MazeDrawable.getBoxSize();
		double widthFactor = boxSize * SQRT_3;
		double heightFactor = boxSize * 1.5;

		int mazeWidth = (int) (maze.getWidth() * widthFactor + xOffset * 1.5);
		int mazeLength = (int) (maze.getLength() * heightFactor + yOffset * 1.3);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int widthSize = (int) (screenSize.getWidth() - 300);
		int heightSize = (int) (screenSize.getHeight());

		// If the size needed is bigger than the screen we reduce it
		if (mazeWidth >= widthSize || mazeLength >= heightSize) {
			MazeDrawable.setBoxSize((int) (Math.min(heightSize - 100, widthSize)
					/ (Math.max(maze.getLength() * 1.5, maze.getWidth() * 1.1))));
			boxSize = MazeDrawable.getBoxSize();
			widthFactor = boxSize * SQRT_3;
			heightFactor = boxSize * 1.5;
			mazeWidth = (int) (maze.getWidth() * widthFactor + xOffset * 1.5);
			mazeLength = (int) (maze.getLength() * heightFactor + yOffset * 1.3);
		}

		// Take the smallest dimensions between the screen and the ideal panel's size
		mazeDimension = new Dimension((int) Math.min(mazeWidth, screenSize.getWidth() - 300),
				(int) Math.min(mazeLength, screenSize.getHeight()));

		setMinimumSize(mazeDimension);
		setPreferredSize(mazeDimension);
	}
}
