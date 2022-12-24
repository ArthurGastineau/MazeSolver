package graphics;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HexagonalTable {
  private static final int ORIGIN_X = 100;
  private static final int ORIGIN_Y = 100;
  private static final double SQRT_3 = Math.sqrt(3);
  private static final int size =30;
  private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN};
  
  private static final int numberOfBoxes = 10;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Hexagonal Table");
    HexagonalTablePanel panel = new HexagonalTablePanel();
    frame.add(panel);
    frame.addMouseMotionListener(panel);
    frame.setSize(800,800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @SuppressWarnings("serial")
private static class HexagonalTablePanel extends JPanel implements MouseMotionListener {
    private int selectedRow = -1;
    private int selectedColumn = -1;

    @Override
    public void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		String text = "";
       for (int i = 0; i < numberOfBoxes; i++) {

    	   for (int j= 0; j < numberOfBoxes; j++) {
    		   Color c = COLORS[j % 2 + 2 * (i % 2)];
    		   y= ORIGIN_Y + (int) ((1.5 * size * i) );
    		   x = ORIGIN_X + (int) ((j * SQRT_3 * size) + ((i%2) * size * SQRT_3 / 2));
    		   text = i + ":" + j;
    		   if (i == selectedRow && j == selectedColumn) {
    	            drawHex(g, x, y, size, Color.BLACK, text);
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
      g.setFont(g.getFont().deriveFont(24f));
      FontMetrics fontMetrics = g.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(text);
      int textHeight = fontMetrics.getHeight();
      int textX = ox - textWidth / 2;
      int textY = oy + textHeight / 4;
      // Draw the text
      g.drawString(text, textX, textY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      int mouseX = e.getX() ;
      int mouseY = e.getY() ;
      // Calculate the row index based on the mouse position
      int row = (int) ((mouseY - ORIGIN_Y) / (size * 1.5));
      // Calculate the column index based on the mouse position
      int column = (int) ((mouseX + size - ORIGIN_X - ((row % 2) * size * SQRT_3 / 2)) / (SQRT_3 * size));
      // Update the selected row and column
      selectedRow = row;
      selectedColumn = column;
      repaint();
    }


    




    @Override
    public void mouseDragged(MouseEvent e) {}
  }
}

