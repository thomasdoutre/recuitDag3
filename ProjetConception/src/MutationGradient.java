import java.util.Iterator;


public class MutationGradient implements IMutation {


	public void faire(Probleme probleme) {
		
		Routage routage = (Routage) probleme; 
		int u = routage.utilisateurs.size();
		int n = routage.graphe.n;
		int m = routage.graphe.m;
		Utilisateur UBest = null;
		int jBest = 1;
		int kBest = 1;
		for (Iterator<Utilisateur> l = routage.utilisateurs.iterator(); l.hasNext();) {
			l.next().calculerLatence();
		}
		double E = routage.calculerEnergie();
		double EBest = E;
		
		for (int i = 0; i < u; i++) {
			
			Utilisateur U = routage.utilisateurs.get(i);
			for (int j = 0; j < n; j++) {
				
				Noeud N = U.route.get(j + 1);
				for (int k = 0; k < m; k++) {
					if (((N.num-2)/m != j) || ((N.num-2)%m != k)) {
						U.route.remove(j + 1);
						U.route.add(j + 1, routage.graphe.noeuds[j * m + k + 1]);
						for (Iterator<Utilisateur> l = routage.utilisateurs.iterator(); l.hasNext();) {
							l.next().calculerLatence();
						}
						E = routage.calculerEnergie();
						if (E < EBest) {
							EBest = E;
							UBest = U;
							jBest = j;
							kBest = k;
						}
						U.route.remove(j + 1);
						U.route.add(j + 1, N);
					}
				}
				
			}
			
		}
		
		if(UBest != null) {
			routage.derniereModif = new Modification(UBest.route.get(jBest + 1), routage.graphe.noeuds[jBest * m + kBest + 1], UBest);
			UBest.route.remove(jBest + 1);
			UBest.route.add(jBest + 1, routage.graphe.noeuds[jBest * m + kBest + 1]);
			for (Iterator<Utilisateur> l = routage.utilisateurs.iterator(); l.hasNext();) {
				l.next().calculerLatence();
			}
		}
		
	}


	public void defaire(Probleme probleme) {

		Routage routage = (Routage) probleme; 
		Modification modif = routage.derniereModif;
		modif = new Modification(modif.noeudAj, modif.noeudSup, modif.utilisateurModifie);
		modif.utilisateurModifie.changerChemin(modif);
		for (Iterator<Utilisateur> j = routage.utilisateurs.iterator(); j.hasNext();) {
			j.next().calculerLatence();
		}
		
	}
	
	

}
