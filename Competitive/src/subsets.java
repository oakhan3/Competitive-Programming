/* subsets.java by Omar Khan
 * This is a problem to demonstrate the capabilities of the backtracking algorithm
 * 
 * Note: This implementation takes in multiple ints for input
 */
import java.util.Scanner;

public class subsets {
	static int n;
	
	private static void printSol(boolean[] sol, int k) {
		int i;
		System.out.printf("(");
	
		for (i = 1; i <= k; i++)
			if (sol[i] == true) 
				System.out.print(" " + i);
		
		System.out.print(" )");
		
		//For better formatting:
		if (n > 4)
			System.out.println();
	}
	
	private static void backtrack(boolean[] sol, int k) {
		//if the array is filled print sol
		if (k == n)
			printSol(sol, k);
		else {
			k = k + 1;
			sol[k] = true;
			backtrack(sol, k);
			sol[k] = false;
			backtrack(sol, k);
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//Take in an n value
		while (scan.hasNextInt()) {
			n = scan.nextInt();
			System.out.printf("Subsets of n = %d: \n{ ", n);
			//create a vector array
			boolean[] sol = new boolean[n+1];
			//backtrack with 0 initially
			backtrack(sol, 0);
			System.out.println(" }");
		}
		scan.close();
	}

}
