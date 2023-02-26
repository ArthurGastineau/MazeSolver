/**
 * 
 */
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
	public MazeCustomNumRowsListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner rowsSpinner = (JSpinner) e.getSource();
		SpinnerNumberModel rowsSpinnerModel = (SpinnerNumberModel) (rowsSpinner.getModel());
		int numRows = (int) rowsSpinnerModel.getNumber();
		mazeController.setMazeNumRows(numRows);
	}
}
