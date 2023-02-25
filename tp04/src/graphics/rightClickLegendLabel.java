/**
 * 
 */
package graphics;

/**
 * @author arthur
 *
 */
public class rightClickLegendLabel extends legendLabel{

	
	/**
	 * @param text
	 */
	public rightClickLegendLabel(String text) {
		super(text);
	}
	
	public void changeLabel(String text) {
		setText(text);
		this.text = text;
	}

}
