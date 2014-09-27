import java.util.HashMap;


public class Noeud {

	int num;
	HashMap<Noeud,Double> couts; // voisins du noeud + cout pour les rejoindre
	
	public Noeud(int num) {
		
		this.couts = new HashMap<Noeud,Double>();
		this.num = num;
		
	}
	
	public String toString() {
		
		return (this.num + "");
		
	}
	
}
