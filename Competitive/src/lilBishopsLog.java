import java.util.ArrayList;
import java.util.Scanner;

public class lilBishopsLog {
	//n is chessboard size
	//k is # of bishops
	private static int n, k;
	private static ArrayList<boolean[][]> solution;

	private static void backtrack(int b, boolean[][] attempt, int x, int y, boolean[][] positions) {
		//Move on to the next position without placing a bishop this time
		if (x < (n - 1))
			backtrack(b, attempt, x + 1, y, positions);
		else if (y < (n - 1)) 
			backtrack(b, attempt, 0, y + 1, positions);
				
		//If you can possibly place a Bishop on attempt[x][y]
		if (attempt[x][y] == false) {
			//Place the Bishop by marking the diagonals from position x, y
			//If b + 1 = the number of bishops wanted, add to the solutions list
			//else carry on by 'backtracking' forward
			if (b + 1 == k) {
				boolean[][] redo = new boolean[n][];

				for (int i = 0; i < n; i++)
					redo[i] = positions[i].clone();
				solution.add(redo);
			}
			else {
				positions[x][y] = true; //there is a bishop here
				if (x < (n - 1))
					backtrack(b + 1, markDiagonal(x, y, attempt), x + 1, y, positions);
				else if (y < (n - 1)) 
					backtrack(b + 1, markDiagonal(x, y, attempt), 0, y + 1, positions);	
				positions[x][y] = false; //there is a bishop here
			}
		}
	}

	private static boolean[][] markDiagonal(int x, int y, boolean[][] attempt) {
		boolean[][] redo = new boolean[n][];

		for (int i = 0; i < n; i++)
			redo[i] = attempt[i].clone();

		//gives top right
		int a = x -  ((x < y) ? x :y);
		int b = y -  ((x < y) ? x :y);
		
		while(a < n && b < n)
			redo[a++][b++] = true;
				
		//gives top left
		while (x > 0 && y < n - 1) {
			x--;
			y++;
		}
		
		while (x < n && y >= 0)
			redo[x++][y--] = true;
		
		return redo;
	}

	private static boolean check (boolean[][] attempt1, boolean[][]attempt2) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (attempt1[i][j] == true) //A bishops path is here
					if (attempt2[i][j] == false) //
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			n = scan.nextInt();
			k = scan.nextInt();

			long startTime = System.currentTimeMillis();
			
			solution = new ArrayList<>();
			
			if (n != 0 && k != 0) {
				backtrack(0, new boolean[n][n], 0, 0, new boolean[n][n]);
			}
			
			for (boolean[][] i : solution) {
				for (int j = 0; j < n; j++) {
					for (int h = 0; h < n; h++)
						System.out.print(i[j][h] + " ");
					System.out.println();
				}
			}
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		}
		scan.close();
	}
}