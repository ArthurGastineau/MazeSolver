/**
 * 
 */
package model;

/**
 *
 * All of the different maze box types.
 * 
 * @author Arthur Gastineau
 */
public enum BoxType {
	EMPTY("EMPTY"), WALL("WALL"), DEPARTURE("DEPARTURE"), ARRIVAL("ARRIVAL");

	private final String name;

	BoxType(String name) {
		this.name = name;
	}

	/**
	 * Returns the enum value corresponding to the input string (if it exists).
	 *
	 * @param name A string
	 * @return The BoxType with the name corresponding to the input string (if it
	 *         exists).
	 */
	public static BoxType fromString(String name) {
		for (BoxType boxType : BoxType.values()) {
			if (boxType.name.equalsIgnoreCase(name)) {
				return boxType;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
}
