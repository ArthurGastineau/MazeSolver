package controller.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.MazeController;
import view.drawable.MazePanel;

/**
 * 
 * A {@link MouseAdapter} that listens for clicks on a generated maze to modify
 * some of the boxes.
 * 
 * @see MazeMouseAdapter
 * @see MazeController
 * @see MazePanel
 *
 * @author Arthur Gastineau
 * 
 */
public class MazeBoxClickListener extends MouseAdapter {
	private final MazePanel mazePanel;
	private final MazeController mazeController;

	/**
	 * Constructor for the {@link MazeBoxClickListener} class. Initializes the maze
	 * controller and maze panel.
	 * 
	 * @param mazePanel      the maze panel associated with this listener
	 * @param mazeController the maze controller controlling the application
	 */

	public MazeBoxClickListener(MazePanel mazePanel, MazeController mazeController) {
		super();
		this.mazePanel = mazePanel;
		this.mazeController = mazeController;
	}

	/**
	 * 
	 * The {@code mouseReleased} method sets the state of the clicked box in
	 * the{@link MazePanel} to be modified by the {@link MazeController}.
	 * 
	 * @param mouseEvent the mouse event that triggered the method call
	 * 
	 */

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		mazePanel.setMazeBox();
	}
}
