package graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.FileReader;

@SuppressWarnings("serial")
public class HexagonalTable extends JPanel implements MouseMotionListener {
	private int ORIGIN_X = 50;
	private int ORIGIN_Y = 50;
	private static final double SQRT_3 = Math.sqrt(3);
	private int size = 30;
	

	private int length = 10;
	private int width = 10;

	private int selectedRow = -1;
	private int selectedColumn = -1;

	char[][] solution = null;

	public void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		Color c = null;
		String text = "";
		for (int i = 0; i < length; i++) {

			for (int j= 0; j < width; j++) {

				try {
					solution = this.readSolutionFile("data/solution");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (solution[i][j] == 'A') {
					c = Color.red;

				} else if (solution[i][j] == 'D') {
					c = Color.blue;

				} else if (solution[i][j] == 'E') {
					c = Color.white;
				} else if (solution[i][j] == 'W') {
					c = Color.black;
				}
				else if (solution[i][j] == '.') {
					c = Color.yellow;
				}
				ORIGIN_X = size * 2;
				ORIGIN_Y = size * 2;
				y= ORIGIN_Y + (int) ((1.5 * size * i) );
				x = ORIGIN_X + (int) ((j * SQRT_3 * size) + ((i%2) * size * SQRT_3 / 2));
				text = i + ":" + j;
				if (i == selectedRow && j == selectedColumn) {
					drawHex(g, x, y, size, Color.GRAY, text);
				} else {
					drawHex(g, x, y, size, c, text);
				}
			}
		}

	}

	private void drawHex(Graphics g, int ox, int oy, int size, Color color, String text) {
		g.setColor(color);

		int[] xPoints = {ox, (int) ((ox + (SQRT_3 / 2) * size)), (int) ((ox + SQRT_3 / 2 * size)), ox, (int) ((ox - SQRT_3 / 2 * size)), (int) ((ox - (SQRT_3 / 2)* size))};
		int[] yPoints = {size + oy, (int) (0.5 * size + oy), (int) (oy - 0.5* size), oy - 1* size, (int) (oy - 0.5* size), (int) (oy + 0.5* size)};


		g.fillPolygon(xPoints, yPoints, 6);
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 6);
		// Calculate the position of the text within the hexagon
		/*g.setFont(g.getFont().deriveFont(12f));
		FontMetrics fontMetrics = g.getFontMetrics();
		int textWidth = fontMetrics.stringWidth(text);
		int textHeight = fontMetrics.getHeight();
		int textX = ox - textWidth / 2;
		int textY = oy + textHeight / 4;
		// Draw the text
		g.drawString(text, textX, textY);*/
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		try {
				int mouseX = e.getX() - MainFrame.westPanelWidth - ORIGIN_X;
				int mouseY = e.getY() - MainFrame.northPanelHeight - ORIGIN_Y;
				// Calculate the row index based on the mouse position
				int row = (int) (mouseY  / (size * 1.5));
				// Calculate the column index based on the mouse position
				int column = (int) ((mouseX + size  - ((row % 2) * size * SQRT_3 / 2)) / (SQRT_3 * size));
				// Update the selected row and column
				selectedRow = row;
				selectedColumn = column;
				System.out.println(row + ":" + column);
				repaint();
			}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}





	public char[][] readSolutionFile(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		int numLines = 0;
		int maxLineLength = 0;
		// Determine the number of lines and the maximum line length in the file
		while ((line = reader.readLine()) != null) {
			numLines++;
			maxLineLength = Math.max(maxLineLength, line.length());
		}
		reader.close();
		// Create the 2D array with the appropriate size
		char[][] solution = new char[numLines][maxLineLength];
		// Read the file again and store the characters in the array
		reader = new BufferedReader(new FileReader(fileName));
		int i = 0;
		while ((line = reader.readLine()) != null) {
			for (int j = 0; j < line.length(); j++) {
				solution[i][j] = line.charAt(j);
			}
			i++;
		}
		reader.close();
		return solution;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}
	
	public void changeSize(int size) {
		this.size= size;
	}

}

