import java.awt.Graphics;

// Cette classe définit la brique élémentaire du problème : le noeud que doit visiter l'homme d'affaires.

public class Noeud {
	    int CoordX;
	    int CoordY;
	    
	    // Construire un nouveau noeud avec des coordonnées aléatoires
	    public Noeud(){
	        this.CoordX = (int)(Math.random()*600);
	        this.CoordY = (int)(Math.random()*300);
	    }
	    
	    // Construire un nouveau noeud aux coordonnées (x,y)
	    public 	Noeud(int CoordX, int CoordY){
	        this.CoordX = CoordX;
	        this.CoordY = CoordY;
	    }
	    
	    // Renvoie la coordonnée en X
	    public int getCoordX(){
	        return this.CoordX;
	    }
	    
	    // Renvoie la coordonnée en Y
	    public int getCoordY(){
	        return this.CoordY;
	    }
	    
	    // Renvoie la distance entre le noeud courant et le noeud passé en argument
	    public double distanceTo(Noeud noeud){
	        int xDistance = Math.abs(getCoordX() - noeud.getCoordX());
	        int yDistance = Math.abs(getCoordY() - noeud.getCoordY());
	        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
	        
	        return distance;
	    }
	    
	    // Affichage du noeud sous la forme (x,y)
	    public String toString()
	    {
	        return "(" +getCoordX()+", "+getCoordY() + ")";
	    }
	    
	    // Représenter le noeud.
		public void dessiner(Graphics g) 
		{
			g.drawOval(this.CoordX-3,this.CoordY-3,6,6);
		}
}
