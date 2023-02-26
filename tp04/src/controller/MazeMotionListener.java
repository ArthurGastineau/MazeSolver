package controller;

import java.awt.event.MouseMotionListener;

/**
 * 
 * An abstract {@link MouseMotionListener} class. All MouseMotionListener extend
 * this class to gain access to the maze controller.
 * 
 * @see MazeController
 * 
 * @author Arthur Gastineau
 */
public abstract class MazeMotionListener implements MouseMotionListener {

	/**
	 * The MazeController instance used to gain access to the maze controller.
	 */

	protected final MazeController mazeController;

	/**
	 * Constructs a new {@link MazeMotionListener} with the given
	 * {@link MazeController} instance.
	 * 
	 * @param mazeController the MazeController instance to use
	 */

	protected MazeMotionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}