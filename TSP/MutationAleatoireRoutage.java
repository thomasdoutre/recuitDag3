// Cette classe dÃ©finit la mutation aleatoire. Il s'agit d'inverser deux noeuds dans la suite des noeuds visites.

public class MutationAleatoireRoutage {

	public static void twoPointsMove(Routage newSolution)
	{
        // On choisit deux positions du parcours au hasard.
        int randIndex1 = (int) (newSolution.tailleRoute() * Math.random());
        int randIndex2 = (int) (newSolution.tailleRoute() * Math.random());

        // On rÃ©cupÃ¨re les noeuds Ã  cet index
        Noeud swapNoeud1 = newSolution.getNoeud(randIndex1);
        Noeud swapNoeud2 = newSolution.getNoeud(randIndex2);

        // On Ã©change ces noeuds dans le parcours.
        newSolution.setNoeud(randIndex2, swapNoeud1);
        newSolution.setNoeud(randIndex1, swapNoeud2);
	}
	
public static void twoOptMove(Routage newSolution)
	{//on opere de la meme maniere sauf que l'on change le chemin de sens (dans le while)
	// On choisit deux positions du parcours au hasard.
	int i;
	int j;
	int randIndex1 = (int) (newSolution.tailleRoute() * Math.random());
	int randIndex2 = (int) (newSolution.tailleRoute() * Math.random());
    // j > i
	if(randIndex1>randIndex2){
		i = randIndex2;
		j = randIndex1;		
	}
	else{
		i = randIndex1;
		j = randIndex2;
	}
	while(i<j){
			// On recupere les noeuds a ces indexs
		Noeud swapNoeud1 = newSolution.getNoeud(i);
		Noeud swapNoeud2 = newSolution.getNoeud(j);
		// On echange ces noeuds dans le parcours.
		newSolution.setNoeud(j, swapNoeud1);
		newSolution.setNoeud(i, swapNoeud2);
		i++;
		j--;
}
}

}
