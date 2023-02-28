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
	 * @param mazeController the maze controler instance associated with this
	 *                       listener
	 */

	public MazeGeneratorListener(MazeController mazeController) {
		super(mazeController);
	}

	/**
	 * Called when the 'Generate' button is clicked. Allows the user to generate a
	 * new maze with custom size
	 *
	 * @param e the action event instance representing the action event that
	 *          occurred.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.generate();
	}
}
