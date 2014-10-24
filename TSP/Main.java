import javax.swing.*;

import java.awt.event.*;
import java.awt.BorderLayout;

// Cette classe contient la mÃ©thode main. Elle initialise la zone de dessin et ses composantes. /*

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L; // bidouille obscurantiste tant qu'Ã©sotÃ©rique pour se dÃ©barasser d'un warning...
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
		// dÃ©finition des diffÃ©rentes options
		// onglet opÃ©rations
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuTxt = new JMenu("Fichiers");
		menuBar.add(menuTxt);
		JMenu menuOp = new JMenu("Opérations");
		menuBar.add(menuOp);
		JMenu menuPrm = new JMenu("Parametres");
		menuBar.add(menuPrm);
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
		item = new JMenuItem("Affiner la solution");
		item.addActionListener(this.zoneDessin);
		menuOp.add(item);
		item = new JMenuItem("Charger monde");
		item.addActionListener(this.zoneDessin);
		menuTxt.add(item);
		item = new JMenuItem("Lire solution");
		item.addActionListener(this.zoneDessin);
		menuTxt.add(item);
		item = new JMenuItem("Parametres");
		item.addActionListener(this.zoneDessin);
		menuPrm.add(item);
	}
	
	// initialise la zone de dessin...
	protected void initZoneDessin() {
		this.zoneDessin = new ZoneDessin();
		getContentPane().add(this.zoneDessin, BorderLayout.CENTER);
	}

	// Affichage de la fenÃªtre
    public static void main(String[] args) {
    	Main app = new Main("TSP par recuit simule");
		app.pack();
		app.setVisible(true);

    }
}
