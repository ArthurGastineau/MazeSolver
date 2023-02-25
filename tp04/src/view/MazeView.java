package view;

import model.maze.Maze;
import view.drawable.GUIPanel;
import view.drawable.MazePanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.MazeController;

/**
 * @author arthur
 *
 * The view of the maze (i.e. the view in the MVC design pattern). This is a JPanel that contains both the maze panel
 * (the JPanel containing the maze) and the GUI panel (the panel containing all the UI elements). This class also acts
 * as an intermediary between the controller and between each of the its child panels.
 */
@SuppressWarnings("serial")
public class MazeView extends JFrame{
	private final MazeController mazeController;
	private final MazePanel mazePanel;
	private final GUIPanel guiPanel;
	
	public MazeView(Maze maze, MazeController mazeController) {
		super("Maze Solver - Arthur Gastineau");
		this.mazeController = mazeController;
		this.mazePanel = new MazePanel(maze, mazeController);
		this.guiPanel = new GUIPanel(mazeController);

		initDisplay();
	}
	
	private void initDisplay() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridBagLayout());

		Insets insets = new Insets(5, 5, 5, 5);

		addComponent(mazePanel, 0, 0, 2, 1, GridBagConstraints.CENTER,
				GridBagConstraints.BOTH, insets);
		addComponent(guiPanel, 2, 0, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, insets);

		setVisible(true);
		pack();
	}
	
	
	/**
	 * Resizes the maze panel to account for a change in the number of rows and columns.
	 */
	public void resize() {
		mazePanel.resize();
		pack();
		mazePanel.setOffset(mazePanel.getWidth(), mazePanel.getHeight());
		mazePanel.repaint();
	}
	
	public void repaintMaze(Maze maze) {
		mazePanel.repaintMaze(maze);
	}
	
	/**
	 * Resets the view after the reset action is triggered.
	 */
	public void resetView() {
		mazePanel.repaint();
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

		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
				anchor, fill, insets, 0, 0);
		getContentPane().add(component, gbc);
	}

}
