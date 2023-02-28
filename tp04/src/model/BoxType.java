package model;

/**
 *
 * Represents all of the different maze box types. Each BoxType can be EMPTY,
 * WALL, DEPARTURE or ARRIVAL.
 *
 * @author Arthur Gastineau
 */

public enum BoxType {
	EMPTY("EMPTY"), WALL("WALL"), DEPARTURE("DEPARTURE"), ARRIVAL("ARRIVAL");

	private final String name;

	/**
	 * Constructs a {@link BoxType} object with the specified name.
	 *
	 * @param name The name of the box type.
	 */

	BoxType(String name) {
		this.name = name;
	}

	/**
	 * Returns the enum value corresponding to the input string (if it exists).
	 *
	 * @param name A string corresponding to a box type
	 *
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

	/**
	 * Returns the name of this box type.
	 *
	 * @return The name of this box type.
	 */

	public String getName() {
		return name;
	}
}
