import javax.swing.*;

// Cette classe définit le problème du recuit. Il se charge d'effectuer les mutations élémentaires, de calculer l'énergie et de diminuer T...

public class Recuit extends JFrame
{
	private static final long serialVersionUID = 2L;
    static double temperature = 10000;
    static double refroidissement = 0.0001;
	
    public static void resetRecuit()
    {
    	temperature = 10000;
    }
    // Probabilité d'accepter une solution pire que l'actuelle
    public static double probaAcceptation(int energieCourante, int energieNouvelle, double temperature) 
    {
        // Si la nouvelle solution est meilleure, alors on accepte !
        if (energieNouvelle < energieCourante) {
            return 1.0;
        }
        // si elle est pire, on définit une proba pour accepter éventuellement cette solution...
        return Math.exp((energieCourante - energieNouvelle) / temperature);
    }
    
    // Remonte la température, remet tout en ordre !

    // Cette méthode est la plus importante : elle implémente l'algorithme du recuit simulé
    public static Routage solution()
    {
    	// On définit une route aléatoire en premier lieu
    	Routage solutionCourante = new Routage();
    	solutionCourante.routeAleatoire();
    	// On en calcule l'énergie
    	System.out.println("distance solution initiale: " + solutionCourante.getDistance());
    	// et on dit que pour l'instant, c'est la meilleure route !
    	Routage meilleureRoute = new Routage(solutionCourante.getRoute());
    	
    	// On répète tant que la température est assez haute
    	while (temperature > 1) {
    	// On crée une nouvelle route conçue à partir de l'ancienne
    	Routage nvelleSolution = new Routage(solutionCourante.getRoute());
    	// Sur cette nouvelle route, on effectue une mutation élémentaire (2optMove)
    	MutationAleatoireRoutage.twoPointsMove(nvelleSolution);

    	// On récupère l'énergie (distance de parcours) des deux routes
    	int energieCourante = solutionCourante.getDistance();
    	int energieVoisine = nvelleSolution.getDistance();

    	// On décide si on accepte cette nouvelle route comme vu précédemment
    	if (probaAcceptation(energieCourante, energieVoisine, temperature) >= Math.random()) {
    		solutionCourante = new Routage(nvelleSolution.getRoute());
    	}

    	// et si cette solution est meilleure que toutes les précédentes, alors on l'enregistre comme étant la meilleure pour l'instant
    	if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
    	meilleureRoute = new Routage(solutionCourante.getRoute());
    	}

    	// puis on refroidit le système
    	temperature *= 1-refroidissement;
    	}
    	// Lorsque l'énergie cinétique n'est plus suffisante, on s'arrête et on affiche la solution trouvée
    	System.out.println("distance solution trouvée: " + meilleureRoute.getDistance());
    	System.out.println("Tour: " + meilleureRoute);
    	return meilleureRoute;
    }
     
}
