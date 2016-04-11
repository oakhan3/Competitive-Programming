import java.util.Arrays;
import java.util.Scanner;

public class fractions {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String name = scan.nextLine();
			String nStr = name.substring(2);
			
			//Check Sequence
			String n9Str = nStr.substring(0, 
					(9 > nStr.length()) ? nStr.length() : 9);

			boolean[] seq = new boolean[3];
			Arrays.fill(seq, true);
			
			boolean yup = true;
			int zeros = 0;
			for (int i = 0; i < n9Str.length(); i++) {
				if (n9Str.charAt(i) == '0') {
					zeros++;
				}
				else
					break;
			}
			
			String[] seq2 = n9Str.substring(zeros).split("(?<=\\G.{2})");
			String[] seq3 = n9Str.substring(zeros).split("(?<=\\G.{3})");
			
			for (int i = zeros; i < n9Str.length() - 1; i++) {
				if (n9Str.charAt(i) != n9Str.charAt(i+1)) {
					seq[0] = false;
					break;
				}
			}
			
			if (!seq[0]) {
				for (int i = zeros; i < seq2.length - 1; i++) {
					if (!seq2[i].equals(seq2[i+1])) {
						seq[1] = false;
						break;
					}
				}
			}
			
			if (!seq[1]) {
				for (int i = zeros; i < seq3.length - 1; i++) {
					if (!seq3[i].equals(seq3[i+1])) {
						seq[2] = false;
						break;
					}
				}
			}
			
			int n = 0, d = 0;
			
			if (seq[0]) {
				n = Integer.parseInt(n9Str.charAt(zeros) + "");
				if (zeros != 0) {
					d = (int) (9 * Math.pow(10, zeros));
				}
				else
					d = 9;
			}
			else if (seq[1]) {
				n = Integer.parseInt(n9Str.charAt(zeros) 
						+ "" + n9Str.charAt(1));
				if (zeros != 0) {
					d = (int) (99 * Math.pow(10, zeros));
				}
				else
					d = 99;
			}
			else if (seq[2]) {
				n = Integer.parseInt(n9Str.charAt(zeros) 
						+ "" + n9Str.charAt(zeros+1) 
						+ "" + n9Str.charAt(zeros+2));
				if (zeros != 0) {
					d = (int) (999 * Math.pow(10, zeros));
				}
				else
					d = 999;
			}
			
			System.out.print(name);
			for (int i = name.length(); i < 17; i++) {
				System.out.print("0");
			}
			
			System.out.print(" ");
			int g = gcd(n, d);
			System.out.print((g != 0 ) ? n/g + "/" : "-");
			System.out.print((g != 0 ) ? d/g : "");
			System.out.print("\n");
		}
	}
	
	public static int gcd(int p, int q) {
		while (q != 0) {
			int temp = q;
			q = p % q;
			p = temp;
		}
		return p;
	}
}
