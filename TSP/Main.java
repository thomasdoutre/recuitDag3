import javax.swing.*;

import java.awt.event.*;
import java.awt.BorderLayout;

// Cette classe contient la méthode main. Elle initialise la zone de dessin et ses composantes. /*

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L; // bidouille obscurantiste tant qu'ésotérique pour se débarasser d'un warning...
	private ZoneDessin zoneDessin;
    
	public Main(String title) {
		super(title);
     	getContentPane().setLayout(new BorderLayout());
        // On initialise la zone de dessin et le menu
		initZoneDessin();
		initMenu();
        
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	protected void initMenu() {
		// définition des différentes options
		// onglet opérations
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuOp = new JMenu("Opérations");
		menuBar.add(menuOp);
        // les opérations possibles :
		JMenuItem item;
		item = new JMenuItem("Solution");
		item.addActionListener(this.zoneDessin);
		menuOp.add(item);
		item = new JMenuItem("Apocatastase");
		item.addActionListener(this.zoneDessin);
		menuOp.add(item);
		item = new JMenuItem("Monde aléatoire");
		item.addActionListener(this.zoneDessin);
		menuOp.add(item);
	}
	
	// initialise la zone de dessin...
	protected void initZoneDessin() {
		this.zoneDessin = new ZoneDessin();
		getContentPane().add(this.zoneDessin, BorderLayout.CENTER);
	}

	// Affichage de la fenêtre
    public static void main(String[] args) {
    	Main app = new Main("TSP par recuit simulé");
		app.pack();
		app.setVisible(true);

    }
}
