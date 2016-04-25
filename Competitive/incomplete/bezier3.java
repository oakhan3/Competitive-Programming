import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.Stack;

public class bezier3 {
	private static double[][] bpoints, controls;
	private static boolean[][] bdone;
	private static Stack<Object> ops;
/*
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNext()) {
			System.out.println(scan.nextLine());
			
			int m = scan.nextInt();
			int X = scan.nextInt();
			int Y = scan.nextInt();
			int n = scan.nextInt();
			
			controls = new double[n][2];
			
			for (int i = 0; i < n; i++) {
				controls[i][0] = scan.nextDouble();
				controls[i][1] = scan.nextDouble();
			}
			
			//Eat the LineFeed
			if (scan.hasNextLine())
				scan.nextLine();
			
			bdone = new boolean[n][n];
			bpoints = new double[n][n];
			
			double[][] braw = new double[m][2];

			int a = 0;
			ops = new Stack<Object>();
			
			for (double t = 0, i = 0; i < m; t = (++i) / m) {
				
				braw[a][0] = bezier1(t, 1-t, 0, n - 1, 0);

				bdone = new boolean[n][n];
				
				braw[a++][1] = bezier1(t, 1-t, 0, n - 1, 1);
				
				bdone = new boolean[n][n];
			}
			
			for (int i = 0; i < m; i++) {
				if (i % 5 == 0 && i != 0)
					System.out.println();
				System.out.printf(" %4.1f %4.1f", round(braw[(int) i][0], 1), round(braw[(int) i][1], 1));
			}
			
			boolean[][] asterisks = new boolean[Y+1][X+1];
			boolean[][] pluses = new boolean[Y+1][X+1];
			
			for (int i = 0; i < n; i++) {
				int row = (int) (Y - controls[i][1] + 0.5);
				int column = (int) (1.5 + controls[i][0]);
				
				pluses[row][column] = true;
			}
			
			for (int i = 0; i < m; i++) {
				int row = (int) (Y - braw[i][1] + 0.5);
				int column = (int) (1.5 + braw[i][0]);
				
				asterisks[row][column] = true;
			}
		
			System.out.println();
			
			for (int i = 1; i < Y + 1; i++) {
				for (int j = 1; j < X + 1; j++) { 
					System.out.print(pluses[i][j] ? "+" : asterisks[i][j] ? "*" : " ");
				}
				System.out.println();
			}
		}
		scan.close();
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	private static double bezier1(double t, double comp, int min, int max, int axis) {
		if (max - min == 1) {
			ops.push("+");
			ops.push("*");
			ops.push("comp");
			ops.push(controls[min][axis]);
			ops.push("*");
			ops.push("t");
			ops.push(controls[max][axis]);
			
			bpoints[min][max] = comp * controls[min][axis] + t * controls[max][axis];
			bdone[min][max] = true;
			return bpoints[min][max];
		}
		else {
			ops.push("+");
			ops.push("*");
			ops.push("comp");
			bezier1(t, comp, min, max - 1, axis);
			ops.push("*");
			ops.push("t");
			bezier1(t, comp, min + 1, max, axis);
			
		}
	}
	*/
}
