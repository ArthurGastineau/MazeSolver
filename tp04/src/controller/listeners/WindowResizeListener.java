/**
 * 
 */
package controller.listeners;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import controller.MazeController;
import view.MazeView;

/**
 * @author arthur
 *
 */
public class WindowResizeListener extends ComponentAdapter {

	private final MazeController mazeController;
	private final MazeView mazeView;

	public WindowResizeListener(MazeController mazeController, MazeView mazeView) {
		this.mazeController = mazeController;
		this.mazeView = mazeView;
	}

	public void componentResized(ComponentEvent e) {
		mazeView.resizeWindow();
	}
}
