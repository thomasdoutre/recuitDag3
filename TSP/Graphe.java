public class Graphe {

	// Un graphe est défini par une matrice carrée représentant les distances entre chaque noeud
	double[][] matriceDesDistances;

	//Constructeur
	public Graphe(double[][] tab) {
		matriceDesDistances = tab;
	}
	
	public double[][] getdists(){
		return this.matriceDesDistances;
	}

	//Fournit la longueur entre 2 noeuds
	public double longueurEntre(int index1, int index2) {
		return matriceDesDistances[index1][index2];
	}

	//Renvoie le nombre de noeuds du graphe
	public int nombreDeNoeuds() {
		return matriceDesDistances.length;
	}

}