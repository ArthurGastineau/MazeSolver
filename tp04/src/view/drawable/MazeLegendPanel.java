package view.drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * @author Arthur Gastineau
 *
 * 
 */
public class MazeLegendPanel extends JPanel {
	private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.WHITE, Color.BLACK, Color.YELLOW, Color.GRAY };
	private static final String[] STRINGS = { "Start", "End", "Empty", "Wall", "Path", "Selected" };
	private static final Color[] STRINGS_COLOR = { Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK,
			Color.WHITE };
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final double SQRT_3 = Math.sqrt(3);
	private static final int HEXAGON_SIZE = 30;
	private static final int PADDING = 10;
	private static final int NUM_HEXAGONS = COLORS.length;

	public MazeLegendPanel() {
		// Set preferred size based on the number of hexagons to be displayed
		int width = (int) (NUM_HEXAGONS * (SQRT_3 * HEXAGON_SIZE + PADDING) + PADDING);
		int height = 4 * HEXAGON_SIZE + PADDING;
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Set background color
		setBackground(BACKGROUND_COLOR);

		// Define hexagon position
		int xOffset = PADDING + HEXAGON_SIZE;
		int yOffset = getHeight() / 2;

		Font font = new Font("Arial", Font.PLAIN, 12);
		FontMetrics metrics = g.getFontMetrics(font);

		// Draw each hexagon with its corresponding color
		for (int i = 0; i < NUM_HEXAGONS; i++) {
			g.setColor(COLORS[i]);
			int[] xPoints = { xOffset, (int) ((xOffset + (SQRT_3 / 2) * HEXAGON_SIZE)),
					(int) ((xOffset + SQRT_3 / 2 * HEXAGON_SIZE)), xOffset,
					(int) ((xOffset - SQRT_3 / 2 * HEXAGON_SIZE)), (int) ((xOffset - (SQRT_3 / 2) * HEXAGON_SIZE)) };
			int[] yPoints = { HEXAGON_SIZE + yOffset, (int) (0.5 * HEXAGON_SIZE + yOffset),
					(int) (yOffset - 0.5 * HEXAGON_SIZE), yOffset - 1 * HEXAGON_SIZE,
					(int) (yOffset - 0.5 * HEXAGON_SIZE), (int) (yOffset + 0.5 * HEXAGON_SIZE) };

			g.fillPolygon(xPoints, yPoints, 6);
			g.setColor(Color.BLACK);
			g.drawPolygon(xPoints, yPoints, 6);

			// Draw legend text
			g.setColor(STRINGS_COLOR[i]);
			g.setFont(font);
			g.drawString(STRINGS[i], xOffset - metrics.stringWidth(STRINGS[i]) / 2, yOffset + HEXAGON_SIZE / 6);

			xOffset += HEXAGON_SIZE * SQRT_3 + PADDING;
		}
	}
}
