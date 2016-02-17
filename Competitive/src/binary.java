import java.util.Scanner;

public class binary {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNext()) {
			
			String bin1 = scan.next();
			if (scan.hasNext()) {
			
			String bin2 = scan.next();
			
			int n = Math.max(bin1.length(), bin2.length()) + 1;
			
			//Append spaces to even out each line length to (n - 1)
			if (bin1.length() > bin2.length()) {
				StringBuilder buffer = new StringBuilder();
				for (int i = 0; i < bin1.length() - bin2.length() + 1; i++)
					buffer.append(' ');
				buffer.append(bin2);
				bin2 = buffer.toString();
				bin1 = " " + bin1;
			}
			else if (bin2.length() > bin1.length()) {
				StringBuilder buffer = new StringBuilder();
				for (int i = 0; i < bin2.length() - bin1.length() + 1; i++)
					buffer.append(' ');
				buffer.append(bin1);
				bin1 = buffer.toString();
				bin2 = " " + bin2;
			}
			else {
				bin1 = " " + bin1;
				bin2 = " " + bin2;
			}
			
			//Create a 2D Array for Sum and Carry
			char[][] print = new char[2][n];
			
			for(int i = 0; i < n; i++) {
				print[0][i] = ' ';
				print[1][i] = ' ';
			}
			
			//Calculate Sum and Carry
			int k = 0;
			
			for (int j = n - 1; j > 0; j--) {
				if (print[0][j] == '1')
					k++;
				if (bin1.charAt(j) == '1')
					k++;
				if (bin2.charAt(j) == '1')
					k++;
				
				if (k == 3) {
					print[1][j] = '1';
					print[0][j - 1] = '1';
				}
				else if (k == 2) {
					print[1][j] = '0';
					print[0][j - 1] = '1';
				}
				else if (k == 1) {
					print[1][j] = '1';
					print[0][j - 1] = ' ';
				}
				else {
					print[1][j] = '0';
					print[0][j - 1] = ' ';
				}
				k = 0;	
			}
			
			//Append the first number to the Sum
			print[1][0] = (print[0][0] == '1') ? '1' : '0';
			
			//Print out the Calculation following Specific Format
			System.out.println("BINARY SUM:");
			
			for (int q = 0; q < n; q++) {
				System.out.print(print[0][q]);
			}
			System.out.println();
			
			System.out.println(bin1);
			System.out.println(bin2);
			
			for (int m = 0; m < n; m++) {
				System.out.print("-");
			}
			
			System.out.println();
			
			for (int p = 0; p < n; p++) {
				System.out.print(print[1][p]);
			}
			
			System.out.println("\n");
			}
		}
		scan.close();
	}
}
