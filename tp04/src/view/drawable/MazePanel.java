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
import model.MazeState;
import model.maze.Maze;

/**
 * A {@link JPanel} of the maze. This panel is where the maze is drawn, and
 * where the user can pick the start and end points.
 * 
 * @see Color
 * @see MazeController
 * @see MazeDrawable
 * @see Maze
 * @see Dimension
 * 
 * @author Arthur Gastineau
 */

public class MazePanel extends JPanel {

	/**
	 * The background color of the maze panel.
	 */
	private static final Color BACKGROUND = new Color(0, 100, 0);

	/**
	 * The maze controller for the maze.
	 */
	private final MazeController mazeController;

	/**
	 * The maze drawable object used to draw the maze.
	 */
	private final MazeDrawable mazeDrawable;

	/**
	 * The maze model for the maze.
	 */
	private Maze maze;

	/**
	 * The x and y offset of the maze.
	 */
	private int yOffset, xOffset;

	/**
	 * The dimension of the maze panel.
	 */
	private Dimension mazeDimension;

	/**
	 * Flag indicating whether the mouse is out of the maze.
	 */
	private boolean outOfMaze = true;

	/**
	 * The square root of 3.
	 */
	private static final double SQRT_3 = Math.sqrt(3);

	/**
	 * Constructs a MazePanel with the given maze model and maze controller.
	 * 
	 * @param maze           the maze model for the maze
	 * @param mazeController the maze controller for the maze
	 */
	public MazePanel(Maze maze, MazeController mazeController) {
		this.maze = maze;
		this.mazeController = mazeController;
		this.mazeDrawable = new MazeDrawable();
		this.yOffset = this.xOffset = 50;
		this.mazeDimension = new Dimension();

		initMazePanel();
	}

	/**
	 * Initializes the MazePanel.
	 */
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
	 * Repaints the maze with the given maze model.
	 * 
	 * @param maze the maze model to use
	 */
	public void repaintMaze(Maze maze) {
		this.maze = maze;
		repaint();
	}

	/**
	 * Draws the maze on the panel.
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		// get state
		mazeDrawable.drawMaze(maze, graphics, xOffset, yOffset);
	}

	/**
	 * Sets the selected box based on the given mouse coordinates.
	 * 
	 * @param mouseX the x coordinate of the mouse
	 * @param mouseY the y coordinate of the mouse
	 */
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

	/**
	 * Sets the type of box in the maze grid based on the current state of the radio
	 * button in the GUI. If the user has not clicked outside of the maze, the
	 * method gets the selected row and column, checks if they are within the maze
	 * bounds, and adds the appropriate type of box to the maze (empty, wall,
	 * departure or arrival). The method then calls repaint() to update the view.
	 */
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
			// if we have now a different shortest path display it
			if (mazeController.getState() == MazeState.SOLVED) {
				mazeController.solveMaze();
			}
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

		repaint();
	}
}
