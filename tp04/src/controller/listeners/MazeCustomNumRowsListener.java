package controller.listeners;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import controller.MazeChangeListener;
import controller.MazeController;

/**
 *
 * A ChangeListener (extending {@link MazeChangeListener}) that listens for
 * changes in the number of rows spinner and sets the number of rows to be used
 * in the next maze generation accordingly.
 *
 * @see MazeChangeListener
 * @see MazeController
 * @see JSpinner
 * @see SpinnerNumberModel
 * 
 * @author Arthur Gastineau
 */
public class MazeCustomNumRowsListener extends MazeChangeListener {
	
	/**
     * Constructs a {@link MazeCustomNumRowsListener} with the given {@link MazeController}.
     * 
     * @param mazeController the {@link MazeController} to use
     */
	
	public MazeCustomNumRowsListener(MazeController mazeController) {
		super(mazeController);
	}
	
	/**
     * Listens for state changes in the JSpinner associated with the number of rows,
     * and sets the number of rows to be used in the next maze generation accordingly.
     * 
     * @param e the {@link ChangeEvent} representing the state change
     */

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner rowsSpinner = (JSpinner) e.getSource();
		SpinnerNumberModel rowsSpinnerModel = (SpinnerNumberModel) (rowsSpinner.getModel());
		int numRows = (int) rowsSpinnerModel.getNumber();
		mazeController.setMazeNumRows(numRows);
	}
}
