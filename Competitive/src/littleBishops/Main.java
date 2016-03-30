package littleBishops;
import java.io.*;
import java.util.*;

class Main {
	static int n, k, solution;

	static void backtrack(int b, boolean[][] attempt, int x, int y) {
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

	static boolean[][] markDiagonal(int x, int y, boolean[][] attempt) {
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

	static String ReadLn (int maxLg)  // utility function to read from stdin
	{
		byte lin[] = new byte [maxLg];
		int lg = 0, car = -1;
		String line = "";

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n')) break;
				lin [lg++] += car;
			}
		}
		catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0)) return (null);  // eof
		return (new String (lin, 0, lg));
	}

	public static void main (String args[])  // entry point from OS
	{
		Main myWork = new Main();  // create a dinamic instance
		myWork.Begin();            // the true entry point
	}

	void Begin() {
		String input;
		StringTokenizer idata;

		while ((input = Main.ReadLn (255)) != null) {
			idata = new StringTokenizer (input);
			n = Integer.parseInt (idata.nextToken());
			k = Integer.parseInt (idata.nextToken());
			solution = 0;

			if (n != 0 && k != 0) {
				boolean[][] attempt = new boolean[n][n];
				backtrack(0, attempt, 0, 0);
			}
			System.out.println(solution);
		}
	}
}