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
 * GUIPanel is a {@link JPanel} used to display and manage the GUI elements and
 * layout of the application. It is initialized with a {@link MazeController}
 * object to provide event listeners for the GUI elements.
 * <p>
 * The GUIPanel has a {@link GridBagLayout} and contains various GUI elements
 * such as buttons, input fields, and radio buttons to manipulate the maze
 * display and functionality.
 * <p>
 * The class provides a public constructor that initializes the panel with a
 * MazeController object and a private initGUIPanel method to create and add the
 * GUI elements to the panel. It also provides a private initButton method to
 * initialize and add a button to the panel and a private actionPerformed method
 * to handle button clicks.
 * <p>
 * This class also uses the {@link DrawableHelper} class, a helper class
 * available to all classes in the view, to assist in adding components to the
 * panel.
 * <p>
 * This class implements the {@link ActionListener} interface to handle button
 * clicks.
 * 
 * @see MazeController
 * @see DrawableHelper
 * @see ActionListener
 * @see JPanel
 * @see JButton
 * @see MazeSizeInputs
 * @see BoxTypeRadioPanel
 * @see GridBagConstraints
 * @see GridBagLayout
 * 
 * @author Arthur Gastineau
 */

public class GUIPanel extends JPanel implements ActionListener {
	private final MazeController mazeController;

	/**
	 * Constructor for GUIPanel.
	 * 
	 * @param mazeController the MazeController object controlling the application
	 */

	public GUIPanel(MazeController mazeController) {
		this.mazeController = mazeController;

		initGUIPanel();

	}

	/**
	 * Initializes the GUIPanel.
	 */
	private void initGUIPanel() {

		setLayout(new GridBagLayout());

		Insets insets = new Insets(5, 0, 0, 0);

		// Load Maze Button
		initButton(new JButton("Load"), "load", 0, mazeController.getMazeLoadListener(), insets);

		// Save Maze Button
		initButton(new JButton("Save"), "save", 1, mazeController.getMazeSaveListener(), insets);

		// Maze Size Inputs
		JPanel customMazeSizeInputs = new MazeSizeInputs(mazeController.getMazeCustomNumRowsListener(),
				mazeController.getMazeCustomNumColsListener());
		addComponent(this, customMazeSizeInputs, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				insets);

		// Generate Maze Button
		initButton(new JButton("Generate"), "generate", 3, mazeController.getMazeGeneratorListener(), insets);

		// Box Selection Radio
		JPanel boxSelectionRadio = new BoxTypeRadioPanel(mazeController.getMazeBoxSelectionRadioListener(),
				mazeController.getBoxType());
		addComponent(this, boxSelectionRadio, 0, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				insets);

		// Solve Maze Button
		initButton(new JButton("Solve"), "solve", 5, mazeController.getMazeSolverListener(), insets);

		// Exit Button
		initButton(new JButton("Exit"), "exit", 6, this, insets);

		addComponent(this, new MazeLegendPanel(), 0, 7, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				insets);

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

		if ("exit".equals(buttonIntention)) {
			System.exit(0);
		}
	}
}
