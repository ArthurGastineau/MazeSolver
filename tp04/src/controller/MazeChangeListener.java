package controller;

import javax.swing.event.ChangeListener;

/**
 *
 * An abstract {@link ChangeListener} class. All {@link ChangeListeners} extend
 * this class to gain access to the maze controller.
 *
 * @see MazeController
 *
 * @author Arthur Gastineau
 */
public abstract class MazeChangeListener implements ChangeListener {

	/**
	 * The maze controller instance to use.
	 */

	protected final MazeController mazeController;

	/**
	 * Constructs a new {@code MazeChangeListener} with the specified
	 * {@code MazeController}.
	 *
	 * @param mazeController the maze controller instance associated with this
	 *                       listener
	 */

	protected MazeChangeListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}
