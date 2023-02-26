/**
 * 
 */
package controller.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.MazeController;
import view.drawable.MazePanel;

/**
 * A MouseAdapter that listens for clicks on a generated maze to modify some of
 * the boxes.
 *
 * @author Arthur Gastineau
 * 
 */
public class MazeBoxClickListener extends MouseAdapter {
	private final MazePanel mazePanel;
	private final MazeController mazeController;

	public MazeBoxClickListener(MazePanel mazePanel, MazeController mazeController) {
		super();
		this.mazePanel = mazePanel;
		this.mazeController = mazeController;
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		mazePanel.setMazeBox();
	}
}
