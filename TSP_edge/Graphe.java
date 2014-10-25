
public class Graphe {

	// Un graphe est défini par une matrice carrée représentant les distances entre chaque noeud
	int[][] longueursAretes;
	
	//Constructeur
	public Graphe(int[][] tab) {
		longueursAretes = tab;
	}
	public int[][] getdists(){
		return this.longueursAretes;
	}
	
	//Fournit la longueur entre 2 noeuds
	public double longueurEntre(int index1, int index2) {
		return longueursAretes[index1][index2];
	}
	
	//Renvoie le nombre de noeuds du graphe
	public int nombreDeNoeuds() {
		return longueursAretes.length;
	}

}
