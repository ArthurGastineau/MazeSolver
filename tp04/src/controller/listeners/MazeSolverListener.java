package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 *
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Solve' button, and triggers the maze solve.
 *
 * @see MazeActionListener
 * @see MazeController
 *
 * @author Arthur Gastineau
 */
public class MazeSolverListener extends MazeActionListener {

	/**
	 * Constructs a new {@link MazeSolverListener} with the given
	 * {@link MazeController}.
	 *
	 * @param mazeController the maze controller associated with this listener
	 */

	public MazeSolverListener(MazeController mazeController) {
		super(mazeController);
	}

	/**
	 * Method that is called when the 'Solve' button is clicked. Triggers the maze
	 * to be solved.
	 *
	 * @param event the action event that triggered this method call
	 */

	@Override
	public void actionPerformed(ActionEvent event) {
		mazeController.solveMaze();
	}
}
