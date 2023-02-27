package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import controller.MazeActionListener;
import controller.MazeController;

/**
 * 
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Save' button, allow the user to choose a
 * file and triggers maze saving.
 *
 * @see MazeActionListener
 * @see MazeController
 * @see JFileChooser
 * @see File
 * 
 * 
 * @author Arthur Gastineau
 */
public class MazeSaveListener extends MazeActionListener {
	
	/**
	 * Constructs a new {@link MazeSaveListener} with the given @link mazeController}.
	 *
	 * @param mazeController the {@link MazeController} to associate with this listener
	 */
	
	public MazeSaveListener(MazeController mazeController) {
		super(mazeController);
	}
	
	/**
	 * Called when the 'Save' button is clicked. Displays a file chooser dialog
	 * and allows the user to choose a file to save the maze to. If a file is
	 * selected, the maze is saved to the selected file using the {@link mazeController}.
	 *
	 * @param e the {@link ActionEvent} associated with the 'Save' button click
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("data/");
		int returnVal = fileChooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File fileToSave = new File(fileChooser.getSelectedFile() + ".maze");
			mazeController.save(fileToSave.getAbsolutePath());
		}
	}
}
