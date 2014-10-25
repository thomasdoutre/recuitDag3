import java.io.IOException;

import javax.swing.*;

// Cette classe dÃ©finit le problÃ¨me du recuit. Il se charge d'effectuer les mutations Ã©lÃ©mentaires, de calculer l'Ã©nergie et de diminuer T...

public class Recuit extends JFrame
{
	private static final long serialVersionUID = 2L;
	static double temperature = 10000;
	static double refroidissement = 0.0001;
	static double K = 1;




	// ProbabilitÃ© d'accepter une solution pire que l'actuelle
	public static double probaAcceptation(int energieCourante, int energieNouvelle, double temperature) 
	{
		// Si la nouvelle solution est meilleure, alors on accepte !
		if (energieNouvelle < energieCourante) {
			return 1.0;
		}
		// si elle est pire, on dÃ©finit une proba pour accepter Ã©ventuellement cette solution...
		return Math.exp((energieCourante - energieNouvelle) / (1*temperature));
	}

	// Remonte la tempÃ©rature, remet tout en ordre !

	// Cette mÃ©thode est la plus importante : elle implÃ©mente l'algorithme du recuit simulÃ©
	public static Routage2 solution(Graphe g) throws IOException, InterruptedException
	{
		// On définit une route aléatoire en premier lieu
		Routage2 solutionCourante = new Routage2(g);

		// On en calcule l'énergie
		System.out.println("distance solution initiale: " + solutionCourante.getDistance());
		Thread.sleep(2000);
		// et on dit que pour l'instant, c'est la meilleure route !
		Routage2 meilleureRoute = new Routage2(g);
		Routage2 nvelleSolution = new Routage2(g);
		double temperatureRecuit = temperature;
		// On répète tant que la température est assez haute
		while (temperatureRecuit > 1) {
			// On crée une nouvelle route conçue à partir de l'ancienne
			nvelleSolution.clone(solutionCourante);
			// Sur cette nouvelle route, on effectue une mutation élémentaire (2optMove)
			nvelleSolution.twoOptMove();

			// On récupère l'énergie (distance de parcours) des deux routes
			int energieCourante = solutionCourante.getDistance();
			int energieVoisine = nvelleSolution.getDistance();

			// On décide si on accepte cette nouvelle route comme vu précédemment
			if (probaAcceptation(energieCourante, energieVoisine, temperatureRecuit) >= Math.random()) {
				solutionCourante.clone(nvelleSolution);
			}

			if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
				meilleureRoute.clone(solutionCourante);
			
			}
			// et si cette solution est meilleure que toutes les précédentes, alors on l'enregistre comme étant la meilleure pour l'instant
			
			System.out.println(" Energie " + solutionCourante.getDistance() + " Temperature " + temperatureRecuit);
			//Thread.sleep(1000);
			temperatureRecuit *= 1-refroidissement;
		}
		// Lorsque l'énergie cinétique n'est plus suffisante, on s'arrête et on affiche la solution trouvée
		System.out.println("distance de la solution trouvee: " + meilleureRoute.getDistance());
		System.out.println("Tour: " + meilleureRoute);
		return meilleureRoute;



	}   
}
