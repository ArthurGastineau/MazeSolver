import graph.Vertex;

<<<<<<< HEAD
public abstract class MazeBox implements Vertex{
	
=======
public abstract class MazeBox {

>>>>>>> branch 'main' of git@gitlab.enst.fr:2022inf103/groupe6/gastineau-arthur.git
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
	
	// get his Label
	public String getLabel () {
		return x + " , " + y;
	}
	

	public abstract String typeOfBox();

}
