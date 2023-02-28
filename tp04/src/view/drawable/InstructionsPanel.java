package view.drawable;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.MazeController;

/**
 *
 * A {@link JPanel} of the instructions for the maze at each stage to give the
 * user guidance on what to do and what features are available to them.
 *
 * @see JPanel
 * @see JTextArea
 * @see BorderLayout
 * @see Border
 * @see EtchedBorder
 *
 * @author Arthur Gastineau
 */
public class InstructionsPanel extends JPanel implements ChangeListener {

	// The JTextArea used to display the instructions
	private final JTextArea instructions;

	private final MazeController mazeController;

	/**
	 * Creates a new InstructionsPanel with an etched border and a margin of 5
	 * pixels.
	 *
	 * Initializes the JTextArea to be used to display the instructions and sets its
	 * formatting.
	 *
	 * The panel is not focusable.
	 * 
	 * @param mazeController the MazeController object controlling the application
	 */
	public InstructionsPanel(MazeController mazeController) {

		this.mazeController = mazeController;
		this.mazeController.addObserver(this);

		setLayout(new BorderLayout());
		Border margin = new EmptyBorder(5, 5, 5, 5);
		setBorder(new CompoundBorder(new EtchedBorder(), margin));

		this.instructions = new JTextArea();
		initInstructionTextArea();
		add(instructions);

		setVisible(true);
	}

	/**
	 * Initializes the JTextArea used to display the instructions.
	 *
	 * The JTextArea has a gray background and is not editable, not focusable, and
	 * opaque. It has a wrap style word and a line wrap to automatically wrap text.
	 */
	private void initInstructionTextArea() {
		instructions.setBackground(new Color(238, 238, 238));
		instructions.setEditable(false);
		instructions.setCursor(null);
		instructions.setOpaque(false);
		instructions.setFocusable(false);
		instructions.setWrapStyleWord(true);
		instructions.setLineWrap(true);
	}

	/**
	 * Sets the instructions to be displayed in the JTextArea.
	 *
	 * @param instruction The instructions to be displayed
	 */
	public void setInstructions(String instruction) {
		instructions.setText(instruction);
	}

	/**
	 * Called when the state of the Model is changed.
	 * 
	 * Notifies the panel to change the instructions to display
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		notifyForUpdate();
	}

	/**
	 * The panel to change the instructions to display
	 */
	public void notifyForUpdate() {
		setInstructions(mazeController.getState().getInstruction());
	}

}
