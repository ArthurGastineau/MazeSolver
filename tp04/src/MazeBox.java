
public abstract class MazeBox {

	private boolean hasCrossed;
	private int x;
	private int y;
	private int boxNumber; // Grid index : 10*x + y

	public final String departure = "Departure";
	public final String arrival = "Arrival";
	public final String empty = "Empty";
	public final String wall = "Wall";

	public MazeBox(int x, int y) {
		this.x = x;
		this.y = y;
		boxNumber = 10 * y + x;
	}

	public void setHasCrossed(boolean state) {
		hasCrossed = state;
	}

	public boolean getHasCrossed() {
		return hasCrossed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumberint(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public abstract String typeOfBox();

}
