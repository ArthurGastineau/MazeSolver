
public class EmptyBox extends MazeBox{
	
	public EmptyBox(int x, int y) {
		super(x, y);
	}
	
	public String typeOfBox() {
		return super.empty;
	}

}
