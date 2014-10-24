import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class Utilisateur {

	int num; // identifiant
	double poids;
	LinkedList<Noeud> route; // route actuelle de l'utilisateur
	LinkedList<Noeud> meilleureRoute; // sauvegarde de la route de l'utilisateur dans la meilleure situation 
	double latence;
	double meilleureLatence; // sauvegarde de la latence de l'utilisateur dans la meilleure situation
	
	public Utilisateur(int num, double poids) {
		
		this.num = num;
		this.poids = poids;
		this.latence = 0;
		
	}
	
	public String toString() {
		
		String chemin = "Utilisateur " + this.num + " : ";
		for (Iterator<Noeud> i = this.route.iterator(); i.hasNext();) {
			Noeud noeud = i.next();
			if (i.hasNext()) chemin += noeud.num+" -> ";
			else chemin += noeud.num;
		}
			return chemin;
		
	}
	
	// Méthode attribuant aléatoirement une route à l'utilisateur
	public void  attribuerRouteAleatoire(Routage routage, Random random){
		LinkedList<Noeud> route=new LinkedList<Noeud>();
		Noeud noeud=routage.graphe.noeuds[0];
		route.add(noeud);
		while (noeud.num<routage.graphe.nbNoeuds-1){
			int nbPossiblites = noeud.couts.size();
			int rand = ((int) (random.nextDouble()*nbPossiblites)) + 1;
			int compteur = 1;
			Iterator<Noeud> it = noeud.couts.keySet().iterator();
			while(it.hasNext()){
			  Noeud v = it.next();
			  if (compteur==rand){
				  route.add(v);
				  noeud.couts.put(v,noeud.couts.get(v)+this.poids);
				  noeud=v;
					
			  }
			  compteur++;
			}
		}
		this.route=route;
	}
	
	// Calcul de la latence de l'utilisateur (somme des coûts des arêtes empruntées)
	public void calculerLatence(){
		double latence=0;
		Iterator<Noeud> it = this.route.iterator();
		Noeud noeud = it.next();
		for (Iterator<Noeud> i = it; i.hasNext();){
			if (i.hasNext()) {
				Noeud temp = i.next();
				latence += noeud.couts.get(temp);
				noeud = temp;
			}
		}
		this.latence = latence;
	}
	
	// Modification de la route de l'utilisateur en effectuant une déviation (un noeud est remplacé par un autre)
	public void changerChemin(Modification modif) {
		
		// Mise à jour des coûts des noeuds concernés
		int sup = this.route.indexOf(modif.noeudSup);
		Noeud noeudPrec = this.route.get(sup-1);
		Noeud noeudSuiv = this.route.get(sup+1);
		noeudPrec.couts.put(modif.noeudSup, noeudPrec.couts.get(modif.noeudSup) - this.poids);
		noeudPrec.couts.put(modif.noeudAj, noeudPrec.couts.get(modif.noeudAj) + this.poids);
		modif.noeudSup.couts.put(noeudSuiv, modif.noeudSup.couts.get(noeudSuiv) - this.poids);
		modif.noeudAj.couts.put(noeudSuiv, modif.noeudAj.couts.get(noeudSuiv) + this.poids);
		
		// Suppression des anciens noeuds et attribution des nouveaux
		this.route.remove(modif.noeudSup);
		this.route.add(sup, modif.noeudAj);
		
	}
	
}
