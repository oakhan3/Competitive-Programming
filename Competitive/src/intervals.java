import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class intervals {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			System.out.println(scan.nextLine());
			
			int n = scan.nextInt();
			
			interval[] vals = new interval[n];
			
			for (int i = 0; i < n; i++) {
				vals[i] = new interval(scan.nextDouble(), scan.nextDouble());
			}
			
			Arrays.sort(vals, new Comparator<interval>() {
				public int compare(interval a, interval b) {
					return (a.a > b.a) ? 1 : (a.a < b.a) ? -1 : 0;
				}
			});
			
			scan.nextLine();
			
			Stack<interval> stack = new Stack<interval>();
			
			stack.push(vals[0]);
			
			for (int i = 1; i < n; i++) {
				interval top = stack.peek();
				
				if (top.b < vals[i].a)
					stack.push(vals[i]);
				else if (top.b < vals[i].b)
					top.b = vals[i].b;
			}
			
			double sum = 0;
			for (interval val : stack) {
				sum += val.length();
			}
						
			System.out.printf("%.3f\n", round(sum, 3));
		}
		
		scan.close();
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}

class interval {
	double a;
	double b;
	
	interval(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public double length() {
		return b - a;
	}
}

