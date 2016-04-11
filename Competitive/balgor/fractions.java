import java.util.Scanner;

public class fractions {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextDouble()) {
			double num = scan.nextDouble();
			boolean found = false;

			for (int i = 1; i <= 1000; i++) {
				for (int j = i + 1; j <= 1000; j++) {
					
					if (Math.abs(num - ((double) i / j)) < 0.5e-9) {
						System.out.printf("%.15f %d/%d\n", num, i, j);
						found = true;
						break;
					}	
				}
				
				if (found)
					break;
			}
			
			if (!found) {
				System.out.printf("%.15f -\n", num);
			}
		}
	}
}
