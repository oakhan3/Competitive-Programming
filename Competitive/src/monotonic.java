import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class monotonic {
	private static Stack<Object> sum;
	private static double L, H;	
	private double pow;
	private double cons;
	
	monotonic(double constant, double power) {
		this.cons = constant;
		this.pow = power;
	}

	public String toString() {
		return "x^" + this.pow; 
	}
	
	private static void func(Object one, Object two, String func) {
		switch (func) {
			case "+":
				if (one instanceof Double && two instanceof Double)
					sum.push((Double) two + (Double) one);
				else
					sum.push(two + "+" + one);
				break;
			case "-":
				if (one instanceof Double && two instanceof Double)
					sum.push((Double) two - (Double) one);
				else
					sum.push(two + "-" + one);
				break;
			case "*":
				if (one instanceof Double && two instanceof Double)
						sum.push((Double) two * (Double) one);
				else if (one instanceof monotonic && two instanceof monotonic) {
					monotonic a = (monotonic) one;
					monotonic b = (monotonic) two;
					sum.push(new monotonic(1, b.pow + a.pow));
				}
				else
					sum.push(two + "*" + one);
				break;
			case "/":
				if (one instanceof Double && two instanceof Double)
					sum.push((Double) two / (Double) one);
				else if (one instanceof monotonic && two instanceof monotonic) {
					monotonic a = (monotonic) one;
					monotonic b = (monotonic) two;
					sum.push(new monotonic(1, b.pow - a.pow));
				}
				else
					sum.push(two + "/" + one);
				break;
		}
	}

/*	int binary_search(int A[], int key, int imin, int imax)
	{
	  // test if array is empty
	  if (imax < imin)
	    // set is empty, so return value showing not found
	    return 0;
	  else
	    {
	      // calculate midpoint to cut set in half
	      int imid = midpoint(imin, imax);
	     
	      // three-way comparison
	      if (A[imid] > key)
	        // key is in lower subset
	        return binary_search(A, key, imin, imid - 1);
	      else if (A[imid] < key)
	        // key is in upper subset
	        return binary_search(A, key, imid + 1, imax);
	      else
	        // key has been found
	        return imid;
	    }
	}*/
	
	
	private static int binarySearch(int min, int max) {
		int mid = (int) ((min + max)/2);
		
		if (function(mid) > 0)
			return binarySearch(min, max - 1);
		else if (function(mid) < 0)
			return binarySearch(min + 1, max);
		else
			return mid;
	}

	private static Double function(double test) {
		double result;
		
		if (sum.peek() instanceof Double)
			result = (double) sum.pop();
		else
			result = Math.pow(test, ((monotonic) sum.pop()).pow);
		
		while(!sum.isEmpty()) {
			String temp = (String) sum.pop();
			Object temp2 = sum.pop();
			
			if (temp2 instanceof Double) {
				switch (temp) {
					case "+":
						result += (Double) temp2;
						break;
					case "-":
						result -= (Double) temp2;
						break;
					case "*":
						result *= (Double) temp2;
						break;
					case "/":
						result /= (Double) temp2;
						break;
				}
			}
			else if (temp2 instanceof monotonic) {
				switch (temp) {
				case "+":
					result += Math.pow(test, ((monotonic) temp2).pow);
					break;
				case "-":
					result -= Math.pow(test, ((monotonic) temp2).pow);
					break;
				case "*":
					result *= Math.pow(test, ((monotonic) temp2).pow);
					break;
				case "/":
					result /= Math.pow(test, ((monotonic) temp2).pow);
					break;
			}
		}
				
			
			System.out.print(sum.pop().toString());
		}
		
		return result;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String[] polish = scan.nextLine().trim().split("\\s+");
			sum = new Stack<Object>();
			L = Double.parseDouble(polish[0]);
			H = Double.parseDouble(polish[1]);
			
			Object one, two;
			
			for (int i = 2; i < polish.length; i++) {
				switch (polish[i]) {
					case "+":
					case "-":
					case "*":
					case "/":
						one = sum.pop();
						two = sum.pop();
						func(one, two, polish[i]);
						break;
					case ";":
						break;
					case "x":
						sum.push(new monotonic(1, 1));
						break;
					default: //Must be a double
						sum.push(Double.parseDouble(polish[i]));
						break;
				}
			}
			
			//binarySearch((int) L, (int) H);
			
			int z = sum.size();
			for (int i = 0; i < z; i++)
				System.out.print(sum.pop().toString());
			System.out.println();
			
			
		}

	}

}
