import java.util.Arrays;
import java.util.Scanner;

public class bezier {
	private static int m, X, Y, n;
	private static double[][] controls, bpoints;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNext()) {
			String casename = scan.nextLine();
			
			System.out.println(casename);
			
			m = scan.nextInt();
			X = scan.nextInt();
			Y = scan.nextInt();
			n = scan.nextInt();
			
			controls = new double[n][2];
			
			for (int i = 0; i < n; i++) {
				controls[i][0] = scan.nextDouble();
				controls[i][1] = scan.nextDouble();
			}
			
			System.out.println(m + " " + X + " " + Y + " " + n);
			System.out.println(Arrays.deepToString(controls));
			
			//Eat the LineFeed
			if (scan.hasNextLine())
				scan.nextLine();
			
			bpoints = new double[m][2];
			
			for (int i = 0; i < m; i++) {
				bpoints[i][0] = (1.0 - (double) i/m) * bezier((double) i/m, 0, 0, n - 2)
									+ (double) i/m * bezier((double) i/m, 0, 1, n - 1);

				bpoints[i][1] = (1.0 - (double) i/m) * bezier((double) i/m, 1, 0, n - 2)
									+ (double) i/m * bezier((double) i/m, 1, 1, n - 1);
			}
		}
	}

	private static double bezier(double t, int axis, int min, int max) {
		
		
		return 0;
	}
}
