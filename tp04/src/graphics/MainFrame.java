package graphics;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.*;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel buttonPanel;
	private JPanel loadPanel;
	private JButton loadButton;
	//private HexagonalLabyrinthPanel labyrinthPanel;


  public MainFrame() {
	// Initialise the window
	    setSize(900, 800);
	    setTitle("Hexagonal Labyrinth");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    buttonPanel = new JPanel();
	    add(buttonPanel, BorderLayout.NORTH);

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
	    
	    HexagonalTable panelMaze = new HexagonalTable();
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

}

