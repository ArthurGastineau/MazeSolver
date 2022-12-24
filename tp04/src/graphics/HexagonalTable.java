package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HexagonalTable {
  private static final int ORIGIN_X = 100;
  private static final int ORIGIN_Y = 100;
  private static final double SQRT_3 = Math.sqrt(3);
  private static final int SIZE =25;
  private static final Color[] COLORS = {Color.RED, Color.YELLOW, Color.CYAN, Color.GREEN};
  
  private static final int numberOfBoxes = 10;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Hexagonal Table");
    HexagonalTablePanel panel = new HexagonalTablePanel();
    frame.add(panel);
    frame.addMouseMotionListener(panel);
    frame.setSize(600,600);
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
        		   y= ORIGIN_Y + (int) ((1.5 * SIZE * i) );
        		   x = ORIGIN_X + (int) ((j * SQRT_3 * SIZE) + ((i%2) * SIZE * SQRT_3 / 2));
        		   if (i == selectedRow && j == selectedColumn) {
        	            drawHex(g, x, y, SIZE, Color.BLACK, text);
        	          } else {
        	            drawHex(g, x, y, SIZE, c, text);
        	          }
        	   }
           }
         
    }

    private void drawHex(Graphics g, int ox, int oy, int size, Color color, String text) {
      g.setColor(color);
      int[] xPoints = new int [6];
      int[] yPoints = new int [6];
      for (int i = 0; i < 6; i++) {
    	  double angle = (i + 0.5) * 2 * Math.PI / 6 ;
    	  xPoints[i] = (int) (ox + SIZE * Math.cos(angle));
    	  yPoints[i] = (int) (oy + SIZE * Math.sin(angle));
    	  
      }
      //int[] xPoints = {ox, (int) ((ox + (SQRT_3 / 2) * SIZE)), (int) ((ox + SQRT_3 / 2 * SIZE)), ox, (int) ((ox - SQRT_3 / 2 * SIZE)), (int) ((ox - (SQRT_3 / 2)* SIZE))};
      //int[] yPoints = {SIZE + oy, (int) (0.5 * SIZE + oy), (int) (oy - 0.5* SIZE), oy - 1* SIZE, (int) (oy - 0.5* SIZE), (int) (oy + 0.5* SIZE)};
      
      
      g.fillPolygon(xPoints, yPoints, 6);
      g.setColor(Color.BLACK);
      g.drawPolygon(xPoints, yPoints, 6);
      g.setFont(g.getFont().deriveFont(24f));
      //g.drawString(text, ox + SIZE + 2, oy + 2 + 2 * SIZE);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      int x = e.getX() - ORIGIN_X;
      int y = e.getY() - ORIGIN_Y;
      if (x < 0 || y < 0 || x > WIDTH * 2 * SIZE * SQRT_3 || y > HEIGHT * 3 * SIZE) return;
      int ii = y / (3 * SIZE);
      int jj = x / (2 * (int) SQRT_3 * SIZE);
      int row = (int) Math.round(ii - 0.5 * (jj % 2));
      int col = (int) Math.round(jj * 1.5);
      selectedRow = row;
      selectedColumn = col;
      repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
  }
}

