/**
 * 
 */
package controller.listeners;

import java.awt.event.MouseEvent;

import controller.MazeController;
import controller.MazeMotionListener;
import view.drawable.MazePanel;

/**
 * @author arthur
 *
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
