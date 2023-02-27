package controller.listeners;

import java.awt.event.ActionEvent;

import controller.MazeActionListener;
import controller.MazeController;
import model.BoxType;

/**
 *
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for changes in the box type radio, and updates the maze
 * accordingly on change.
 * 
 * <p>
 * This class is responsible for handling the events triggered by the selection
 * of different types of boxes, by setting the new type of box and updating the
 * maze accordingly. This class extends {@link MazeActionListener} to gain
 * access to the maze controller.
 * <p>
 * 
 * @see MazeActionListener
 * @see MazeController
 * @see String
 * @see BoxType
 * 
 * @author Arthur Gastineau
 */
public class MazeBoxSelectionRadioListener extends MazeActionListener {

	/**
	 * Constructor for {@link MazeBoxSelectionRadioListener}. Initializes the maze
	 * controller.
	 *
	 * @param mazeController the maze controller
	 */

	public MazeBoxSelectionRadioListener(MazeController mazeController) {
		super(mazeController);
	}

	/**
	 * Invoked when an action occurs. This method determines which box type was
	 * selected and updates the maze controller with the new box type.
	 *
	 * @param e the {@link ActionEvent} to be processed
	 */

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
