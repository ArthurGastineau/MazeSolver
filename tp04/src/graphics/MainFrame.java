package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

import graph.Dijkstra;
import graph.ShortestPaths;
import graph.Vertex;
import maze.Maze;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseMotionListener, MouseListener {

	private JPanel buttonPanel;
	private JPanel legendPanel;
	private JPanel statusPanel;
	private HexagonalTable panelMaze;
	private String fileName = "labyrinthe.maze";
	private Maze actualMaze = new Maze();
	// private HexagonalLabyrinthPanel labyrinthPanel;
	private JPanel editPanel;
	private JButton saveButton;

	private JRadioButton wallButton;
	private JRadioButton emptyButton;
	private JRadioButton startButton;
	private JRadioButton endButton;

	private JLabel statusLabel;
	JLabel creationStatusLabel;

	final static int westPanelWidth = 200;
	final static int northPanelHeight = 30;
	private boolean editMode = false;

	public MainFrame(Maze maze) {
		actualMaze = maze;
		// Initialise the window
		setSize(1700, 1000);
		setTitle("Hexagonal Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Top Display of the status of edition
		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(1600, northPanelHeight));
		statusPanel.setLayout(new BorderLayout(0,0));
		statusPanel.setBorder(BorderFactory.createEmptyBorder());
		addStatus(statusPanel);
		add(statusPanel, BorderLayout.NORTH);

		// Set the layout for the button panel to display the buttons in a grid on the
		// right size of the window
		buttonPanel = new JPanel();
		fileName = addMazeButtons(buttonPanel, maze, panelMaze);
		add(buttonPanel, BorderLayout.EAST);

		// Set the layout to display the maze at the center of the window
		panelMaze = new HexagonalTable();
		//panelMaze.requestFocus();
		add(panelMaze, BorderLayout.CENTER);

		// Set the layout to display the legend at the bottom of the window
		legendPanel = new JPanel();
		legendPanel.setLayout(new GridLayout(2, 8, 20, 5));
		addLegend(legendPanel);
		add(legendPanel, BorderLayout.SOUTH);

		// Set the layout of the edit menu at the left of the windows
		editPanel = new JPanel();
		editPanel.setBorder(new EmptyBorder(200, 0, 0, 0));
		editPanel.setPreferredSize(new Dimension(westPanelWidth, 480));
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		addEdit(editPanel);
		add(editPanel, BorderLayout.WEST);
	}
	
	public void addStatus(JPanel panel) {
		
		creationStatusLabel = new JLabel("Mode Edition");
		creationStatusLabel.setFont(new Font(creationStatusLabel.getFont().getName(), creationStatusLabel.getFont().getStyle(), 20));
		creationStatusLabel.setForeground(Color.RED);
		panel.add(creationStatusLabel, BorderLayout.WEST);

		statusLabel = new JLabel();
		statusLabel.setFont(new Font(statusLabel.getFont().getName(), statusLabel.getFont().getStyle(), 20));
		statusLabel.setText("Veuillez sélectionner un labyrinthe ou en créer un");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(statusLabel, BorderLayout.CENTER);



	}

	public void addEdit(JPanel panel) {
		// Ajoutez un label pour indiquer le mendu d'édition
		JLabel editLabel = new JLabel();
		editLabel.setText("Menu Création");
		editLabel.setFont(new Font(editLabel.getFont().getName(), editLabel.getFont().getStyle(), 15));
		editLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(editLabel);
		
		// Ajoutez un JSeparator comme espace vide
		panel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Ajoutez un label pour indiquer à l'utilisateur de saisir la largeur du
		// labyrinthe
		JLabel widthLabel = new JLabel("Largeur :");
		panel.add(widthLabel);

		// Créez un champ de saisie pour la largeur du labyrinthe
		final JFormattedTextField widthField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		widthField.setMaximumSize(new Dimension(200, 50));
		panel.add(widthField);

		// Ajoutez un label pour indiquer à l'utilisateur de saisir la hauteur du
		// labyrinthe
		JLabel heightLabel = new JLabel("Hauteur :");
		panel.add(heightLabel);

		// Créez un champ de saisie pour la hauteur du labyrinthe
		final JFormattedTextField heightField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		heightField.setMaximumSize(new Dimension(200, 50));
		panel.add(heightField);

		// Ajoutez un bouton pour créer le labyrinthe vide avec les dimensions saisies
		JButton createButton = new JButton("Créer");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Récupérez la largeur et la hauteur du labyrinthe saisies par l'utilisateur
					int width = Integer.parseInt(widthField.getText());
					int height = Integer.parseInt(heightField.getText());

					// Vérifiez que la largeur et la hauteur sont des valeurs valides (supérieures à
					// zéro)
					if (width <= 0 || height <= 0) {
						throw new IllegalArgumentException(
								"La largeur et la hauteur doivent être des valeurs supérieures à zéro");
					}

					if (width > 20 || height > 20) {
						throw new IllegalArgumentException(
								"La largeur et la hauteur doivent être des valeurs inférieure ou égae à 20");
					}

					// Créez un nouveau labyrinthe vide avec les dimensions spécifiées
					Maze newMaze = new Maze(width, height);
					newMaze.saveToTextFile("data/solution");

					// Mettez à jour la vue du labyrinthe avec le nouveau labyrinthe vide
					panelMaze.setLength(width);
					panelMaze.setWidth(height);
					actualMaze = newMaze;
					panelMaze.repaint();
					
					editMode = true;
					creationStatusLabel.setText("Mode Création      ");
					creationStatusLabel.setForeground(Color.GREEN);
					statusLabel.setText("Labyrinthe créé");
					// Display the corresponding legend
					legendPanel.removeAll();
					legendPanel.setLayout(new GridLayout(2, 8, 20, 5));
					addLegend(legendPanel);
					legendPanel.revalidate();
					legendPanel.repaint();
				} catch (IllegalArgumentException ex) {
					// Affichez un message d'erreur si la largeur ou la hauteur n'est pas une valeur
					// valide
					JOptionPane.showMessageDialog(MainFrame.this,
							"La largeur et/ou la hauteur n'est pas valide \n Saisir un nombre compris entre 1 et 20",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(createButton);
		//
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (editMode = true) {
					creationStatusLabel.setText("Mode Création       ");
					creationStatusLabel.setForeground(Color.GREEN);
					statusLabel.setText("Labyrinthe réinitialisé");
					actualMaze.initEmptyMaze(actualMaze.getLength(), actualMaze.getWidth());
					panelMaze.repaint();
				} else {
					// Affichez un message d'erreur signifiant que l'on ne peut seulement reset en
					// mode édition
					JOptionPane.showMessageDialog(MainFrame.this, "Reset seulement possible en mode édition", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(resetButton);
		// Ajoutez 4 Boutons pour chosiir le type de case en édition
		ButtonGroup buttonGroup = new ButtonGroup();

		wallButton = new JRadioButton("Mur");
		buttonGroup.add(wallButton);
		panel.add(wallButton); // Ajoutez chaque JRadioButton individuellement

		emptyButton = new JRadioButton("Vide");
		buttonGroup.add(emptyButton);
		panel.add(emptyButton);

		startButton = new JRadioButton("Départ");
		buttonGroup.add(startButton);
		panel.add(startButton);

		endButton = new JRadioButton("Arrivée");
		buttonGroup.add(endButton);
		panel.add(endButton);

		// Définissez un bouton comme bouton par défaut
		startButton.setSelected(true);

		// Ajoutez un label pour indiquer à l'utilisateur de saisir le nom du fichier
		// sauvegardé
		JLabel savedFileNameLabel = new JLabel("Nom du fichier :");
		editPanel.add(savedFileNameLabel);

		// Permet à l'utilsiateur de choisir le nom du fichier sauvegardé
		final JTextField savedFileNameField = new JTextField();
		savedFileNameField.setMaximumSize(new Dimension(200, 50));
		panel.add(savedFileNameField);

		saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Récupérez le nom du fichier saisi par l'utilisateur
				String savedFileName = savedFileNameField.getText();
				// Si vide utilisez nom par défaut
				if (savedFileName.isEmpty()) {
					savedFileName = "savedMaze.maze";
				} else {
					// Si le nom du fichier contient un point, supprimez tout ce qu'il y a après
					int index = savedFileName.indexOf(".");
					if (savedFileName.indexOf(".") != -1) {
						savedFileName = savedFileName.substring(0, index);
					}
					// Vérifiez si le nom du fichier se termine par ".maze"
					if (!savedFileName.endsWith(".maze")) {
						// Si ce n'est pas le cas, ajoutez l'extension ".maze" au nom du fichier
						savedFileName += ".maze";
					}
				}

				// Enregistrez le labyrinthe dans le fichier
				actualMaze.saveToTextFile("data/" + savedFileName);
				statusLabel.setText("Votre labyrinthe " + savedFileName + " a été sauvegardé");

				// Refresh the east side panel by removing all the components
				buttonPanel.removeAll();

				// Get the new files and add the buttons for the maze files to the button panel
				fileName = addMazeButtons(buttonPanel, actualMaze, panelMaze);

				// Revalidate and repaint the button panel to update the display
				buttonPanel.revalidate();
				buttonPanel.repaint();
			}
		});
		panel.add(saveButton);
	}

	public void addLegend(JPanel panel) {
		JLabel clickGauche = new JLabel("Clic Gauche");
		panel.add(clickGauche);
		if (editMode == false) {
			JLabel Wall_Empty = new JLabel("Modif Mur/Vide");
			panel.add(Wall_Empty);
		} else {
			JLabel Wall_Empty = new JLabel("Ajout Case Choisie");
			panel.add(Wall_Empty);
		}

		JLabel startLabel = new JLabel("Départ");
		JPanel startColor = new JPanel();
		startColor.setBackground(Color.BLUE);
		panel.add(startLabel);
		panel.add(startColor);

		JLabel endLabel = new JLabel("Arrivée");
		JPanel endColor = new JPanel();
		endColor.setBackground(Color.RED);
		panel.add(endLabel);
		panel.add(endColor);

		JLabel emptyLabel = new JLabel("Vide");
		JPanel emptyColor = new JPanel();
		emptyColor.setBackground(Color.WHITE);
		panel.add(emptyLabel);
		panel.add(emptyColor);

		JLabel clickDroit = new JLabel("Clic Droit");
		panel.add(clickDroit);
		if (editMode == false) {
			JLabel newArrival = new JLabel("Changer Arrivée");
			panel.add(newArrival);
		} else {
			JLabel newArrival = new JLabel("Aucune Action");
			panel.add(newArrival);
		}

		JLabel wallLabel = new JLabel("Mur");
		JPanel wallColor = new JPanel();
		wallColor.setBackground(Color.BLACK);
		panel.add(wallLabel);
		panel.add(wallColor);

		JLabel pathLabel = new JLabel("Chemin");
		JPanel pathColor = new JPanel();
		pathColor.setBackground(Color.YELLOW);
		panel.add(pathLabel);
		panel.add(pathColor);

		JLabel selectedLabel = new JLabel("Sélectionné");
		JPanel selectedColor = new JPanel();
		selectedColor.setBackground(Color.GRAY);
		panel.add(selectedLabel);
		panel.add(selectedColor);
	}

	public String addMazeButtons(JPanel panel, final Maze myMaze, HexagonalTable hex) {
		
		File dataDirectory = new File("data");
		FilenameFilter mazeFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".maze");
			}
		};
		String[] mazeFiles = dataDirectory.list(mazeFilter);
		
		panel.setLayout(new GridLayout(mazeFiles.length + 1, 1, 0, 0));
		
		JLabel editionStatusLabel = new JLabel("Menu Edition");
		editionStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		editionStatusLabel.setFont(new Font(editionStatusLabel.getFont().getName(), editionStatusLabel.getFont().getStyle(), 15));
		panel.add(editionStatusLabel);
		for (final String mazeFile : mazeFiles) {
			// Crée un bouton pour chaque fichier de labyrinthe
			JButton mazeButton = new JButton(mazeFile);
			panel.add(mazeButton);

			// Ajoute un écouteur d'événement pour chaque bouton
			mazeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					editMode = false;
					creationStatusLabel.setText("Mode lecture seule");
					creationStatusLabel.setForeground(Color.RED);
					statusLabel.setText("Fichier chargé : " + mazeFile);
					legendPanel.removeAll();
					legendPanel.setLayout(new GridLayout(2, 8, 20, 5));
					addLegend(legendPanel);
					legendPanel.revalidate();
					legendPanel.repaint();
					//
					int[] vals = myMaze.fromFileGetMazeSize("data/" + mazeFile);
					myMaze.setSize(vals[0], vals[1]);
					panelMaze.setLength(vals[0]);
					panelMaze.setWidth(vals[1]);
					myMaze.initFromTextFile("data/" + mazeFile);
					Vertex startVertex = myMaze.getStartVertex();
					Vertex endVertex = myMaze.getEndVertex();
					System.out.println(
							"Calculating shotest path from" + startVertex.toString() + " to " + endVertex.toString());
					ShortestPaths shortestPaths = Dijkstra.dijkstra(myMaze, startVertex, endVertex);
					List<Vertex> path = shortestPaths.getShortestPath(endVertex);
					myMaze.saveShortestPath("data/solution", path);
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
		if (editMode == false) {
			// Vérifiez si le clic est gauche (1) ou droit (3)
			if (e.getButton() == MouseEvent.BUTTON1) {
				int selectedRow = panelMaze.getSelectedRow();
				int selectedColumn = panelMaze.getSelectedColumn();
				// Vérifiez que l'hexagone sélectionné est valide (c'est-à-dire qu'il a été
				// précédemment sélectionné par la souris)
				if (selectedRow != -1 && selectedColumn != -1 && selectedRow < actualMaze.getLength()
						&& selectedColumn < actualMaze.getWidth()) {
					// Vérifiez que l'hexagone sélectionné n'est pas une case de départ ou d'arrivée
					String boxType = actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox();
					if (boxType.compareTo("Empty") == 0) {
						actualMaze.addWallBox(selectedRow, selectedColumn);
					} else if (boxType.compareTo("Wall") == 0) {
						actualMaze.addEmptyBox(selectedRow, selectedColumn);
					}
					// Redessinez le panel
					Vertex startVertex = actualMaze.getStartVertex();
					Vertex endVertex = actualMaze.getEndVertex();
					System.out.println("Modified Maze");
					ShortestPaths shortestPaths = Dijkstra.dijkstra(actualMaze, startVertex, endVertex);
					List<Vertex> path = shortestPaths.getShortestPath(endVertex);
					actualMaze.saveShortestPath("data/solution", path);

					actualMaze.saveToTextFile("data/modified.txt");

					panelMaze.repaint();
				}
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				int selectedRow = panelMaze.getSelectedRow();
				int selectedColumn = panelMaze.getSelectedColumn();
				// Vérifiez que l'hexagone sélectionné est valide (c'est-à-dire qu'il a été
				// précédemment sélectionné par la souris)
				if (selectedRow != -1 && selectedColumn != -1 && selectedRow < actualMaze.getLength()
						&& selectedColumn < actualMaze.getWidth()) {
					// Vérifiez que l'hexagone sélectionné n'est pas une case de départ ou d'arrivée
					String boxType = actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox();
					if (boxType.compareTo("Arrival") != 0 && boxType.compareTo("Departure") != 0) {
						System.out.println("Modified Arrival");

						Vertex oldEndVertex = actualMaze.getEndVertex();
						// int x = oldEndVertex.getRow();
						// int y = oldEndVertex.getCol();
						// System.out.println(actualMaze.getMaze()[oldEndVertex.getRow()][oldEndVertex.getCol()].typeOfBox());
						actualMaze.addEmptyBox(oldEndVertex.getRow(), oldEndVertex.getCol());
						// System.out.println(actualMaze.getMaze()[x][y].typeOfBox());
						// Set the selected hexagon as the new end vertex
						// System.out.println(actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox());
						actualMaze.addArrivalBox(selectedRow, selectedColumn);
						// System.out.println(actualMaze.getMaze()[selectedRow][selectedColumn].typeOfBox());

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
		} else {
			if (e.getButton() == MouseEvent.BUTTON1) {
				int row = panelMaze.getSelectedRow();
				int col = panelMaze.getSelectedColumn();

				// Vérifiez si le bouton "Mur" est sélectionné
				if (wallButton.isSelected()) {
					// Affectez à la case correspondante dans actualMaze la valeur "Mur"
					actualMaze.addWallBox(row, col);
				}
				// Vérifiez si le bouton "Vide" est sélectionné
				else if (emptyButton.isSelected()) {
					// Affectez à la case correspondante dans actualMaze la valeur "Vide"
					actualMaze.addEmptyBox(row, col);
				}
				// Vérifiez si le bouton "Départ" est sélectionné
				else if (startButton.isSelected() && actualMaze.hasDepartureBox() == false) {
					// Affectez à la case correspondante dans actualMaze la valeur "Départ"
					actualMaze.addDepartureBox(row, col);
				}
				// Vérifiez si le bouton "Arrivée" est sélectionné
				else if (endButton.isSelected() && actualMaze.hasArrivalBox() == false) {
					// Affectez à la case correspondante dans actualMaze la valeur "Arrivée"
					actualMaze.addArrivalBox(row, col);
				}
				actualMaze.saveToTextFile("data/solution");
				panelMaze.repaint();
			}
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
