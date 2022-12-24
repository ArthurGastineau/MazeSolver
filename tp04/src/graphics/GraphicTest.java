package graphics;


public class GraphicTest {

	public static void main(String[] args) {
	    // création de notre fenêtre principale
	    MainFrame mainFrame = new MainFrame();
	    mainFrame.addMouseMotionListener(mainFrame);
	    // affichage de la fenêtre
	    mainFrame.setVisible(true);
	}

}
