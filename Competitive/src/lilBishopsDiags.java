 import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class lilBishopsDiags {
	//n is chessboard size
	//k is # of bishops
	private static int n, k, solution;
	private static Hashtable<String, Boolean> topRight;
	private static Hashtable<String, Boolean> topLeft;

	private static void backtrack(int b, boolean[][] attempt, int x, int y, ArrayList<Mark> topLeft, ArrayList<Mark> topRight) {
		//Move on to the next position without placing a bishop this time
		if (x < (n - 1))
			backtrack(b, attempt, x + 1, y, topLeft, topRight);
		else if (y < (n - 1)) 
			backtrack(b, attempt, 0, y + 1, topLeft, topRight);
				
		//If you can possibly place a Bishop on attempt[x][y]
		if (attempt[x][y] == false) {
			//Place the Bishop by marking the diagonals from position x, y
			//If b + 1 = the number of bishops wanted, add to the solutions list
			//else carry on by 'backtracking' forward
			if (b + 1 == k)
				solution++;
			else {
				if (x < (n - 1))
					backtrack(b + 1, markDiagonal(x, y, attempt), x + 1, y, topLeft, topRight);
				else if (y < (n - 1)) 
					backtrack(b + 1, markDiagonal(x, y, attempt), 0, y + 1, topLeft, topRight);	
			}
		}
	}
	
	private static void setup() {
		topLeft  = new Hashtable<String, Boolean>();
		topRight = new Hashtable<String, Boolean>();
		
		int a = n-1;
		int b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (a < 0)
				a = 0;
			if (i > ((n*2)-1)/2)
				b++;
			topLeft.put(a+"-"+b, false);
			a--;
		}
		
		a = 0;
		b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (i > ((n*2)-1)/2)
				a++;
			topRight.put(a+"-"+b, false);
			if (b != (n-1))
				b++;
		}
		
		System.out.println("Top Left");
		
		for (String i : topLeft.keySet())
			System.out.print(i + " ");
		
		System.out.println("\nTop Right");
		
		for (String i : topRight.keySet())
			System.out.print(i + " ");
		
		System.out.println();
		
	}

	private static boolean[][] markDiagonal(int x, int y, boolean[][] attempt) {
		boolean[][] redo = new boolean[n][];

		for (int i = 0; i < n; i++)
			redo[i] = attempt[i].clone();

		//gives top left
		//int a = x - Math.min(x, y);
		//int b = y - Math.min(x, y);
		//quicker
		int a = x -  ((x < y) ? x :y);
		int b = y -  ((x < y) ? x :y);
		
		if (topLeft.containsKey(a+"-"+b))
			System.out.println("True\t" + a + "-" + b);
	else
		System.out.println("NOPE");
		
		while(a < n && b < n)
			redo[a++][b++] = true;
				
		//gives top right
		while (x > 0 && y < n - 1) {
			x--;
			y++;
		}
		
		if (topRight.containsKey(x+"-"+y))
				System.out.println("True\t" + x + "-" + y);
		else
			System.out.println("NOPE");
		
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
			
			setup();
			
			if (n != 0 && k != 0) {
				boolean[][] attempt = new boolean[n][n];
				backtrack(0, attempt, 0, 0, new ArrayList<Mark>(), new ArrayList<Mark>());
			}
			
			System.out.println(solution);
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		}
		scan.close();
	}
}

class Mark {
	private int x;
	private int y;

	Mark (int x, int y) {
		this.x = x;
		this.y = y;
	}
}
