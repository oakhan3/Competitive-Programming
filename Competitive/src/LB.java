import java.util.Scanner;

public class LB {
	static int n, k;
	static int solution;
	static boolean[][] sol;
	
	private static void backtrack(int b, boolean[][] attempt, int x, int y) {
		if (attempt[x][y] == true) {
			boolean[][] redo = new boolean[n][];
			
			for (int i = 0; i < n; i++) {
				redo[i] = attempt[i].clone();
			}
			
			markDiagonal(x, y, attempt);
			
			if (b + 1 == k)
				solution++;
						
			 if (x < (n - 1))
				backtrack(b + 1, attempt, x + 1, y);
			 else if (y < (n - 1)) 
				backtrack(b + 1, attempt, 0, y + 1);
				 
			 for (int i = 0; i < n; i++)
					attempt[i] = redo[i].clone();
		}
			
		 if (x < (n - 1))
			backtrack(b, attempt, x + 1, y);
		 else if (y < (n - 1)) 
			backtrack(b, attempt, 0, y + 1);
	}
	
	private static void markDiagonal(int x, int y, boolean[][] attempt) {
		int i = x;
		int j = y;
		
		while (i >= 0 && j >= 0) {
			attempt[i--][j--] = false;
		}
		
		i = x;
		j = y;
		
		while(i < n && j < n) {
			attempt[i++][j++] = false;
		}
		
		i = x;
		j = y;
		
		while (i >= 0 && j < n)
			attempt[i--][j++] = false;
		
		i = x;
		j = y;
		
		while (i < n && j >= 0)
			attempt[i++][j--] = false;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			n = scan.nextInt();
			k = scan.nextInt();
			
			if (n != 0 && k != 0) {
				solution = 0;
				
				boolean[][] attempt = new boolean[n][n];
				
				for (int i = 0; i < n; i ++) {
					for (int j = 0; j < n; j++)
						attempt[i][j] = true;
				}
				
				backtrack(0, attempt, 0, 0);
				System.out.printf("%d\n", solution);
			}
		}
		scan.close();
	}
}
