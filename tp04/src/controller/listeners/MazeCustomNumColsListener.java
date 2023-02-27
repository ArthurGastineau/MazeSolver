package controller.listeners;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import controller.MazeChangeListener;
import controller.MazeController;

/**
 * 
 * A ChangeListener (extending {@link MazeChangeListener}) that listens for
 * changes in the number of columns spinner and sets the number of columns to be
 * used in the next maze generation accordingly.
 * 
 * @see MazeChangeListener
 * @see MazeController
 * @see JSpinner
 * @see SpinnerNumberModel
 * 
 * @author Arthur Gastineau
 */
public class MazeCustomNumColsListener extends MazeChangeListener {
	
	/**
     * Constructs a new {@link MazeCustomNumColsListener} with the given {@link MazeController}.
     * 
     * @param mazeController the {@link MazeController} to use
     */
	
	public MazeCustomNumColsListener(MazeController mazeController) {
		super(mazeController);
	}
	
	/**
     * Listens for changes in the number of columns spinner and sets the number of columns to be used in the 
     * next maze generation accordingly.
     * 
     * @param e the {@link ChangeEvent} to handle
     */

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner colsSpinner = (JSpinner) e.getSource();
		SpinnerNumberModel colsSpinnerModel = (SpinnerNumberModel) (colsSpinner.getModel());
		int numCols = (int) colsSpinnerModel.getNumber();
		mazeController.setMazeNumCols(numCols);
	}
}
