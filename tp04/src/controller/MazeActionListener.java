package controller;

import java.awt.event.ActionListener;

/**
 * An abstract {@link ActionListener} class that extends this class to gain
 * access to the maze controller.
 * 
 * <p>
 * This class serves as a base class for all {@link ActionListener} classes that
 * need access to the maze controller. It contains a reference to the maze
 * controller that can be accessed by subclasses.
 * </p>
 * 
 * @see MazeController
 * 
 * @author Arthur Gastineau
 */

public abstract class MazeActionListener implements ActionListener {

	/**
	 * The maze controller instance to use.
	 */

	protected final MazeController mazeController;

	/**
	 * Constructs a new {@link MazeActionListener} with the given
	 * {@link MazeController} instance.
	 * 
	 * @param mazeController the maze controller instance associated with this
	 *                       listener
	 */

	protected MazeActionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}
