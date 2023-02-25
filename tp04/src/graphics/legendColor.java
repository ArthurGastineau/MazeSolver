/**
 * 
 */
package graphics;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * @author arthur
 *
 */
@SuppressWarnings("serial")
public class legendColor extends JPanel{
	Color color; 
	public legendColor (Color color) {
		super();
		this.color = color;
		setBackground(color);
	}
}
