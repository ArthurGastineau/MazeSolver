package view.drawable;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

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
public class InstructionsPanel extends JPanel {

	// The JTextArea used to display the instructions
	private final JTextArea instructions;

	/**
	 * Creates a new InstructionsPanel with an etched border and a margin of 5
	 * pixels.
	 *
	 * Initializes the JTextArea to be used to display the instructions and sets its
	 * formatting.
	 *
	 * The panel is not focusable.
	 */
	public InstructionsPanel() {
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

}
