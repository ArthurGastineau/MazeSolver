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
 * @author arthur
 *
 */
public class MazeSaveListener extends MazeActionListener {
	public MazeSaveListener(MazeController mazeController) {
		super(mazeController);
	}

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
