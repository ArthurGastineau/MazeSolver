package controller;

import java.awt.event.ActionListener;

/**
 * 
 * An abstract ActionListener class. All ActionListeners extend this class to
 * gain access to the maze controller.
 * 
 * @author Arthur Gastineau
 */
public abstract class MazeActionListener implements ActionListener {
	protected final MazeController mazeController;

	protected MazeActionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}
