// Cette classe définit la mutation aléatoire. Il s'agit d'inverser deux noeuds dans la suite des noeuds visités.

public class MutationAleatoireRoutage {

	public static void twoPointsMove(Routage newSolution)
	{
        // On choisit deux positions du parcours au hasard.
        int randIndex1 = (int) (newSolution.tailleRoute() * Math.random());
        int randIndex2 = (int) (newSolution.tailleRoute() * Math.random());

        // On récupère les noeuds à cet index
        Noeud swapNoeud1 = newSolution.getNoeud(randIndex1);
        Noeud swapNoeud2 = newSolution.getNoeud(randIndex2);

        // On échange ces noeuds dans le parcours.
        newSolution.setNoeud(randIndex2, swapNoeud1);
        newSolution.setNoeud(randIndex1, swapNoeud2);
	}
	
public static void twoOptMove(Routage newSolution)
	{//on opère de la même manière sauf que l'on change le chemin de sens(dans le while)
	// On choisit deux positions du parcours au hasard.
	int randIndex1 = (int) (newSolution.tailleRoute() * Math.random());
	int randIndex2 = (int) (newSolution.tailleRoute() * Math.random());

	if(randIndex1>randIndex2){
		int i = randIndex2;
		int j = randIndex1;		
	}
	else{
		int i = randIndex1;
		int j = randIndex2;
	}
	while(i<j){
			// On récupère les noeuds à ces indexes
		Noeud swapNoeud1 = newSolution.getNoeud(i);
		Noeud swapNoeud2 = newSolution.getNoeud(j);
		// On échange ces noeuds dans le parcours.
		newSolution.setNoeud(j, swapNoeud1);
		newSolution.setNoeud(i, swapNoeud2);
		i++;
		j--;
}
}
