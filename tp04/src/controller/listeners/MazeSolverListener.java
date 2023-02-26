/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 * 
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
