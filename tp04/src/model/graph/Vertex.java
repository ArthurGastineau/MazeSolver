package model.graph;

/**
 * An interface for defining a vertex in a graph with a row and column position
 * and a label.
 * 
 * <p>
 * The row and column position of a vertex is used to locate it on a 2D grid or
 * matrix. The label of a vertex is a string representation of the vertex, which
 * can be used for printing or debugging purposes.
 * </p>
 * 
 * <p>
 * Implementations of this interface should provide a constructor that takes the
 * row and column position of the vertex as arguments.
 * </p>
 * 
 * @author Arthur Gastineau
 */

public interface Vertex {

	/**
	 * Gets the row position of this vertex.
	 * 
	 * @return the row position of this vertex
	 */

	public int getRow();

	/**
	 * Sets the row position of this vertex.
	 * 
	 * @param row the new row position of this vertex
	 */

	public void setRow(int row);

	/**
	 * Gets the column position of this vertex.
	 * 
	 * @return the column position of this vertex
	 */

	public int getCol();

	/**
	 * Sets the column position of this vertex.
	 * 
	 * @param col the new column position of this vertex
	 */

	public void setCol(int col);

	/**
	 * Gets the label of this vertex for debugging purposes
	 * 
	 * @return the label of this vertex
	 */

	public String toString();
}
