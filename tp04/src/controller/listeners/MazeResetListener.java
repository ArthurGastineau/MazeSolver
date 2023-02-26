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
 * and listens for clicks from the 'Reset' button and triggers maze resetting.
 * 
 * @author Arthur Gastineau
 */
public class MazeResetListener extends MazeActionListener {
	public MazeResetListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.reset();
	}

}
