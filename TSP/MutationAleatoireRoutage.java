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
}
