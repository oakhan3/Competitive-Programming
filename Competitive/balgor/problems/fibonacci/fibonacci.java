

import java.util.Scanner;

public class fibonacci {

	public static void main(String[] args)  {
		Scanner scanner = new Scanner(System.in);

		while(scanner.hasNextLine()) {
			int i1 = Integer.parseInt(scanner.nextLine());
			System.out.println(i1);
			int j = fib(i1);
			System.out.println("fibonacci=" + j);

		}
	}

	private static int fib(int i) {
		System.out.println("fibonacci(" + i + ")");
		if (i == 0) 
			return 0;
		return fib_helper(i, 1, 0, 1);

	}

	private static int fib_helper(int n, int m, int minus, int current) {

		System.out.printf("fibonacci_helper(%d,%d,%d,%d)\n", n, m, minus, current);

		int k;

		if (n == m) {
			k = current;
			System.out.println("fibonacci_helper=" + k);
			return k;
		} 
		else {
			k = fib_helper(n, m + 1, current, current + minus);
			System.out.println("fibonacci_helper=" + k);
			return k;
		}
	}




}

