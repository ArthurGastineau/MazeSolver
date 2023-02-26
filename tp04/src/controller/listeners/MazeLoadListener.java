/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import controller.MazeActionListener;
import controller.MazeController;

/**
 * 
 * An ActionListener (extending {@link MazeActionListener}) that is registered
 * and listens for clicks from the 'Load' button, allow the user to choose a
 * file and triggers maze loading.
 * 
 * @see MazeActionListener
 * @see MazeController
 * @see JFileChooser
 * @see File
 * 
 * @author Arthur Gastineau
 */
public class MazeLoadListener extends MazeActionListener {
	public MazeLoadListener(MazeController mazeController) {
		super(mazeController);
	}

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
