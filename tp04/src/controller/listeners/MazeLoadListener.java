package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import controller.MazeActionListener;
import controller.MazeController;

/**
 * 
 * An {@link ActionListener} (extending {@link MazeActionListener}) that is
 * registered and listens for clicks from the 'Load' button, allow the user to
 * choose a file and triggers maze loading.
 * 
 * @see MazeActionListener
 * @see MazeController
 * @see JFileChooser
 * @see File
 * 
 * @author Arthur Gastineau
 */

public class MazeLoadListener extends MazeActionListener {

	/**
	 * Constructs a {@link MazeLoadListener} with the specified
	 * {@link MazeController}.
	 *
	 * @param mazeController the maze controller associated with this listener
	 */

	public MazeLoadListener(MazeController mazeController) {
		super(mazeController);
	}

	/**
	 * Performs the action associated with this {@link MazeLoadListener} when the
	 * 'Load' button is clicked. Allows the user to choose a file and triggers maze
	 * loading.
	 *
	 * @param e the action event that triggered this listener.
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("data/");
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			mazeController.load(selectedFile.getAbsolutePath());
		}
	}
}
