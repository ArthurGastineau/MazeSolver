package graphics;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import javax.swing.*;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseMotionListener, MouseListener{

	private JPanel buttonPanel;
	private HexagonalTable panelMaze;
	private String fileName = "labyrinthe.maze";
	private Maze actualMaze = new Maze();
	//private HexagonalLabyrinthPanel labyrinthPanel;


	public MainFrame(Maze maze) {
		actualMaze = maze;
		// Initialise the window
		setSize(1500, 1000);
		setTitle("Hexagonal Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);

		// Set the layout for the button panel to display the buttons in a grid
		buttonPanel.setLayout(new GridLayout(3, 3));

		File dataDirectory = new File("data");
		FilenameFilter mazeFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".maze");
			}
		};
		String[] mazeFiles = dataDirectory.list(mazeFilter);
		fileName = addMazeButtons(mazeFiles, buttonPanel, maze, panelMaze);

		// display the maze
		panelMaze = new HexagonalTable();
		panelMaze.requestFocus();
		add(panelMaze, BorderLayout.CENTER);


	}



	public String addMazeButtons(String[] mazeFiles, JPanel panel, Maze myMaze, HexagonalTable hex) {
		for (String mazeFile : mazeFiles) {
			// Crée un bouton pour chaque fichier de labyrinthe
			JButton mazeButton = new JButton(mazeFile);
			buttonPanel.add(mazeButton);

			// Ajoute un écouteur d'événement pour chaque bouton
			mazeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int [] vals = myMaze.fromFileGetMazeSize("data/" + mazeFile);
					myMaze.setSize(vals[0], vals[1]);
					panelMaze.setLength(vals[0]);
					panelMaze.setWidth(vals[1]);
					myMaze.initFromTextFile("data/" + mazeFile);
					Vertex startVertex = myMaze.getStartVertex();
					Vertex endVertex = myMaze.getEndVertex();
					System.out.println("Calculating shotest path from" + startVertex.toString() + " to " + endVertex.toString());
					ShortestPaths shortestPaths = Dijkstra.dijkstra(myMaze, startVertex, endVertex);
					List<Vertex> path = shortestPaths.getShortestPath(endVertex);
					myMaze.saveShortestPath("data/solution",path);
					actualMaze = myMaze;
				}
			});
			panel.add(mazeButton);
		}
		return "";
	}

	public String getFileName() {
		return fileName;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		panelMaze.mouseMoved(e);
	}
	
	public void mouseClicked(MouseEvent e) {
		int selectedRow = panelMaze.getSelectedRow();
		int selectedColumn = panelMaze.getSelectedColumn();
		// Vérifiez que l'hexagone sélectionné est valide (c'est-à-dire qu'il a été précédemment sélectionné par la souris)
		if (selectedRow != -1 && selectedColumn != -1) {
			// Vérifiez que l'hexagone sélectionné n'est pas une case de départ ou d'arrivée
			System.out.println(selectedRow + ":" + selectedColumn);
			String boxType = actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox();
			if (boxType.compareTo("Empty") == 0) {
				actualMaze.addWallBox(selectedRow, selectedColumn);
			}
			else if (boxType.compareTo("Wall") == 0) {
				actualMaze.addEmptyBox(selectedRow, selectedColumn);
			}
		}

		// Redessinez le panel
		Vertex startVertex = actualMaze.getStartVertex();
		Vertex endVertex = actualMaze.getEndVertex();
		System.out.println("Modified Maze");
		ShortestPaths shortestPaths = Dijkstra.dijkstra(actualMaze, startVertex, endVertex);
		List<Vertex> path = shortestPaths.getShortestPath(endVertex);
		actualMaze.saveShortestPath("data/solution",path);

		actualMaze.saveToTextFile("data/modified.txt");
		
		panelMaze.repaint();
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

