import java.util.Scanner;
import java.util.Stack;

public class monotonic {
	private static String[] polish;
	
	//Calculate the Answer given an X value
	private static double calculate(double xval) {
		Stack<Object> sum = new Stack<>();
		Object one, two;
		
		for (int i = 2; i < polish.length; i++) {
			switch (polish[i]) {
				case "+":
					one = sum.pop();
					two = sum.pop();
					if (one instanceof Double && two instanceof Double)
						sum.push((Double) two + (Double) one);
					else if (one instanceof Double && two instanceof String)
						sum.push(xval + (Double) one);
					else if (one instanceof String && two instanceof Double)
						sum.push((Double) two + xval);
					else if (one instanceof String && two instanceof String)
						sum.push(xval + xval);
					break;	
				case "-":
					one = sum.pop();
					two = sum.pop();
					if (one instanceof Double && two instanceof Double)
						sum.push((Double) two - (Double) one);
					else if (one instanceof Double && two instanceof String)
						sum.push(xval - (Double) one);
					else if (one instanceof String && two instanceof Double)
						sum.push((Double) two - xval);
					else if (one instanceof String && two instanceof String)
						sum.push(xval - xval);
					break;
				case "*":
					one = sum.pop();
					two = sum.pop();
					if (one instanceof Double && two instanceof Double)
						sum.push((Double) two * (Double) one);
					else if (one instanceof Double && two instanceof String)
						sum.push(xval * (Double) one);
					else if (one instanceof String && two instanceof Double)
						sum.push((Double) two * xval);
					else if (one instanceof String && two instanceof String)
						sum.push(xval * xval);
					break;
				case "/":
					one = sum.pop();
					two = sum.pop();
					if (one instanceof Double && two instanceof Double)
						sum.push((Double) two / (Double) one);
					else if (one instanceof Double && two instanceof String)
						sum.push(xval / (Double) one);
					else if (one instanceof String && two instanceof Double)
						sum.push((Double) two / xval);
					else if (one instanceof String && two instanceof String)
						sum.push(xval / xval);
					break;
				case ";":
					break;
				case "x":
					sum.push("x");
					break;
				default: //Must be a double
					sum.push(Double.parseDouble(polish[i]));
					break;
			}
		}
		return (double) sum.pop();
	}
	
	//Solve the equation by checking halves of the solutions space
	private static void binarySolve(double lo, double hi, String[] polish) {
		double mid = (lo + hi)/2.0;
		
		if (round(calculate(mid)) == 0.0) {
			//fine-tune the answer
			double best = Math.abs(calculate(mid));
			double low = mid - 0.000000250;
			double high = mid + 0.000000250;
			double winner = mid;
			
			for (; low < high; low = low + 0.000000001) {
				double diff = Math.abs(calculate(low));
				if (diff < best) {
					winner = low;
					best = diff;
				}
			}
			
			System.out.println(round(winner));
			
			return;
		}
		
		//Similar to BinarySearch
		if (calculate(mid) > 0.0)
			binarySolve(lo, mid, polish);
		else
			binarySolve(mid, hi, polish);
		
		return;
	}
	
	//Return an Answer rounded to 9 Decimal Places
	private static double round(double val) {
	    return Double.parseDouble(String.format("%.9f", val));
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			polish = scan.nextLine().trim().split("\\s+");
			double L = Double.parseDouble(polish[0]);
			double H = Double.parseDouble(polish[1]);
			
			binarySolve(L, H, polish);
		}	
		scan.close();
	}
}
