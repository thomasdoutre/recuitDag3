import java.util.ArrayList;
import java.util.Collections;
public class Routage2 {
	ArrayList<Integer> route ;
	Graphe g;
	
	public ArrayList<Integer> routeInitiale() {
		int n = g.nombreDeNoeuds();
		ArrayList<Integer> liste = new ArrayList<Integer>();
		for (int index = 0; index < n; index++) {
			liste.add(new Integer(index));
		}
		// rÃ©organise alÃ©atoirement l'ordre de visite
		Collections.shuffle(liste);	
		return liste;
		
	}
	public Routage2 (Graphe g){
		this.g = g;
		this.route=routeInitiale();
	
	}
	
	
	public  Routage2 (Graphe g, ArrayList<Integer> liste){
		this.g=g;
		this.route=liste;
	}
	
	public void clone(Routage2 modele)
	{
		int n = g.nombreDeNoeuds();
		for (int index = 0; index < n; index++){
			this.getRoute().set(index, modele.route.get(index));
		}
	}
	public void clear()
	{
		this.route.clear();
	}
	public Graphe getGraphe(){
		return this.g;
	}
	public int getNext(int index){
		if (index==this.tailleRoute()-1) {
			return this.getRoute().get(0);
		} else {
			int b= this.getRoute().get(index+1);
			return b;
			
		}
	}
	public int getPrevious(int index){
		if (index==0) {
			return  this.getRoute().get(this.tailleRoute() - 1);
		} else {
			return  this.getRoute().get(index-1);
		}
	}
	public int getPreviousIndex(int index){
		if (index==0) {
			return this.tailleRoute() - 1;
		} else {
			return index-1;
		}
	}
		public ArrayList<Integer> getRoute(){
			return route;
		}
		public int tailleRoute() {
			return route.size();
		}

	
		public String toString() {
			int n = g.nombreDeNoeuds();
			String s = "";
			for (int index = 0; index < n; index++){
				s += route.get(index).intValue() + "("+ g.longueurEntre(route.get(index),getPrevious(index)) + ")"+ "->";
			}
			return s;
		}
	public void swap(int i, int j){
		Collections.swap(route,i,j);
	}
	
	//une fois les getters setters crées on attaque les opérations sur les routes
	
// Mutations
	// en theorie une classe mutations devrait etre plus adaptée a notre solution rigide
	
	public void twoOptMove() {
		int randIndex1 = 0;
		int randIndex2 = 0;
		int i;
		int j;
		// On choisit deux positions différentes du parcours au hasard et on les échange. Notons que le cas c2=c1' ne change pas la route et que le cas c1=c1' n'a aucun sens.
		while (randIndex1 == randIndex2 || getPreviousIndex(randIndex1)==randIndex2){
			randIndex1 = (int) (tailleRoute() * Math.random());
			randIndex2 = (int) (tailleRoute() * Math.random());
		}
		
		if(randIndex1>randIndex2){
			i = randIndex2;
			j = randIndex1;		
		}
		else{
			i = randIndex1;
			j = randIndex2;
		}
		while(i<j){
	    swap(i,j);
		i++;
		j--;
		}
	}

	public double getDistance(){
		double cpt=0.0;
		int j=0;
		int L = this.tailleRoute();
		for(int i=0;i<L;i++){
			j=this.getNext((i));
			cpt+=this.getGraphe().getdists()[this.getRoute().get(i)][j];
			
		}//on prend quand meme en consideration la distance dernier -> premier

		return cpt;
		
	}


}
