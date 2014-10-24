import java.util.ArrayList;
import java.util.Iterator;

/* Cette classe définit le monde, il s'agit en fait de l'ensemble des noeuds (destinations possibles) qui le constituent. */

public class Graphe {

    // Liste des destinations
    private static ArrayList<Noeud> destinations= new ArrayList<Noeud>();

    // Ajouter un noeud à la liste des destinations
    public static void ajouterNoeud(Noeud noeud) {
        destinations.add(noeud);
    }
    
    public static void mondeAleatoire(int nombreDeNoeuds)
    {
    	for(int i = 0; i < nombreDeNoeuds; i++)
    	{
    	Noeud noeud = new Noeud();
    	destinations.add(noeud);
    	}
    }
    
    public static void detruireMonde()
    {
    	for(int i = Graphe.nombreDestinations()-1; i >= 0; i--)
    	{
    		supprimerNoeud(Graphe.getNoeud(i));
    	}
    }
    // Effacer un noeud de la liste des destinations
    public static void supprimerNoeud(Noeud noeud)
    {
    	destinations.remove(noeud);
    }
    
    // Obtenir un noeud
    public static Noeud getNoeud(int index){
        return (Noeud)destinations.get(index);
    }
    
    // Retourne le nombre de destinations
    public static int nombreDestinations(){
        return destinations.size();
    }

	public Iterator<Noeud> iterator()
	{
		Iterator<Noeud> it = new Iterator<Noeud>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < destinations.size() && Graphe.getNoeud(currentIndex) != null;
            }

            @Override
            public Noeud next() {
                return Graphe.getNoeud(currentIndex++);
            }

            @Override
            public void remove() {
                Graphe.supprimerNoeud(Graphe.getNoeud(currentIndex));
            }
        };
        return it;
	}
}

