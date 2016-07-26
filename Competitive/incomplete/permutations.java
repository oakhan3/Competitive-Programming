import java.util.Arrays;

public class permutations {
		
	public static void isMember(int number, int n) {
		String temp = Integer.toString(number);
		
		int[] possible = new int[temp.length()];
		
		for (int i = 0; i < temp.length(); i++) {
		    possible[i] = temp.charAt(i) - '0';
		}
		
		Arrays.sort(possible);
		boolean member = true;
		
		for (int k = 0; k < possible.length - 1; k++) {
			if (possible[k] == possible[k + 1]) {
				member = false;
				break;
			}
		}
		
		for (int m = 0; m < possible.length; m++) {
			if (possible[m] > n || possible[m] == 0) {
				member = false;
				break;
			}
		}
	
		if (member)
			System.out.println(number);
	}
	
	public static void main(String[] args) {
		
		int min = 0;
		int max = 0;
		int n = Integer.parseInt(args[0]);
		
		for (int m = n; m > 0; m--)
			max = (max * 10) + m;
		
		for (int p = 1; p <= n; p++)
			min = (min * 10) + p;
		
		for (; min <= max; min++)
			isMember(min, n);
	}
}
		
