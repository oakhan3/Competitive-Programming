package littleBishops;

import java.util.Scanner;

public class subsetSums {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextInt()) {
			int N = scan.nextInt();
			
			for (int i = 0b0000_0000; i <= N; i += 0b1) {
				System.out.printf("%8s\n", Integer.toBinaryString(i));
			}
		}
		
		scan.close();
	}
}
