/**
 * 
 */
package controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

/**
 * @author arthur
 * 
 *         An abstract ActionListener class. All ActionListeners extend this
 *         class to gain access to the maze controller.
 */
public abstract class MazeActionListener implements ActionListener {
	protected final MazeController mazeController;

	protected MazeActionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}
