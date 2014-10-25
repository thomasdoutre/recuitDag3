
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Main{
	private static BufferedReader br;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException{
		String thisLine;
		int dimension = 0;
		boolean fullMatrix = false;
		boolean upper = false;
		boolean lower = false;
		try {
			String fichier = "C:/Users/Alain/workspace/TSPedge/bayg29.tsp";
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fichier)));
			String chaine ="";
			boolean doitSArreter = false;
			while (((thisLine = br.readLine()) != null)&&!(doitSArreter)) {
				boolean ligneDeLaMatrice = (thisLine.startsWith(" "));
				for(int j=0; j < 10 ; j++){
					ligneDeLaMatrice = ligneDeLaMatrice || thisLine.startsWith(""+j);
				}
				if(thisLine.startsWith("DISPLAY_DATA_SECTION")){
					doitSArreter = true;
				}
				if (ligneDeLaMatrice){
					chaine = chaine + " " +thisLine;
				}
				if(thisLine.startsWith("DIMENSION")){
					dimension = Integer.parseInt(thisLine.replace("DIMENSION: ", ""));
				}
				if((thisLine.startsWith("EDGE_WEIGHT_FORMAT")) && (thisLine.replace("EDGE_WEIGHT_FORMAT: ", "").startsWith("LOWER_DIAG_ROW"))){
					lower = true;
				}
				if((thisLine.startsWith("EDGE_WEIGHT_FORMAT")) && (thisLine.replace("EDGE_WEIGHT_FORMAT: ", "").startsWith("FULL_MATRIX"))){
					fullMatrix = true;
				}
				if((thisLine.startsWith("EDGE_WEIGHT_FORMAT")) && (thisLine.replace("EDGE_WEIGHT_FORMAT: ", "").startsWith("UPPER_ROW"))){
					upper = true;
				}
			}
			scanner = new Scanner(chaine);
			int[][] matrice = new int[dimension][dimension];
			if(lower){
				for(int j=0; j < dimension ; j++){
					for (int i=0; i <= j ; i++){
						if (scanner.hasNextInt()) {
							matrice[j][i] = scanner.nextInt();
						}
					}
				}
				int[][] mat = matrixAdd(matrice,transposeMatrix(matrice));
				for(int j=0; j < dimension ; j++){
					String afficheMatrice = "";
					for (int i=0; i < dimension ; i++){
						afficheMatrice = afficheMatrice + " "+mat[j][i];
					}
					System.out.println(afficheMatrice);
				}
				Graphe g = new Graphe(mat);
				try {
					Recuit.solution(g);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(fullMatrix) {
				for(int j=0; j < dimension ; j++){
					for (int i=0; i < dimension ; i++){
						if (scanner.hasNextInt()) {
							matrice[j][i] = scanner.nextInt();
						}
					}
				}
				for(int j=0; j < dimension ; j++){
					String afficheMatrice = "";
					for (int i=0; i < dimension ; i++){
						afficheMatrice = afficheMatrice + " "+matrice[j][i];
					}
					System.out.println(afficheMatrice);
				}
				Graphe g = new Graphe(matrice);
				try {
					Recuit.solution(g);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(upper){
				for(int j=0; j < dimension ; j++){
					for (int i=j+1; i <dimension ; i++){
						if (scanner.hasNextInt()) {
							matrice[j][i] = scanner.nextInt();
						}
					}
				}
				for(int j=0; j < dimension ; j++){
					String afficheMatrice = "MATRICE :";
					for (int i=0; i < dimension ; i++){
						afficheMatrice = afficheMatrice + " "+matrice[j][i];
					}
					System.out.println(afficheMatrice);
				}
				int[][] mat = matrixAdd(matrice,transposeMatrix(matrice));
				for(int j=0; j < dimension ; j++){
					String afficheMatrice = "";
					for (int i=0; i < dimension ; i++){
						afficheMatrice = afficheMatrice + " "+mat[j][i];
					}
					System.out.println(afficheMatrice);
				}
				Graphe g = new Graphe(mat);
				try {
					Recuit.solution(g);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e);
		} catch (NumberFormatException e) {
			System.err.println("Invalid number");
		}
	}
	public static int[][] transposeMatrix(int [][] m){
		int[][] temp = new int[m[0].length][m.length];
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[0].length; j++)
				temp[j][i] = m[i][j];
		return temp;
	}
	public static int[][] matrixAdd(int[][] A, int[][] B)
	{
		int[][]C = new int[A.length][A[0].length];
		for(int i =0; i < A.length; i++)
		{
			for(int j=0; j < A[i].length;j++)
			{
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		return C;
	}
}
