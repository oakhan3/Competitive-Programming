import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class tour {
	private static int n;
	private static double[][] edges;
	private static ArrayList<Integer> minPath;
	private static double minDist;
	//private static HashMap<String, Double> map;
	
	@SuppressWarnings("unchecked")
	private static void backtrack(ArrayList<Integer> path, double dist) {
		if (path.size() == n - 1) {
			dist = generatePath(path);
			
			if (dist < minDist) {
				minPath = (ArrayList<Integer>) path.clone();
				minDist = dist;
			}
		}
		else {
			if (dist > minDist)
				return;
			
			for (int i = 1; i < n - 1; i++) {
				if (!path.contains(i)) {
					path.add(i);
					backtrack(path, dist);
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	private static double generatePath(ArrayList<Integer> path) {
		double result = 0;

		for (int i = 0; i < n - 2; i++) {
			result += edges[path.get(i)][path.get(i + 1)];
		}
		
		result += edges[path.get(n - 2)][n - 1];
		
		return result;
	}
	
	

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
				
				for (int i = 0; i < n - 1; i++)
					minDist += edges[i][i+1];
				
				ArrayList<Integer> path = new ArrayList<Integer>();
				path.add(0);
				backtrack(path, 0);			
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