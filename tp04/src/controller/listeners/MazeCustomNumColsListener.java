package controller.listeners;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;

import controller.MazeChangeListener;
import controller.MazeController;

/*
*
* 		A ChangeListener (extending MazeChangeListener) that listens for changes in the number of columns spinner and sets
* 		the number of columns to be used in the next maze generation accordingly.
* 		
* 		@author Arthur Gastineau 
*/
public class MazeCustomNumColsListener extends MazeChangeListener {
	public MazeCustomNumColsListener(MazeController mazeController) {
		super(mazeController);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner colsSpinner = (JSpinner) e.getSource();
		SpinnerNumberModel colsSpinnerModel = (SpinnerNumberModel) (colsSpinner.getModel());
		int numCols = (int) colsSpinnerModel.getNumber();
		mazeController.setMazeNumCols(numCols);
	}
}
