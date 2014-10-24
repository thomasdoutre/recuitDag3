import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import java.io.*;

import java.util.Iterator;

public class ZoneDessin extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 3L;
	
	// On génère une solution et l'ensemble des noeuds (un graphe en fait...) à donner en argument pour dessiner les villes
	private Routage solution = new Routage();
	// private Vector<Noeud> noeuds;
	private Graphe graphe = new Graphe();
    public static int limiteX1 = 0;
    public static int limiteY1 = 0;
    public static int limiteX2 = 500;
    public static int limiteY2 = 500;
    static int limiteCourante = 0;
    
	// Crée la zone de dessin
	public ZoneDessin() {
		super();

		//this.graphe = new Graphe(); // initialise le vecteur noeud
		setPreferredSize(new Dimension(600, 300));

		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder border = BorderFactory.createTitledBorder(blackline,"Carte du Monde"); // le titre, c'est pour le marketing de l'ensemble
		border.setTitleJustification(TitledBorder.CENTER);

		setBorder(border);
		addMouseListener(this);
	}
	
	// Si on appuie sur solution alors... on recherche la solution !
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Solution")) {
			System.out.println("Recherche en cours...");
			Recuit.reset();
			try { solution = Recuit.solution(); }
			catch (IOException e1) {e1.printStackTrace();}
			return;
		}
		if (cmd.equals("Apocatastase")) {
			System.out.println("Destruction du monde...");
			Graphe.detruireMonde();
			return;
		}
		if (cmd.equals("Monde aléatoire")) {
			System.out.println("Création du monde...");
			Graphe.mondeAleatoire(50);
			return;
		}
		if (cmd.equals("Charger monde")) {
			IOtxt.Charger();
			return;
		}
		if (cmd.equals("Lire solution")) {
			System.out.println("ecriture");
				IOtxt.Lire();
				return;
		}
		if (cmd.equals("Affiner la solution")) {
			System.out.println("ecriture");
				try {
					solution = Recuit.solution();
					//solution = Recuit.affinerSolution(IOtxt.Lire());
					repaint();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
		}
		if (cmd.equals("Parametres")) {
			Recuit.parametrage();
				return;
		}
	}
	
	// dessine les noeuds et le graphe.
	protected void paintComponent(Graphics g) {
		Iterator<Noeud> it =  this.graphe.iterator();
		java.awt.Rectangle r = g.getClipBounds();
		((Graphics2D) g).setBackground(Color.white);
		g.clearRect(r.x, r.y, r.width, r.height);
	    g.fillOval(limiteX1 - 3, limiteY1 - 3, 6, 6);
	    g.fillOval(limiteX2 - 3, limiteY2 - 3, 6, 6);
		while (it.hasNext()) {
			Noeud fig = (Noeud) it.next();
			fig.dessiner(g);
		}
		solution.dessiner(g);
		repaint();
	}

	// à chaque clic on crée un noeud aux coordonnées (clic.x, clic.y)
	public void mouseClicked(MouseEvent e) {
		
		int buttonDown = e.getButton();
		 
	    if (buttonDown == MouseEvent.BUTTON1) {
	    	int x = e.getX(), y = e.getY();
			Noeud noeud = new Noeud(x,y);
	    	Graphe.ajouterNoeud(noeud);
			System.out.println("La ville " + Graphe.nombreDestinations()+ " a été créée aux coordonnées ("+noeud.getCoordX()+", "+noeud.getCoordY()+")");
	    }
	    else if(buttonDown == MouseEvent.BUTTON2) 
	    {
	    	int x = e.getX(), y = e.getY();
	    	if(limiteCourante == 0)
	    	{
	    		limiteX1 = x;
	    		limiteY1 = y;
	    		limiteCourante = 1;
	    	}
	    	else if(limiteCourante == 1)
	    	{
	    		limiteX2 = x;
	    		limiteY2 = y;
	    		limiteCourante = 0;
	    	}
	    	repaint();
	    }
	    else if(buttonDown == MouseEvent.BUTTON3) 
	    {
	    	   int x = e.getX(), y = e.getY();
	           for(int i = 0; i < Graphe.nombreDestinations(); i++)
	           {
	        	   Noeud noeudConsidere = Graphe.getNoeud(i);
	        	   int coordX = noeudConsidere.getCoordX();
	        	   int coordY = noeudConsidere.getCoordY();
	        	   if(coordX - 6 <= x && x <= coordX + 6 && coordY - 6 <= y && y <= coordY + 6)
	        	   {
	        		   Graphe.supprimerNoeud(noeudConsidere);
	        		   //Recuit.resetRecuit();
	       			   try {Recuit.reset(); solution = Recuit.solution(); }
					  catch (IOException e1) {e1.printStackTrace();}
	        	   }
	           }
	    }
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
