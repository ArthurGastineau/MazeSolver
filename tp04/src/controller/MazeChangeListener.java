/**
 * 
 */
package controller;

import javax.swing.event.ChangeListener;

/**
 * @author arthur
 *
 *         An abstract ChangeListener class. All ChangeListeners extend this
 *         class to gain access to the maze controller.
 */
public abstract class MazeChangeListener implements ChangeListener {
	protected final MazeController mazeController;

	protected MazeChangeListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}
