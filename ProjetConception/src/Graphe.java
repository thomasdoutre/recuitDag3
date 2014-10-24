import java.util.Iterator;



public class Graphe {

	Noeud[] noeuds;
	int n; // longueur du graphe
	int m; // hauteur du graphe
	int nbNoeuds;
	
	public Graphe(int n, int m) {
		
		this.n=n;
		this.m=m;
		this.nbNoeuds = n*m+2;
		this.noeuds = new Noeud[n*m+2];
		
		// Création des noeuds
		for (int k=0; k<n*m+2; k++){
			this.noeuds[k] = new Noeud(k);
		}	
	}
	
	
	// Réinitialisation du coût des arêtes à 0
	public void initialiser(){
		
	// Affectation des HashMaps	
	
			// Source
			for (int j = 1; j <= m; j++) {
				this.noeuds[0].couts.put(this.noeuds[j], 0.0);
			}
			
			// Noeuds
			for (int i = 1; i <= n-1; i++) {
			//	for (int j = 0; j < Math.random()*m; j++) {
				for (int j = 1; j <= m; j++) {
					for (int k = 1; k <= m; k++){
						this.noeuds[(i-1)*m+j].couts.put(this.noeuds[i*m+k], 0.0);
					}
				}
			}
			
			// Puits
			for (int j = 1; j <= m; j++){
				this.noeuds[(n-1)*m+j].couts.put(this.noeuds[n*m+1], 0.0);
			}
		//	System.out.println(this);
	}
	
	public String toString(){
		String str = "[";
		for (int i=0; i<n*m+1;i++){
			for (Iterator<Noeud> it = this.noeuds[i].couts.keySet().iterator();it.hasNext();){
				Noeud noeud = it.next();
				str+=(this.noeuds[i].toString()+"->"+noeud.toString()+" , ");
				
			}
		}
		return str+"]";
	}
		
	
}
