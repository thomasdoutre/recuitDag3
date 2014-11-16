import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;

// Cette classe dÃ©finit le problÃ¨me du recuit. Il se charge d'effectuer les mutations Ã©lÃ©mentaires, de calculer l'Ã©nergie et de diminuer T...

public class Recuit extends JFrame
{
	private static final long serialVersionUID = 2L;
	static double temperature = 1000;
	static double refroidissement = 0.0001;
	static double K = 0;
	static int nbTours = 1;
	public static ArrayList<Double> DeltaE = new ArrayList<Double>();

	public void setK(double K){
		this.K=K;
	}
	// ProbabilitÃ© d'accepter une solution pire que l'actuelle
	public static double probaAcceptation(double energieCourante, double energieNouvelle, double temperature, PrintWriter sortie) throws IOException 
	{
		// Si la nouvelle solution est meilleure, alors on accepte !
		if (energieNouvelle < energieCourante) {
			saveProba(1,sortie);
			return 1.0;
		}
		// si elle est pire, on dÃ©finit une proba pour accepter Ã©ventuellement cette solution...
		saveProba((double)Math.round((Math.exp((energieCourante - energieNouvelle) / (K*temperature))) * 10000) / 10000,sortie);
		return Math.exp((energieCourante - energieNouvelle) / (K*temperature));
	}

	public static Routage2 solution(Graphe g,ArrayList<Integer> liste,PrintWriter sortie) throws IOException, InterruptedException
	{
		// On définit une route aléatoire en premier lieu
		Routage2 solutionCourante = new Routage2(g);
		int nbit=0;
		PrintWriter printProba = new PrintWriter(new FileWriter("Proba.txt"));
		// On en calcule l'énergie
		// et on dit que pour l'instant, c'est la meilleure route !
		Routage2 meilleureRoute = new Routage2(g);
		Routage2 nvelleSolution = new Routage2(g);
		double temperatureRecuit = temperature;
		int cptTours = nbTours;
		// On répète tant que la température est assez haute
		while (temperatureRecuit > 1) {
			while(cptTours > 0)
			{
			// On crée une nouvelle route conçue à partir de l'ancienne
			nvelleSolution.clone(solutionCourante);
			// Sur cette nouvelle route, on effectue une mutation élémentaire (2optMove)
			nvelleSolution.twoOptMove();
			nbit++;
			
			// On récupère l'énergie (distance de parcours) des deux routes
			double energieCourante = solutionCourante.getDistance();
			double energieVoisine = nvelleSolution.getDistance();

			// On décide si on accepte cette nouvelle route comme vu précédemment
			if (probaAcceptation(energieCourante, energieVoisine, temperatureRecuit,printProba) >= Math.random()) {
				solutionCourante.clone(nvelleSolution);
			}

			if (solutionCourante.getDistance() < meilleureRoute.getDistance()) {
				meilleureRoute.clone(solutionCourante);
			}
			Enregistrer(meilleureRoute.getDistance(),nbit,sortie);
			cptTours-=1;
			}
			cptTours = nbTours;
			temperatureRecuit *= 1-refroidissement;
		}
		printProba.close();
		// Lorsque l'énergie cinétique n'est plus suffisante, on s'arrête et on affiche la solution trouvée
		return meilleureRoute;



	}   
	
	   public static void Enregistrer(double E,int nbit,PrintWriter sortie) throws IOException
	    {    	
	    	sortie.println(E+","+nbit);
	    }
	   public static void saveProba(double proba,PrintWriter sortie) throws IOException
	    {    	
	    	sortie.println(proba+",");
	    }
}