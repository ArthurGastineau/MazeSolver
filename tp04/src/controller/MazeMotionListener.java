package controller;

import java.awt.event.MouseMotionListener;

import javax.swing.event.ChangeListener;

/**
 * 
 * An abstract {@link MouseMotionListener} class. All
 * {@link MouseMotionListener} extend this class to gain access to the maze
 * controller.
 * 
 * @see MazeController
 * @see MouseMotionListener
 * 
 * @author Arthur Gastineau
 */
public abstract class MazeMotionListener implements MouseMotionListener{

	/**
	 * The {@link MazeController} instance used to gain access to the maze
	 * controller.
	 */

	protected final MazeController mazeController;

	/**
	 * Constructs a new {@link MazeMotionListener} with the given
	 * {@link MazeController} instance.
	 * 
	 * @param mazeController the maze controller instance associated with this
	 *                       listener
	 */

	protected MazeMotionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}