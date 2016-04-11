import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * Input: 	number of vertices
 * 			multiple lines in the form x y1 y2 y3 yn...
 * 				where x is the node number and y1 ... yn is the list of edges node x is directed towards
 * 			ie:
 * 				8
 * 				0 2 3 4 5 6
 * 				1 3 6
 * 				2 4 6 7
 * 				3 5 6
 * 				4 5 7
 * 				6 7
 * 				7
 */

public class topsort {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			
			int n = Integer.parseInt(scan.nextLine());
			System.out.println(n);
			
			boolean[][] direct = new boolean[n][n];
			
			//Arrays.fill(direct, '-');
			
			for (int i = 0; i < n; i++) {
				if (scan.hasNextLine()) {
					String[] temp = scan.nextLine().split(" ");
					
					int node = Integer.parseInt(temp[0]);
					//System.out.println(Arrays.deepToString(temp));
					
					for (int j = 1; j < temp.length; j++) {
						direct[node][Integer.parseInt(temp[j])] = true;
					}
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(direct[i][j] ? "t " : "- " );
				}
				System.out.println();
			}
			
			dfs(n, direct);
		}
	}

	private static void dfs(int n, boolean[][] direct) {
		char[] checked = new char[n];
		
		Arrays.fill(checked, 'n');
		
		Stack<Integer> goodstart = findStarter(n, direct);
		
		
		
	}

	private static Stack<Integer> findStarter(int n, boolean[][] direct) {
		boolean[] badstart = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					if (direct[i][j])
						badstart[j] = true;
				}
			}
		}
		
		Stack<Integer> goodstart = new Stack<Integer>();
		
		for (int i = 0; i < n; i++)
			if (!badstart[i])
				goodstart.add(i);
		
		System.out.println(goodstart.toString());
		return goodstart;
	}
}
