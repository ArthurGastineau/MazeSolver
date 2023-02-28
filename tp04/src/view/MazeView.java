package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.MazeController;
import controller.listeners.WindowResizeListener;
import model.maze.Maze;
import view.drawable.GUIPanel;
import view.drawable.InstructionsPanel;
import view.drawable.MazePanel;

/**
 *
 * The view of the maze (i.e. the view in the MVC design pattern). This is a
 * {@link JFrame} that contains both the {@link MazePanel} (the JPanel
 * containing the maze) and the {@link GUIPanel} (the panel containing all the
 * UI elements). This class also acts as an intermediary between the controller
 * and between each of the its child panels.
 *
 * @see Maze
 * @see MazeController
 * @see GUIPanel
 * @see InstructionsPanel
 * @see Insets
 * @see GridBagConstraints
 * @see WindowResizeListener
 *
 * @author Arthur Gastineau
 */

public class MazeView extends JFrame {

	/**
	 * The controller for the maze.
	 */
	private final MazeController mazeController;

	/**
	 * The panel containing the maze.
	 */
	private final MazePanel mazePanel;

	/**
	 * The panel containing the UI elements.
	 */
	private final GUIPanel guiPanel;

	/**
	 * The panel containing the instructions for the maze.
	 */
	private final InstructionsPanel instructionsPanel;

	/**
	 * The listener for window resize events.
	 */
	private final WindowResizeListener windowResizeListener;

	/**
	 * Creates a new {@code MazeView} with the given {@link Maze} and
	 * {@link MazeController}.
	 *
	 * @param maze           The maze to be displayed.
	 * @param mazeController The controller for the maze.
	 */
	public MazeView(Maze maze, MazeController mazeController) {
		super("Maze Solver - Arthur Gastineau");
		this.mazeController = mazeController;
		this.mazePanel = new MazePanel(maze, mazeController);
		this.guiPanel = new GUIPanel(mazeController);
		this.instructionsPanel = new InstructionsPanel();
		this.windowResizeListener = new WindowResizeListener(mazeController, this);

		getRootPane().addComponentListener(windowResizeListener);

		initDisplay();
	}

	/**
	 * Initializes the display of the maze view.
	 */
	private void initDisplay() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());

		Insets insets = new Insets(5, 5, 5, 5);

		addComponent(mazePanel, 0, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets);
		addComponent(guiPanel, 2, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets);
		addComponent(instructionsPanel, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets);

		setInstructions();

		setVisible(true);
		pack();

	}

	/**
	 * Sets the maze instructions (based on maze state) and adjusts the view size to
	 * account for changes in text.
	 */
	public void setInstructions() {
		instructionsPanel.setInstructions(mazeController.getState().getInstruction());
		pack();
	}

	/**
	 * Resizes the maze panel to account for a change in the number of rows and
	 * columns.
	 */
	public void resize() {
		resizeWindow();
		pack();
	}

	/**
	 * Repaints the maze with the given Maze object.
	 *
	 * @param maze the Maze object to be used to repaint the maze
	 */
	public void repaintMaze(Maze maze) {
		mazePanel.repaintMaze(maze);
	}

	/**
	 * Add a component to the maze view
	 *
	 * @param component  The component to add to the view
	 * @param gridx      The component's x coordinate in the GridBayLayout
	 * @param gridy      The component's y coordinate in the GridBayLayout
	 * @param gridwidth  The GridBayLayout grid width
	 * @param gridheight The GridBayLayout grid height
	 * @param anchor     The component's anchor in the GridBayLayout
	 * @param fill       The component's fill in the GridBayLayout
	 */
	private void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor,
			int fill, Insets insets) {

		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1, 1, anchor, fill, insets,
				0, 0);

		if (fill == GridBagConstraints.BOTH) {
			gbc.weighty = 0;
		}

		getContentPane().add(component, gbc);
	}

	/**
	 * Resizes the maze panel to fit the size of the window, taking into account the
	 * size of the GUI panel and instructions panel.
	 */
	public void resizeWindow() {
		mazePanel.resize(instructionsPanel.getSize().height, guiPanel.getSize().width, getSize().height,
				getSize().width);
	}

}
