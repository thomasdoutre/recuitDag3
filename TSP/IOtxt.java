import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOtxt {

	static int coordX;
    static int coordY;
    static int numero;
    
	public static void Charger()
	{
		System.out.println("chargement d'un txt");
		try
		{
		    File f = new File ("monde.txt");
		    Scanner scanner = new Scanner (f);

		    while (true)
		    {
		        try
		        {
		        	numero = scanner.nextInt();
		            coordX = scanner.nextInt();
		            coordY = scanner.nextInt();
		            Noeud noeud = new Noeud(10*coordX, 10*coordY);
			    	Graphe.ajouterNoeud(noeud);
		            System.out.println ("Le point numero "+numero+" de coord ("+coordX+","+coordY+") a ete charge");
		        }
		        catch (NoSuchElementException exception)
		        {
		            break;
		        }
		    }
		 
		    scanner.close();
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas ete trouve");
		}
		return;
	}
	
	public static Routage Lire()
	{
		Routage solution = new Routage();
    	try
    	{
    		File f = new File ("solution.txt");
    		Scanner scanner = new Scanner (f);
            while(true)
            {
            	try
            	{
		             for (int index = 0; index < Graphe.nombreDestinations(); index++) 
		             {
		            	 coordX = scanner.nextInt();
				         coordY = scanner.nextInt();
				         Noeud noeud = new Noeud(coordX, coordY);
		                 solution.setNoeud(index, noeud);
		              }
            	}
            	catch (NoSuchElementException exception)
		        {
		            break;
		        }
            }
            scanner.close();
            System.out.println("issu du txt: " + solution);
    	}
    	catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas ete trouve");
		}
    	return solution;
	}

	   public static void Enregistrer(Routage route) throws IOException
	    {
	    	PrintWriter sortie = new PrintWriter(new FileWriter("solution.txt"));
	    	for (int index=0; index < route.tailleRoute(); index++)
	    	{
	    		coordX = route.getNoeud(index).CoordX;
	    		coordY = route.getNoeud(index).CoordY;
	    		sortie.println(coordX + " " + coordY);
	    	}
	    	sortie.close();
	    }
}
