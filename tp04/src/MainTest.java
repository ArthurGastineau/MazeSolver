import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainTest {

	public static void main(String[] args) {
		Maze myMaze = new Maze(10, 10);
		myMaze.initFromTextFile("data/labyrinthe.maze");
		myMaze.saveToTextFile("data/labyrinthe2.maze");
		// Vérification de l'égalité des fichiers
	    try {
	        byte[] file1 = Files.readAllBytes(Paths.get("data/labyrinthe.maze"));
	        byte[] file2 = Files.readAllBytes(Paths.get("data/labyrinthe2.maze"));
	        if (Arrays.equals(file1, file2)) {
	            System.out.println("Files are equal");
	        } else {
	            System.out.println("Files are not equal");
	        }
	    } catch (IOException e) {
	        System.out.println("Error comparing files: " + e.getMessage());
	    }
	}

}
