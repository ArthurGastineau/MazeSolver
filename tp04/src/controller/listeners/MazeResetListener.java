package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 *
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Reset' button and triggers maze resetting.
 * 
 * @see MazeActionListener
 * @see MazeController
 * 
 * @author Arthur Gastineau
 */
public class MazeResetListener extends MazeActionListener {
	
	/**
	 * Constructor for {@link MazeResetListener} class
	 * 
	 * @param mazeController the {@link MazeController} object that manages the maze
	 */
	
	public MazeResetListener(MazeController mazeController) {
		super(mazeController);
	}
	
	/**
	 * Triggers the maze resetting when the 'Reset' button is clicked.
	 * 
	 * @param e the {@link ActionEvent} object representing the event triggered by the button click
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.reset();
	}

}
