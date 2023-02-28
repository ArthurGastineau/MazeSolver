package controller.listeners;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import controller.MazeController;
import view.MazeView;

/**
 * This class implements a listener for resizing the window of a MazeView. When
 * the window is resized, it calls the resizeWindow method of the associated
 * MazeView object.
 *
 * @see ComponentAdapter
 * @see ComponentEvent
 * @see MazeView
 * @see MazeController
 *
 * @author Arthur Gastineau
 */
public class WindowResizeListener extends ComponentAdapter {

	private final MazeController mazeController;
	private final MazeView mazeView;

	/**
	 * Creates a new WindowResizeListener with the given MazeController and
	 * MazeView.
	 *
	 * @param mazeController The MazeController instance to be associated with the
	 *                       MazeView.
	 * @param mazeView       The MazeView instance to be associated with this
	 *                       listener.
	 */
	public WindowResizeListener(MazeController mazeController, MazeView mazeView) {
		this.mazeController = mazeController;
		this.mazeView = mazeView;
	}

	/**
	 * Overrides the method in ComponentAdapter to call the notifyForUpdate method
	 * of the associated MazeView when the window is resized.
	 *
	 * @param e The ComponentEvent that triggered the listener.
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		mazeView.notifyForUpdate();
	}
}
