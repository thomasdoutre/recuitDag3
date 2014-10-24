import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/*
 * Problème du routage
 * Cette classe implémente la classe abstraite Probleme
 */

public class Routage extends Probleme {

	// Paramètres du problème
	public LinkedList<Utilisateur> utilisateurs;
	public Graphe graphe;
	
	// Sauvegarde de la dernière modification effectuée
	public Modification derniereModif;
	
	
	public Routage(Graphe graphe, LinkedList<Utilisateur> utilisateurs, IEnergie E, IMutation mutation) {
		
		this.graphe = graphe;
		this.utilisateurs = utilisateurs;
		this.derniereModif = null;
		this.E = E;
		this.mutation = mutation;
		
	}
	
	
	// Initialisation du problème: affectation de routes aléatoires
	public void initialiser(Random random){
		
		this.graphe.initialiser();
		
		for(Iterator<Utilisateur> i = this.utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.attribuerRouteAleatoire(this, random);
			u.meilleureRoute=new LinkedList<Noeud>();
		}
		
	}
	

	// Sauvegarde du routage actuel dans une variable
	@SuppressWarnings("unchecked")
	public void sauvegarderSolution(){
		for(Iterator<Utilisateur> i = this.utilisateurs.iterator(); i.hasNext();){
			Utilisateur u = i.next();
			u.meilleureLatence=u.latence;
			u.meilleureRoute=(LinkedList<Noeud>) u.route.clone();
		}
	}
	
}
