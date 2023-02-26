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
 * and listens for clicks from the 'Generate' button and triggers the maze
 * generation.
 * 
 * @see MazeActionListener
 * @see MazeController
 * 
 * @author Arthur Gastineau
 */
public class MazeGeneratorListener extends MazeActionListener {
	public MazeGeneratorListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.generate();
	}
}
