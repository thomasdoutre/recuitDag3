import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;


public class Routage{
	  // Parcours de l'homme d'affaire
    public ArrayList<Noeud> route = new ArrayList<Noeud>();
    private int distance = 0;
    
    // Construit une route fantôme
    public Routage(){
        for (int i = 0; i < Graphe.nombreDestinations(); i++) {
            route.add(null);
        }
    }
    
    public void clear()
    {
    	route.clear();
    }
    // Construit une route à partir d'un autre
    @SuppressWarnings("unchecked") // Oui ça m'énervait... Si quelqu'un réussit à le supprimer je suis preneur
	public Routage(ArrayList<Noeud> route){
        this.route = (ArrayList<Noeud>) route.clone();
    }
    
    // Renvoie la route empruntée
    public ArrayList<Noeud> getRoute(){
        return route;
    }

    // Récupérer un noeud de la route
    public Noeud getNoeud(int position) {
        return route.get(position);
    }

    // Placer un noeud à une certaine position de la route
    public void setNoeud(int position, Noeud noeud) {
        route.set(position, noeud);
        // On a modifié la route. Pour le moment on reset la distance et on recalcule tout :-(
        distance = 0; // à modifier avec une matrice des distances !
    }
    
    // Crée une route aléatoire
    public void routeAleatoire() {
        // Ajouter toutes les destinations possibles au parcours
        for (int index = 0; index < Graphe.nombreDestinations(); index++) {
          setNoeud(index, Graphe.getNoeud(index));
        }
        // réorganise aléatoirement l'ordre de visite
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
                // On vérifie si le noeud suivant est le noeud de départ (la boucle est bouclé)
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
    
    // Représente le parcours
	public void dessiner(Graphics g) 
	{
		for (int index=0; index < tailleRoute(); index++) {
            // Obtenir le noeud duquel on part
            Noeud noeudDepart = getNoeud(index);
            // Noeud auquel on arrive
            Noeud noeudArrivee;
            // On vérifie si le noeud suivant est le noeud de départ (la boucle est bouclé)
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
