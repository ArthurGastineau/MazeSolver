/**
 * 
 */
package controller.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.MazeActionListener;
import controller.MazeController;
import model.maze.MazeReadingException;
import view.drawable.GUIPanel;

/**
 * @author arthur
 *
 */
public class MazeLoadListener extends MazeActionListener{
	public MazeLoadListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser("data/");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            mazeController.load(selectedFile.getAbsolutePath());
        }
	}
}
