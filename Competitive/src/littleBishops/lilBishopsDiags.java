package littleBishops;
import java.util.Hashtable;
import java.util.Scanner;

public class lilBishopsDiags {
	//n is chessboard size
	//k is # of bishops
	private static int n, k, solution;

	private static void backtrack(int bish, int x, int y, Hashtable<Double, Boolean> topLeft, Hashtable<Double, Boolean> topRight) {
		//Move on to the next position without placing a bishop this time
		if (x < (n - 1))
			backtrack(bish, x + 1, y, topLeft, topRight);
		else if (y < (n - 1)) 
			backtrack(bish, 0, y + 1, topLeft, topRight);
		
		int a = x -  ((x < y) ? x :y);
		int b = y -  ((x < y) ? x :y);
		 
		int c = x;
		int d = y;
		//gives top right
		while (c > 0 && d < n - 1) {
			c--;
			d++;
		}
		
		double left = .5*(a + b)*(a + b + 1) + b;
		double right = .5*(c + d)*(c + d + 1) + d;
				
		//If you can possibly place a Bishop on attempt[x][y]
		if (topLeft.get(left) == false && topRight.get(right) == false) {
			//Place the Bishop by marking the diagonals from position x, y
			//If b + 1 = the number of bishops wanted, add to the solutions list
			//else carry on by 'backtracking' forward
			if (bish + 1 == k)
				solution++;
			else {
				topLeft.put(left, true);
				topRight.put(right, true);
				
				if (x < (n - 1))
					backtrack(bish + 1, x + 1, y, topLeft, topRight);
				else if (y < (n - 1)) 
					backtrack(bish + 1, 0, y + 1, topLeft, topRight);
				
				topLeft.put(left, false);
				topRight.put(right, false);
			}
		}
	}
	
	private static void setup() {
		Hashtable<Double, Boolean> topLeft  = new Hashtable<Double, Boolean>();
		Hashtable<Double, Boolean> topRight = new Hashtable<Double, Boolean>();
		
		int a = n-1;
		int b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (a < 0)
				a = 0;
			if (i > ((n*2)-1)/2)
				b++;
			topLeft.put(.5*(a + b)*(a + b + 1) + b, false);
			a--;
		}
		
		a = 0;
		b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (i > ((n*2)-1)/2)
				a++;
			topRight.put(.5*(a + b)*(a + b + 1) + b, false);
			if (b != (n-1))
				b++;
		}
		
		backtrack(0, 0, 0, topLeft, topRight);
		
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			n = scan.nextInt();
			k = scan.nextInt();

			long startTime = System.currentTimeMillis();
			
			solution = 0;
			
			if (n != 0 && k != 0) {
				setup();
			}
			
			System.out.println(solution);
			long endTime = System.currentTimeMillis();

			System.out.println("That took " + (endTime - startTime) + " milliseconds");
		}
		scan.close();
	}
}