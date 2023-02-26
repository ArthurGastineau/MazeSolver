/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;
import model.BoxType;

/**
 * @author arthur
 *
 *         An ActionListener (extending MazeActionListener) that is registered
 *         and listens for changes in the box type radio, and updates the maze
 *         accordingly on change.
 */
public class MazeBoxSelectionRadioListener extends MazeActionListener {

	public MazeBoxSelectionRadioListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		BoxType boxTypeChoice = BoxType.fromString(command); // Determines which solver type has been selected.

		if (boxTypeChoice == null) {
			return;
		}

		mazeController.setBoxType(boxTypeChoice);
	}

}
