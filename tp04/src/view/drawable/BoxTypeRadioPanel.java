package view.drawable;

import static view.drawable.DrawableHelper.addComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.listeners.MazeBoxSelectionRadioListener;
import model.BoxType;

/**
 * A {@link JPanel} that displays a set of radio buttons representing the
 * different types of MazeBox that can be selected in the maze editor.
 *
 * @see MazeBoxSelectionRadioListener
 * @see BoxType
 * @see DrawableHelper
 * @see JPanel
 * @see ButtonGroup
 * @see JRadioButton
 * @see GridBagLayout
 * @see GridBagConstraints
 * @see Insets
 * @see Dimension
 *
 * @author Arthur Gastineau
 */

public class BoxTypeRadioPanel extends JPanel {

	/**
	 * Dictionary with the color corresponding to the type of box for the display
	 */
	private static final Map<BoxType, Color> BOX_TYPE_TO_COLOR;

	static {
		BOX_TYPE_TO_COLOR = new HashMap<>();
		BOX_TYPE_TO_COLOR.put(BoxType.EMPTY, Color.WHITE);
		BOX_TYPE_TO_COLOR.put(BoxType.WALL, Color.BLACK);
		BOX_TYPE_TO_COLOR.put(BoxType.DEPARTURE, Color.BLUE);
		BOX_TYPE_TO_COLOR.put(BoxType.ARRIVAL, Color.RED);
	}

	/**
	 * Creates a new BoxTypeRadioPanel object with radio buttons for all possible
	 * BoxType values.
	 *
	 * @param boxSelectionRadioListener the listener to notify when the user selects
	 *                                  a new box type
	 * @param mazeBoxType               the initial box type to select
	 */
	public BoxTypeRadioPanel(MazeBoxSelectionRadioListener boxSelectionRadioListener, BoxType mazeBoxType) {
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder("Box Selector"));

		Box boxTypeRadioBox = Box.createVerticalBox();
		ButtonGroup boxTypeRadioButtonGroup = new ButtonGroup();

		/*
		 * Iterates through all the box types and creates a corresponding radio option
		 * for the user to pick
		 */
		for (BoxType boxType : BoxType.values()) {
			JRadioButton boxTypeOption = new JRadioButton(boxType.getName());
			boxTypeOption.addActionListener(boxSelectionRadioListener);

			// Set the text color based on the box type
			boxTypeOption.setForeground(BOX_TYPE_TO_COLOR.get(boxType));

			if (boxType == mazeBoxType) {
				boxTypeOption.setSelected(true);
				setForeground(Color.RED);
			}

			boxTypeRadioBox.add(boxTypeOption);
			boxTypeRadioButtonGroup.add(boxTypeOption);
		}

		Insets insets = new Insets(2, 2, 2, 2);
		addComponent(this, boxTypeRadioBox, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, insets);

		Dimension guiDimension = new Dimension(125, 125);
		setMinimumSize(guiDimension);
		setPreferredSize(guiDimension);
	}
}
