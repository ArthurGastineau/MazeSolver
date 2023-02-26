/**
 * 
 */
package view.drawable;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MazeController;

import static view.drawable.DrawableHelper.addComponent;

/**
 * @author arthur
 *
 */

@SuppressWarnings("serial")
public class GUIPanel extends JPanel implements ActionListener {
	private final MazeController mazeController;

	public GUIPanel(MazeController mazeController) {
		this.mazeController = mazeController;
		initGUIPanel();
	}

	private void initGUIPanel() {
		setLayout(new GridBagLayout());

		Insets insets = new Insets(5, 0, 0, 0);
		
		// Solve Method Radio
		JPanel boxSelectionRadio = new BoxTypeRadioPanel(mazeController.getMazeBoxSelectionRadioListener(),
				mazeController.getBoxType());
		addComponent(this, boxSelectionRadio, 0, 2, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets);
		
		//Load Maze Button
		initButton(new JButton("Load"), "load", 3, mazeController.getMazeLoadListener(), insets);

		// Solve Maze Button
		initButton(new JButton("Solve"), "solve", 4, mazeController.getMazeSolverListener(), insets);

		// Reset Maze Button
		initButton(new JButton("Reset"), "reset", 5, mazeController.getMazeResetListener(), insets);

		// Exit Button
		initButton(new JButton("Exit"), "exit", 6, this, insets);
	}

	/**
	 * Initializes a button and adds it to the GUI panel.
	 *
	 * @param button              New button to add to the GUI panel
	 * @param buttonActionCommand The new button's ActionCommand
	 * @param gridy               The button's y coordinate in the GridBayLayout
	 * @param actionListener      The actionListener to attach to the button
	 * @param insets              The button's insets
	 */
	private void initButton(JButton button, String buttonActionCommand, int gridy, ActionListener actionListener,
			Insets insets) {
		button.setActionCommand(buttonActionCommand);
		button.addActionListener(actionListener);
		addComponent(this, button, 0, gridy, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets);
	}

	/**
	 * Override of the actionPerformed action. The only button that is listened to
	 * and handled directly by the GUI panel is the 'Exit' button, which terminates
	 * the application.
	 *
	 * @param mazeGuiButtonClick A button click ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent mazeGuiButtonClick) {
		String buttonIntention = mazeGuiButtonClick.getActionCommand();

		if (buttonIntention.equals("exit")) {
			System.exit(0);
		}
	}
}
