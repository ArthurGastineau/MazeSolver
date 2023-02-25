/**
 * 
 */
package graphics;

import javax.swing.JLabel;

/**
 * @author arthur
 *
 */
@SuppressWarnings("serial")
public class legendLabel extends JLabel{
	String text;
	public legendLabel (String text) {
		super(text);
		this.text = text;
	}
}
