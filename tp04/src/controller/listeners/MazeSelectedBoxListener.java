/**
 * 
 */
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
 * @author Arthur Gastineau
 */
public class MazeSelectedBoxListener extends MazeMotionListener {
	private final MazePanel mazePanel;

	public MazeSelectedBoxListener(MazePanel mazePanel, MazeController mazeController) {
		super(mazeController);
		this.mazePanel = mazePanel;
	}

	public void mouseMoved(MouseEvent mouseEvent) {
		mazePanel.setSelected(mouseEvent.getX(), mouseEvent.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
