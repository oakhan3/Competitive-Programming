import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class palin3 {
	private static String testing;
	private static int temp, best = 0;
	private static ArrayList<String> input;
	private static String[] sol, stored;
	private static int x,y;
 
	private static Stack<String> construct_candidates(String initial) {
		Stack<String> pos = new Stack<String>();
		
		for (String in : input) {
			boolean legal = true;
			
			for (int i = 0; i < input.size(); i++)
				if (!sol[i].equals(""))
					if (in.equals(sol[i])) {
						legal = false;
						break;
					}
			
			if (legal) {
				for (int i = 0; i < Math.min(initial.length(), in.length()); i++) {
					if (initial.charAt(i) != in.charAt(in.length() - 1 - i)) {
						legal = false;
						break;
					}
				}
			}
			
			if (legal) {
				pos.add(in);
				System.out.println("Added: " + in);
			}
				
		}
		System.out.println("done");
		
		return pos;
	}

	private static void backtrack(int k, String initial, boolean isLeft) {
		if (k > 0)
			if (validSol()) {
				if (temp > best) {
					stored = sol;
					best = temp;
				}
			}
		if (k < input.size()) {
			System.out.println("Testing: " + initial);
			k++;
			Stack<String> pos = construct_candidates(initial);
			int z = pos.size();
			
			for (int i = 0; i < z; i++) {
				String temp = pos.pop();
				
				isLeft = compareDiff(initial, temp, isLeft);
				
				if (isLeft) 
					sol[x++] = temp;
				else
					sol[y--] = temp;
				
				backtrack(k, testing, isLeft);
			}
		}
	}
	
	private static boolean validSol() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i < input.size(); i++)
			sb.append(sol[i]);
		
		String solution = sb.toString();
		
		for (int i = 1; i < solution.length(); i++) {
			if (solution.charAt(i) != solution.charAt(solution.length() - 1 - i))
				return false;
		}
		
		temp = solution.length();
		
		return true;
	}
	
	private static boolean compareDiff(String initial, String temp, boolean initialLeft) {
		int diff = initial.length() - temp.length();
		
		if (initial.equals("")) {
			testing = temp;
			return false;
		}
		else
			testing = "";
		
		if (diff == 0)
			return true;
		
		else if (diff > 0)
			for (int i = initial.length() - diff; i < initial.length(); i++)
				testing += initial.charAt(i);
		
		else
			for (int i = 0; i < diff*-1; i++)
				testing += temp.charAt(i);

		if (initialLeft) {
			if (diff > 0) {
				return false; //look for the right next time
			}
			else
				return true; //diff was less than 0 meaning temp was longer so next time we look for left
		}
		else {	//we are looking at the right 
			if (diff > 0) {
				return true; 
			}
			else
				return false;
		}
	}
	

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			input = new ArrayList<String>();
			while (scan.hasNextLine()) {
				String in = scan.nextLine();
				if (in.equals("")) {
					sol = new String[input.size()];
					x = 0;
					y = input.size() - 1;
					for (int i = 0; i < input.size(); i++)
						sol[i] = "";
					backtrack(0, "", true);
					break;
				}
				input.add(in);
			}
			
			for (int i = 0; i < input.size(); i++) {
				System.out.print(stored[i]);
				if (i == input.size() - 1)
					System.out.println();
				else
					System.out.print(" ");
			}
			
		}
		//compareDiff("abcdefg", "dcba", true);
		//System.out.println(testing);
		
		scan.close();
	}
}
