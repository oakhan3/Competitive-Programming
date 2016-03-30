package littleBishops;
import java.util.Scanner;

public class littleBishops {
	//n is chessboard size
	//k is # of bishops
	private static int n, k, solution;

	private static void backtrack(int b, boolean[][] attempt, int x, int y) {
		//Move on to the next position without placing a bishop this time
		if (x < (n - 1))
			backtrack(b, attempt, x + 1, y);
		else if (y < (n - 1)) 
			backtrack(b, attempt, 0, y + 1);
				
		//If you can possibly place a Bishop on attempt[x][y]
		if (attempt[x][y] == false) {
			//Place the Bishop by marking the diagonals from position x, y
			//If b + 1 = the number of bishops wanted, add to the solutions list
			//else carry on by 'backtracking' forward
			if (b + 1 == k)
				solution++;
			else {
				if (x < (n - 1))
					backtrack(b + 1, markDiagonal(x, y, attempt), x + 1, y);
				else if (y < (n - 1)) 
					backtrack(b + 1, markDiagonal(x, y, attempt), 0, y + 1);	
			}
		}
	}

	private static boolean[][] markDiagonal(int x, int y, boolean[][] attempt) {
		boolean[][] redo = new boolean[n][];

		for (int i = 0; i < n; i++)
			redo[i] = attempt[i].clone();

		//gives top right
		//int a = x - Math.min(x, y);
		//int b = y - Math.min(x, y);
		//quicker
		int a = x -  ((x < y) ? x :y);
		int b = y -  ((x < y) ? x :y);
		
		while(a < n && b < n)
			redo[a++][b++] = true;
				
		//gives top left
		while (x > 0 && y < n - 1) {
			x--;
			y++;
		}
		
		//x = (x > y) ? x - y : 0;
		//y = (x > y) ? n : y + x; 

		while (x < n && y >= 0)
			redo[x++][y--] = true;
		
		return redo;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			n = scan.nextInt();
			k = scan.nextInt();

			long startTime = System.currentTimeMillis();
			
			solution = 0;
			
			if (n != 0 && k != 0) {
				boolean[][] attempt = new boolean[n][n];
				backtrack(0, attempt, 0, 0);
			}
			
			System.out.println(solution);
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		}
		scan.close();
	}
}