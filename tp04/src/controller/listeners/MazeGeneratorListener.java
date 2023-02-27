package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 *
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Generate' button and triggers the maze
 * generation.
 * 
 * @see MazeActionListener
 * @see MazeController
 * 
 * @author Arthur Gastineau
 */
public class MazeGeneratorListener extends MazeActionListener {
	
	/**
	 * Constructor for the {@link MazeGeneratorListener} class.
	 * 
	 * @param mazeController The {@link MazeController} instance used to generate the maze.
	 */
	
	public MazeGeneratorListener(MazeController mazeController) {
		super(mazeController);
	}
	
	/**
	 * Called when the 'Generate' button is clicked. Calls the {@link MazeController#generate()}
	 * method to generate the maze.
	 * 
	 * @param e The {@link ActionEvent} instance representing the action event that occurred.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.generate();
	}
}
