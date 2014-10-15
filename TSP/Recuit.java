import java.io.IOException;

import javax.swing.*;

// Cette classe dÃ©finit le problÃ¨me du recuit. Il se charge d'effectuer les mutations Ã©lÃ©mentaires, de calculer l'Ã©nergie et de diminuer T...

public class Recuit extends JFrame
{
	private static final long serialVersionUID = 2L;
    static double temperature = 10000;
    static double refroidissement = 0.0001;
    static double K = 1;
    static int boolRecuit = 0;
   
    
    public static void reset()
    {
    	boolRecuit = 0;
    }
    
    // ProbabilitÃ© d'accepter une solution pire que l'actuelle
    public static double probaAcceptation(int energieCourante, int energieNouvelle, double temperature) 
    {
        // Si la nouvelle solution est meilleure, alors on accepte !
        if (energieNouvelle < energieCourante) {
            return 1.0;
        }
        // si elle est pire, on dÃ©finit une proba pour accepter Ã©ventuellement cette solution...
        return Math.exp((energieCourante - energieNouvelle) / (K*temperature));
    }
    
    // Remonte la tempÃ©rature, remet tout en ordre !

    // Cette mÃ©thode est la plus importante : elle implÃ©mente l'algorithme du recuit simulÃ©
    public static Routage solution() throws IOException
    {
    	// On définit une route aléatoire en premier lieu
    	Routage solutionCourante = new Routage();
    	if(boolRecuit == 0)
    	{
    	solutionCourante.routeAleatoire();
    	}
    	else
    	{
    	solutionCourante = IOtxt.Lire();
    	}
    	// On en calcule l'énergie
    	System.out.println("distance solution initiale: " + solutionCourante.getDistance());
    	// et on dit que pour l'instant, c'est la meilleure route !
    	Routage meilleureRoute = new Routage(solutionCourante.getRoute());
    	double temperatureRecuit = temperature;
    	// On répète tant que la température est assez haute
    	while (temperatureRecuit > 1) {
    	// On crée une nouvelle route conçue à partir de l'ancienne
    	Routage nvelleSolution = new Routage(solutionCourante.getRoute());
    	// Sur cette nouvelle route, on effectue une mutation élémentaire (2optMove)
    	MutationAleatoireRoutage.twoOptMove(nvelleSolution);

    	// On récupère l'énergie (distance de parcours) des deux routes
    	int energieCourante = solutionCourante.getDistance();
    	int energieVoisine = nvelleSolution.getDistance();

    	// On décide si on accepte cette nouvelle route comme vu précédemment
    	if (probaAcceptation(energieCourante, energieVoisine, temperatureRecuit) >= Math.random()) {
    		solutionCourante = new Routage(nvelleSolution.getRoute());
    	}

    	// et si cette solution est meilleure que toutes les précédentes, alors on l'enregistre comme étant la meilleure pour l'instant
    	if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
    	meilleureRoute = new Routage(solutionCourante.getRoute());
    	}
    	System.out.println("temp " + temperatureRecuit);
    	System.out.println("     energie " + solutionCourante.getDistance());
    	// puis on refroidit le système
    	temperatureRecuit *= 1-refroidissement;
    	}
    	// Lorsque l'énergie cinétique n'est plus suffisante, on s'arrête et on affiche la solution trouvée
    	System.out.println("distance de la solution trouvee: " + meilleureRoute.getDistance());
    	System.out.println("Tour: " + meilleureRoute);
    	IOtxt.Enregistrer(meilleureRoute);
    	boolRecuit = 1;
    	return meilleureRoute;
    }
     
    // parametrage
    public static void parametrage()
    {
    	DialogueParametres parametrage = new DialogueParametres(null, "parametrages", temperature, refroidissement, K);
    	parametrage.pack();
        parametrage.setVisible(true);
        if (parametrage.okButton()) {
			temperature = parametrage.getTemperature();
			refroidissement = parametrage.getTaux();
			K = parametrage.getK();
		}
    }
}
