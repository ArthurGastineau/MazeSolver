/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;

/**
 * @author arthur
 *
 *         An ActionListener (extending MazeActionListener) that is registered
 *         and listens for clicks from the 'Reset' button and triggers maze
 *         resetting.
 */
@SuppressWarnings("serial")
public class MazeResetListener extends MazeActionListener {
	public MazeResetListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mazeController.reset();
	}

}
