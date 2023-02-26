/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 *
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Solve' button, and triggers the maze solve.
 *
 * @author Arthur Gastineau
 */
public class MazeSolverListener extends MazeActionListener {
	public MazeSolverListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		mazeController.solveMaze();
	}
}
