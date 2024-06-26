/**
 *
 */
package view.drawable;

import static view.drawable.DrawableHelper.addComponent;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.MazeChangeListener;
import controller.listeners.MazeCustomNumColsListener;
import controller.listeners.MazeCustomNumRowsListener;
import model.MazeConstants;

/**
 *
 * A {@link JPanel} of the two custom maze dimension spinners, the number of
 * rows spinner and the number of columns spinner.
 *
 * Provides two spinners for inputting the number of rows and columns for a
 * custom maze. The user can specify a range of valid values for the number of
 * rows and columns.
 *
 * This class is intended to be used as a component of the {@link GUIPanel}
 * class.
 *
 * @see JPanel
 * @see MazeCustomNumRowsListener
 * @see MazeCustomNumColsListener
 * @see SPinnerModel
 * @see Insets
 *
 * @author Arthur Gastineau
 */

public class MazeSizeInputs extends JPanel {

	/**
	 * Creates a new MazeSizeInputs panel with the specified listeners for the
	 * number of rows and columns spinners.
	 *
	 * @param mazeCustomNumRowsListener A MazeCustomNumRowsListener instance to
	 *                                  attach to the number of rows spinner
	 * @param mazeCustomNumColsListener A MazeCustomNumColsListener instance to
	 *                                  attach to the number of columns spinner
	 */
	public MazeSizeInputs(MazeCustomNumRowsListener mazeCustomNumRowsListener,
			MazeCustomNumColsListener mazeCustomNumColsListener) {
		setLayout(new GridBagLayout());

		JPanel numRowsPanel = new JPanel(new GridLayout(1, 0));
		String numRowsSpinnerLabel = String.format("Num Rows (%d-%d):", MazeConstants.MIN_NUM_ROWS,
				MazeConstants.MAX_NUM_ROWS);
		SpinnerModel numRowsSpinner = new SpinnerNumberModel(MazeConstants.DEFAULT_NUM_ROWS, MazeConstants.MIN_NUM_ROWS,
				MazeConstants.MAX_NUM_ROWS, 1);

		JPanel numColsPanel = new JPanel(new GridLayout(1, 0));
		String numColsSpinnerLabel = String.format("Num Cols (%d-%d):", MazeConstants.MIN_NUM_COLS,
				MazeConstants.MAX_NUM_COLS);
		SpinnerModel numColsSpinner = new SpinnerNumberModel(MazeConstants.DEFAULT_NUM_COLS, MazeConstants.MIN_NUM_COLS,
				MazeConstants.MAX_NUM_COLS, 1);

		addLabeledSpinner(numRowsPanel, numRowsSpinner, numRowsSpinnerLabel, mazeCustomNumRowsListener);
		addLabeledSpinner(numColsPanel, numColsSpinner, numColsSpinnerLabel, mazeCustomNumColsListener);

		Insets insets = new Insets(0, 0, 0, 0);
		addComponent(this, numRowsPanel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets);
		addComponent(this, numColsPanel, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets);
	}

	/**
	 * Attaches a SpinnerModel to a container.
	 *
	 * @param container          The container to which to add the spinner
	 * @param model              The SpinnerModel to add
	 * @param label              The SpinnerModel label
	 * @param mazeChangeListener The listener to attach to the spinner
	 */
	private static void addLabeledSpinner(Container container, SpinnerModel model, String label,
			MazeChangeListener mazeChangeListener) {
		JLabel spinnerLabel = new JLabel(label);
		container.add(spinnerLabel);

		JSpinner spinner = new JSpinner(model);
		spinner.addChangeListener(mazeChangeListener);
		spinnerLabel.setLabelFor(spinner);
		container.add(spinner);
	}
}
