/* Omar Khan */
import java.util.Scanner;

public class opnum {
	static String[]  number  
				= {"NOT PAGONG REPRESENTABLE", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
	static String[]  optimal = new String[1001];
	static boolean[] found   = new boolean[1001];
	static int max = 0;

	private static void buildUpTo(int k) {
		for (int i = max; i <= k; i++) {
			if (i <= 10) {
				found[i] = true;
				optimal[i] = number[i];
			}
			else {
				String temp;
				optimal[i] = "one " + optimal[i - 1] + " +";
				
				for (int j = 1; j < i; j++) {
					temp = optimal[i - j] + " " + optimal[j] + " +";
					
					if ((temp.length() <  optimal[i].length()) || 
						(temp.length() == optimal[i].length() && temp.compareTo(optimal[i]) < 0))
						optimal[i] = temp;
				}
							
				for (int j = 2; j < i; j++) {
					if (i % j == 0) {
						temp = optimal[j] + " " + optimal[i / j] + " *";
					
						if ((temp.length() <  optimal[i].length()) || 
							(temp.length() == optimal[i].length() && temp.compareTo(optimal[i]) < 0))
							optimal[i] = temp;
					}
				}
				found[i] = true;
			}	
		}
		max = k;
	}
	
	private static String Pagong(int i) {
		if (found[i])
			return optimal[i];
		else {
			buildUpTo(i);
			return optimal[i];
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int current;

		while (scan.hasNextLine()) {
            current = Integer.parseInt(scan.nextLine());
            assert(current >= 0 && current  <= 1000);
            System.out.println(Pagong(current));
        }
		scan.close();
	}
}
