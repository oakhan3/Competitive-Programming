/* queens.java by Omar Khan
 * This is a problem to demonstrate the capabilities of the backtracking algorithm
 * 
 * Note: This implementation takes in multiple ints for input
 */

import java.util.Scanner;
import java.util.Stack;

public class queensDisplay {
	static int n;
	static int solution;
	static int[] sol;
	
	private static void printSol() {
		int i;
		System.out.printf("[");
	
		for (i = 1; i <= n; i++)
			System.out.print(" " + sol[i]);
		
		System.out.print(" ]\n");
		
		solution++;
	}
	
	private static Stack<Integer> construct_candidates(int k) {
		int i;
		boolean legal;
		Stack<Integer> pos = new Stack<Integer>();
			
		for (i = 1; i <= n; i++) { //for each position on the board
			legal = true;
			for (int j = 1; j < k; j++) { //going k spaces forward
				if (Math.abs(k - j) == Math.abs(i - sol[j]))
					legal = false;
				else if (i == sol[j])
					legal = false;
			}
			if (legal == true) {
				pos.push(i);
			}
		}
		return pos;
	}
	
	
	private static void backtrack(int k) {
		//if the array is filled print sol
		if (k == n)
			printSol();
		else {
			k = k + 1;
			Stack<Integer> pos = construct_candidates(k);
			int z = pos.size();
			for (int i = 0; i < z; i++) {
				sol[k] = pos.pop();
				backtrack(k);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//Take in an n value
		while (scan.hasNextInt()) {
			n = scan.nextInt();
			
			solution = 0;
			sol = new int[n + 1];
			//backtrack with 0 initially
			backtrack(0);
			System.out.printf("Number of Solutions to %d-Queens = %d\n",n, solution);
		}
		scan.close();
	}

}
