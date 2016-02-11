
import java.util.Scanner;

public class hops {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N;
		int Network = 0;
		
		//Network Loop
		while (scan.hasNextInt()) {
			Network++;
			N = Integer.parseInt(scan.nextLine());
			
			int[][] Matrix = new int[N][N];
		
			for (int i = 0; i < N; i++)
				Matrix[i][i] = 0;
		
			//Node Input Loop
			while(scan.hasNextInt()) {
				String temp[] = scan.nextLine().split(" ");
				int node1 = Integer.parseInt(temp[0]);
				int node2 = Integer.parseInt(temp[1]);
			
				//If given ending signal
				if (node1 == -1 ||  node2 == -1)
					break;
			
				//If given incorrect input
				if (node1 >= N || node2 >= N)
					continue;
				
				Matrix[node1][node2] = 1;
				Matrix[node2][node1] = 1;
			}
			
			//Set all other nodes to infinity (-1)
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i != j && (Matrix[i][j]) != 1) {
							Matrix[i][j] = -1;
							Matrix[j][i] = -1;
					}
				}
			}
			
			//Find shortest paths for each node
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						
						if (Matrix[i][j] == -1 && Matrix[i][k] != -1 && Matrix[k][j] != -1)
							Matrix[i][j] = Matrix[i][k] + Matrix[k][j];
						
						else if (Matrix[i][j] != -1 && Matrix[i][k] != -1 && Matrix[k][j] != -1)
							if (Matrix[i][j] > Matrix[i][k] + Matrix[k][j]) 
								Matrix[i][j] = Matrix[i][k] + Matrix[k][j];
					}
				}
			}

			//Print out formatted Network
			System.out.println("NETWORK " + Network);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (Matrix[i][j] == -1)
						System.out.print(String.format("%4s", "-"));
					else
						System.out.print(String.format("%4s", Matrix[i][j]));
				}
				System.out.println();
			}
		}
	}
}
