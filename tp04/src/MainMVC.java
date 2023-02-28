import controller.MazeController;

/**
 *
 * MazeVMC --- A maze solver application that generate a maze from a file, and
 * then proceeds to solve the maze using a the Dijkstra algorithm. This
 * generation is visualized using JSwing, and allows the user to interact with
 * the maze, enabling them to choose the size of the maze, the maze start/end
 * points and obstacles.
 *
 * @author Arthur Gastineau
 */
public class MainMVC {
	public static void main(String[] args) {
		new MazeController();
	}
}
