/**
 * 
 */
package graphics;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.graph.Dijkstra;
import model.graph.ShortestPaths;
import model.graph.Vertex;
import model.maze.Maze;

/**
 * @author arthur
 *
 */
public class buttonPanel extends JPanel{
	
	String fileName;
	JFrame myFrame;
	
	public buttonPanel (JFrame myFrame ) {
		this.myFrame = myFrame;
	}
	
	public String addMazeButtons(Maze myMaze, HexagonalTable hex, boolean editMode) {

		File dataDirectory = new File("data");
		FilenameFilter mazeFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".maze");
			}
		};
		String[] mazeFiles = dataDirectory.list(mazeFilter);

		setLayout(new GridLayout(mazeFiles.length + 2, 1, 0, 0));

		JLabel editionStatusLabel = new JLabel("Menu Edition");
		editionStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editionStatusLabel
				.setFont(new Font(editionStatusLabel.getFont().getName(), editionStatusLabel.getFont().getStyle(), 15));
		add(editionStatusLabel);


		for (String mazeFile : mazeFiles) {
			// Crée un bouton pour chaque fichier de labyrinthe
			JButton mazeButton = new JButton(mazeFile);
			add(mazeButton);

			// Ajoute un écouteur d'événement pour chaque bouton
			mazeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						//editMode = false;
						fileName = mazeFile;
						//
						int[] vals = myMaze.fromFileGetMazeSize("data/" + mazeFile);
						int length = vals[0];
						int width = vals[1];
						if (width > Maze.MAX_WIDTH || length > Maze.MAX_LENGTH) {
							throw new IllegalArgumentException(
									"La largeur et la hauteur doivent être des valeurs inférieure ou égale à 18");
						}
						myMaze.setSize(vals[0], vals[1]);
						//panelMaze.setLength(length);
						//panelMaze.setWidth(width);
						myMaze.initFromTextFile("data/" + mazeFile);
						Vertex startVertex = myMaze.getStartVertex();
						Vertex endVertex = myMaze.getEndVertex();
						ShortestPaths shortestPaths = Dijkstra.dijkstra(myMaze, startVertex, endVertex);
						List<Vertex> path = shortestPaths.getShortestPath(endVertex);
						myMaze.saveShortestPath("data/solution", path);
						//actualMaze = myMaze;
						//panelMaze.repaint();
					} catch (IllegalArgumentException ex) {
						// Affichez un message d'erreur si la largeur ou la hauteur n'est pas une valeur
						// valide
						JOptionPane.showMessageDialog(myFrame,
								"La largeur et/ou la hauteur du labyrinthe chargé n'est pas valide \n Ils doivent être compris entre 1 et 18",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			add(mazeButton);
		}
		return fileName;
	}
	
}
