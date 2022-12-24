package graphics;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MouseMotionListener{

	private JPanel buttonPanel;
	private JPanel loadPanel;
	private JButton loadButton;
	private HexagonalTable panelMaze;
	//private HexagonalLabyrinthPanel labyrinthPanel;


	public MainFrame() {
		// Initialise the window
		setSize(1500, 1000);
		setTitle("Hexagonal Labyrinth");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buttonPanel = new JPanel();
		add(buttonPanel, BorderLayout.EAST);

		loadPanel = new JPanel();
		add(loadPanel, BorderLayout.SOUTH);

		loadButton = new JButton("Load");
		loadPanel.add(loadButton);

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
		addMazeButtons(mazeFiles, buttonPanel);

		panelMaze = new HexagonalTable();
		panelMaze.requestFocus();
		add(panelMaze, BorderLayout.CENTER);


	}



	public void addMazeButtons(String[] mazeFiles, JPanel panel) {
		for (String mazeFile : mazeFiles) {
			// Crée un bouton pour chaque fichier de labyrinthe
			JButton mazeButton = new JButton(mazeFile);
			buttonPanel.add(mazeButton);

			// Ajoute un écouteur d'événement pour chaque bouton
			mazeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

				}
			});
			panel.add(mazeButton);
		}
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
}

