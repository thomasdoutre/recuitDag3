

public class Gradient {

	// Paramètres de l'algorithme du gradient
	int N; // nombre d'itérations
	double energie;
	
	public Gradient(int N) {
		
		this.N=N;
		
	}
	
	public Probleme iterer(Probleme probleme) {
		
		// Initialisation
		// this.energie = probleme.calculerEnergie();
		// this.meilleureEnergie = this.energie;	
						
		// Itérations
		for(int j=1 ; j<=this.N ; j++) {
			
			// Mutation élémentaire
			probleme.modifElem();
			this.energie = probleme.calculerEnergie();
			// Impression de l'énergie courante
			System.out.println("E = "+(double)((int)(this.energie*1000))/1000);
			
		}

		// Retour de la solution
		return probleme; 
	
	}
}
