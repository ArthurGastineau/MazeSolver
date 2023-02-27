package controller.listeners;

import java.awt.event.MouseEvent;

import controller.MazeController;
import controller.MazeMotionListener;
import view.drawable.MazePanel;

/**
 *
 * An ActionListener (extending {@link MazeMotionListener}) that is registered
 * and listens for mouse movements, allow the user to select an hexagon of the
 * maze.
 *
 * @see MazeMotionListener
 * @see MazeController
 * @see MazePanel
 *
 * @author Arthur Gastineau
 */
public class MazeSelectedBoxListener extends MazeMotionListener {
	private final MazePanel mazePanel;
	
	/**
	 * Constructor for the {@link MazeSelectedBoxListener} class.
	 * 
	 * @param mazePanel the {@link MazePanel} associated with the listener
	 * @param mazeController the {@link MazeController} associated with the listener
	 */

	public MazeSelectedBoxListener(MazePanel mazePanel, MazeController mazeController) {
		super(mazeController);
		this.mazePanel = mazePanel;
	}
	
	/**
	 * Method called when the mouse is moved. Updates the selected hexagon of the maze.
	 * 
	 * @param mouseEvent the {@link MouseEvent} associated with the mouse movement
	 */

	public void mouseMoved(MouseEvent mouseEvent) {
		mazePanel.setSelected(mouseEvent.getX(), mouseEvent.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Not implemented
	}
}
