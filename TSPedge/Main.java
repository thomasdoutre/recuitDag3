
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*
;class Main{
	static int N =100;
	public static Double X = 0.0;
	public static Double Y = 0.0;
	private static BufferedReader br;
	private static Scanner scanner;
	public static ArrayList<Double> coordX = new ArrayList<Double>();
	public static ArrayList<Double> coordY = new ArrayList<Double>();
	public static void main(String[] args) throws IOException{
	int numero = 0;
		int dimension = 0;
		boolean upper = false;
		System.out.println("lecture en cours");
		try {
			File fichier = new File("C:/Users/Alain/workspace/TSPedge/pr1002.tsp");
			Scanner scanner = new Scanner(fichier);
			while (true)
			{
				try
				{
					numero = scanner.nextInt();
					X = scanner.nextDouble();
					Y = scanner.nextDouble();
					coordX.add(X);
					coordY.add(Y);
					System.out.println("Le point numero "+numero+" de coord "+X+","+Y+" a ete charge");
				}
				catch(NoSuchElementException exception)
				{
					break;
				}
			}
			System.out.println(coordX.get(100));
			double[][] matrice = new double[1002][1002];
			for (int i=1;i<=1002;i++)
			{
				for (int j=i+1; j<=1002;j++)
				{
					matrice[i-1][j-1] = Math.sqrt((coordX.get(i-1)-coordX.get(j-1))*(coordX.get(i-1)-coordX.get(j-1)) + (coordY.get(i-1)-coordY.get(j-1))*(coordY.get(i-1)-coordY.get(j-1)));
					matrice[j-1][i-1] = matrice[i-1][j-1];
				}
			}		
			Graphe g = new Graphe(matrice);
			Routage2 route = new Routage2(g);
			ArrayList<Integer> l = route.getRoute();
			try
			{
				for(int i=0; i<N; i++)
				{
					PrintWriter sortie = new PrintWriter(new FileWriter("resultats1."+i+".txt"));
					Recuit.solution(g, l, sortie);
					System.out.println(i);
					sortie.close();
				}
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.err.println("Error: " + e);
		} catch (NumberFormatException e) {
			System.err.println("nombre invalide...");
		}
	}
