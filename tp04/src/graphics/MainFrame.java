package graphics;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.*;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseMotionListener, MouseListener{

	private JPanel buttonPanel;
	private JPanel legendPanel;
	private HexagonalTable panelMaze;
	private String fileName = "labyrinthe.maze";
	private Maze actualMaze = new Maze();
	//private HexagonalLabyrinthPanel labyrinthPanel;
	private JPanel editPanel;
	private JTextField dimensionField;
	private JButton createButton;
	private JButton saveButton;

	final static int westPanelWidth = 150;
	private boolean editMode = false;



	public MainFrame(Maze maze) {
		actualMaze = maze;
		// Initialise the window
		setSize(1600, 1000);
		setTitle("Hexagonal Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Set the layout for the button panel to display the buttons in a grid

		File dataDirectory = new File("data");
		FilenameFilter mazeFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".maze");
			}
		};

		String[] mazeFiles = dataDirectory.list(mazeFilter);
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, mazeFiles.length/2));
		add(buttonPanel, BorderLayout.EAST);
		fileName = addMazeButtons(mazeFiles, buttonPanel, maze, panelMaze);

		// display the maze
		panelMaze = new HexagonalTable();
		panelMaze.requestFocus();
		add(panelMaze, BorderLayout.CENTER);

		// Display the legend
		JPanel legendPanel = new JPanel();
		legendPanel.setLayout(new GridLayout(2, 8, 20, 5));

		// Créez une JLabel pour chaque type de case/clic et une JPanel pour la couleur de chaque case
		addLegend(legendPanel);

		//
		editPanel = new JPanel();
		editPanel.setPreferredSize(new Dimension(westPanelWidth, 480));
		editPanel.setLayout(new GridLayout(3, 2));

		// Ajoutez un label pour indiquer à l'utilisateur de saisir la largeur du labyrinthe
		JLabel widthLabel = new JLabel("Largeur :");
		editPanel.add(widthLabel);

		// Créez un champ de saisie pour la largeur du labyrinthe
		JFormattedTextField widthField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		editPanel.add(widthField);

		// Ajoutez un label pour indiquer à l'utilisateur de saisir la hauteur du labyrinthe
		JLabel heightLabel = new JLabel("Hauteur :");
		editPanel.add(heightLabel);

		// Créez un champ de saisie pour la hauteur du labyrinthe
		JFormattedTextField heightField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		editPanel.add(heightField);

		// Ajoutez un bouton pour créer le labyrinthe vide avec les dimensions saisies
		JButton createButton = new JButton("Créer");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					editMode = true;
					// Récupérez la largeur et la hauteur du labyrinthe saisies par l'utilisateur
					int width = Integer.parseInt(widthField.getText());
					int height = Integer.parseInt(heightField.getText());
					
					// Vérifiez que la largeur et la hauteur sont des valeurs valides (supérieures à zéro)
					if (width <= 0 || height <= 0) {
						throw new IllegalArgumentException("La largeur et la hauteur doivent être des valeurs supérieures à zéro");
					}
					
					if (width > 20 || height > 20) {
						throw new IllegalArgumentException("La largeur et la hauteur doivent être des valeurs inférieure ou égae à 20");
					}

					// Créez un nouveau labyrinthe vide avec les dimensions spécifiées
					Maze newMaze = new Maze(width, height);
					newMaze.saveToTextFile("data/solution");

					// Mettez à jour la vue du labyrinthe avec le nouveau labyrinthe vide
					panelMaze.setLength(width);
					panelMaze.setWidth(height);
					actualMaze = newMaze;
					panelMaze.repaint();
				}
				catch (IllegalArgumentException ex) {
					// Affichez un message d'erreur si la largeur ou la hauteur n'est pas une valeur valide
					JOptionPane.showMessageDialog(MainFrame.this, "La largeur et/ou la hauteur n'est pas valide \n Saisir un nombre compris entre 1 et 20", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		editPanel.add(createButton);

		saveButton = new JButton("Save");
		editPanel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Enregistrez le labyrinthe dans le répertoire "data"
				actualMaze.saveToTextFile("data/newMaze.maze");
			}
		});
		add(editPanel, BorderLayout.WEST);
	}

	public void addLegend (JPanel legendPanel) {
		JLabel clickGauche = new JLabel("Clic Gauche");
		legendPanel.add(clickGauche);
		JLabel Wall_Empty = new JLabel("Modif Mur/Vide");
		legendPanel.add(Wall_Empty);

		JLabel startLabel = new JLabel("Départ");
		JPanel startColor = new JPanel();
		startColor.setBackground(Color.BLUE);
		legendPanel.add(startLabel);
		legendPanel.add(startColor);

		JLabel endLabel = new JLabel("Arrivée");
		JPanel endColor = new JPanel();
		endColor.setBackground(Color.RED);
		legendPanel.add(endLabel);
		legendPanel.add(endColor);		

		JLabel emptyLabel = new JLabel("Vide");
		JPanel emptyColor = new JPanel();
		emptyColor.setBackground(Color.WHITE);
		legendPanel.add(emptyLabel);
		legendPanel.add(emptyColor);

		JLabel clickDroit = new JLabel("Clic Droit");
		legendPanel.add(clickDroit);
		JLabel newArrival = new JLabel("Changer Arrivée");
		legendPanel.add(newArrival);

		JLabel wallLabel = new JLabel("Mur");
		JPanel wallColor = new JPanel();
		wallColor.setBackground(Color.BLACK);
		legendPanel.add(wallLabel);
		legendPanel.add(wallColor);

		JLabel pathLabel = new JLabel("Chemin");
		JPanel pathColor = new JPanel();
		pathColor.setBackground(Color.YELLOW);
		legendPanel.add(pathLabel);
		legendPanel.add(pathColor);

		JLabel selectedLabel = new JLabel("Sélectionné");
		JPanel selectedColor = new JPanel();
		selectedColor.setBackground(Color.GRAY);
		legendPanel.add(selectedLabel);
		legendPanel.add(selectedColor);

		// Ajoutez le panel de légende à la fenêtre
		add(legendPanel, BorderLayout.SOUTH);
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
					editMode = false;
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
		// Vérifiez si le clic est gauche (1) ou droit (3)
		if (editMode == false) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int selectedRow = panelMaze.getSelectedRow();
				int selectedColumn = panelMaze.getSelectedColumn();
				// Vérifiez que l'hexagone sélectionné est valide (c'est-à-dire qu'il a été précédemment sélectionné par la souris)
				if (selectedRow != -1 && selectedColumn != -1 && selectedRow < actualMaze.getLength() && selectedColumn < actualMaze.getWidth()) {
					// Vérifiez que l'hexagone sélectionné n'est pas une case de départ ou d'arrivée
					String boxType = actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox();
					if (boxType.compareTo("Empty") == 0) {
						actualMaze.addWallBox(selectedRow, selectedColumn);
					}
					else if (boxType.compareTo("Wall") == 0) {
						actualMaze.addEmptyBox(selectedRow, selectedColumn);
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
			}
			else if (e.getButton() == MouseEvent.BUTTON3) {
				int selectedRow = panelMaze.getSelectedRow();
				int selectedColumn = panelMaze.getSelectedColumn();
				// Vérifiez que l'hexagone sélectionné est valide (c'est-à-dire qu'il a été précédemment sélectionné par la souris)
				if (selectedRow != -1 && selectedColumn != -1 && selectedRow < actualMaze.getLength() && selectedColumn < actualMaze.getWidth()) {
					// Vérifiez que l'hexagone sélectionné n'est pas une case de départ ou d'arrivée
					String boxType = actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox();
					if (boxType.compareTo("Arrival") != 0 && boxType.compareTo("Departure") != 0) {
						System.out.println("Modified Arrival");

						Vertex oldEndVertex = actualMaze.getEndVertex();
						//int x = oldEndVertex.getRow();
						//int y = oldEndVertex.getCol();
						//System.out.println(actualMaze.getMaze()[oldEndVertex.getRow()][oldEndVertex.getCol()].typeOfBox());
						actualMaze.addEmptyBox(oldEndVertex.getRow(), oldEndVertex.getCol());
						//System.out.println(actualMaze.getMaze()[x][y].typeOfBox());
						// Set the selected hexagon as the new end vertex
						//System.out.println(actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox());
						actualMaze.addArrivalBox(selectedRow, selectedColumn);
						//System.out.println(actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox());

						// Recalculate the shortest path
						Vertex startVertex = actualMaze.getStartVertex();
						Vertex endVertex = actualMaze.getEndVertex();
						ShortestPaths shortestPaths = Dijkstra.dijkstra(actualMaze, startVertex, endVertex);
						List<Vertex> path = shortestPaths.getShortestPath(endVertex);
						actualMaze.saveShortestPath("data/solution", path);

						actualMaze.saveToTextFile("data/modified.txt");
						panelMaze.repaint();

					}
				}
			}
		}
		else {
			
		}
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

