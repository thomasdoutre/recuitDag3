import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;


public class Routage{
	  // Parcours de l'homme d'affaire
    public ArrayList<Noeud> route = new ArrayList<Noeud>(); // NE PAS METTRE EN STATIC
    private int distance = 0;
    
    // Construit une route fantÃ´me
    public Routage(){
        for (int i = 0; i < Graphe.nombreDestinations(); i++) {
            route.add(null);
        }
    }
    
    public void clear()
    {
    	route.clear();
    }
    // Construit une route Ã  partir d'un autre
    @SuppressWarnings("unchecked") // Oui Ã§a m'Ã©nervait... Si quelqu'un rÃ©ussit Ã  le supprimer je suis preneur
	public Routage(ArrayList<Noeud> route){
        this.route = (ArrayList<Noeud>) route.clone();
    }
    
    // Renvoie la route empruntÃ©e
    public ArrayList<Noeud> getRoute(){
        return route;
    }

    // RÃ©cupÃ©rer un noeud de la route
    public Noeud getNoeud(int position) {
        return route.get(position);
    }

    // Placer un noeud Ã  une certaine position de la route
    public void setNoeud(int position, Noeud noeud) {
        route.set(position, noeud);
        // On a modifiÃ© la route. Pour le moment on reset la distance et on recalcule tout :-(
        distance = 0; // Ã  modifier avec une matrice des distances !
    }
    
    // CrÃ©e une route alÃ©atoire
    public void routeAleatoire() {
        // Ajouter toutes les destinations possibles au parcours
        for (int index = 0; index < Graphe.nombreDestinations(); index++) {
          setNoeud(index, Graphe.getNoeud(index));
        }
        // rÃ©organise alÃ©atoirement l'ordre de visite
        Collections.shuffle(route);
    }

    // Obtenir la distance totale du tour
    public int getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            // Boucle sur la route
            for (int index=0; index < tailleRoute(); index++) {
                // Obtenir le noeud duquel on part
                Noeud noeudDepart = getNoeud(index);
                // Noeud auquel on arrive
                Noeud noeudArrivee;
                // On vÃ©rifie si le noeud suivant est le noeud de dÃ©part (la boucle est bouclÃ©)
                // si non
                if(index+1 < tailleRoute()){
                    noeudArrivee = getNoeud(index+1);
                }
                // si oui
                else{
                    noeudArrivee = getNoeud(0);
                }
                // On obtient la distance entre les deux villes
                tourDistance += noeudDepart.distanceTo(noeudArrivee);
            }
            distance = tourDistance;
        }
        return distance;
    }

    // Retourne le nombre de visites du parcours
    public int tailleRoute() {
        return route.size();
    }
    
    public String toString() {
        String geneString = "";
        for (int i = 0; i < tailleRoute()-1; i++) {
            geneString += getNoeud(i)+"->";
        }
        geneString += getNoeud(tailleRoute()-1);
        return geneString;
    }
    
    // ReprÃ©sente le parcours
	public void dessiner(Graphics g) 
	{
		for (int index=0; index < tailleRoute(); index++) {
            // Obtenir le noeud duquel on part
            Noeud noeudDepart = getNoeud(index);
            // Noeud auquel on arrive
            Noeud noeudArrivee;
            // On verifie si le noeud suivant est le noeud de depart (la boucle est bouclee)
            // si non
            if(index+1 < tailleRoute()){
                noeudArrivee = getNoeud(index+1);
            }
            // si oui
            else{
                noeudArrivee = getNoeud(0);
            }
            // On obtient la distance entre les deux villes
            g.drawLine(noeudDepart.getCoordX(),noeudDepart.getCoordY(),noeudArrivee.getCoordX(),noeudArrivee.getCoordY());
        }
	}
}

