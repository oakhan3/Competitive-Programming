import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class tour {
	private static int n;
	private static double[][] edges;
	private static ArrayList<Integer> minPath;
	private static double minDist;
	
	private static void calcDist (int p1, int p2, double[][] pt) {
		edges[p1][p2] = Math.sqrt(
				((pt[p2][0] - pt[p1][0]) * (pt[p2][0] - pt[p1][0])) +
				((pt[p2][1] - pt[p1][1]) * (pt[p2][1] - pt[p1][1])) +
				((pt[p2][2] - pt[p1][2]) * (pt[p2][2] - pt[p1][2])) +
				((pt[p2][3] - pt[p1][3]) * (pt[p2][3] - pt[p1][3])) +
				((pt[p2][4] - pt[p1][4]) * (pt[p2][4] - pt[p1][4]))
				);
		
		edges[p2][p1] = edges[p1][p2];
	}
	
	private void calcPath() {
		double[][] d = new double[n][n];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				d[i][j] = -1;
			}
		}
		
		for (int i = 0; i < n; i++)
			d[0][i] = 0;
		
		for (int i  = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//d[j][i] = Math.min(len(), b)
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int instance = 0;
		
		while (scan.hasNextInt()) {
			instance++;
			
			n = scan.nextInt();
			
			if (n == 0)
				break;
			
			else {
				double[][] pt = new double[n][5];
				edges = new double[n][n];
			
				for (int i = 0; i < n; i++) 
					for (int j = 0; j < 5; j++)
						pt[i][j] = scan.nextDouble();
				
				for (int i = 0; i < n; i++)
					for (int j = i + 1; j < n; j++)
						calcDist(i , j, pt);
				
				for (int i = 0; i < n; i++)
					edges[i][i] = 0;
			}
			
			System.out.print("Instance " + instance + ": ");
			
			for (int i = 0; i < n - 1; i++) {
				System.out.print(minPath.get(i) + 1 + " ");
			}
			
			System.out.println(n);
		}
		scan.close();
	}
}