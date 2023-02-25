/**
 * 
 */
package controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

/**
 * @author arthur
 * 
 *         An abstract MouseMotionListener class. All MouseMotionListener extend
 *         this class to gain access to the maze controller.
 */
public abstract class MazeMotionListener implements MouseMotionListener {
	protected final MazeController mazeController;

	protected MazeMotionListener(MazeController mazeController) {
		this.mazeController = mazeController;
	}
}