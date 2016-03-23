import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class palin {
	static ArrayList<String> words;
	final static boolean left = true;
	final static boolean right = false;
	
	
	private static Stack<String> construct_candidates(String initial, String[] sol) {
		Stack<String> pos = new Stack<String>();
		
		for (String word : words) {
			boolean legal = true;
			
			for (int i = 0; i < sol.length; i++) {
				if  (sol[i].equals(word)) {
					legal = false;
					break;
				}
			}
			
			if (word.equals(initial))
				legal = false;
			
			if (legal) {
				for (int i = 0; i < Math.min(initial.length(), word.length()); i++) {
					if (initial.charAt(i) != word.charAt(word.length() - 1 - i)) {
						legal = false;
						break;
					}
				}
			}
			
			if (legal) {
				pos.push(word);
			}
		}
		
		return pos;
	}
	
	private static void backtrack(String initial, String[] sol, int L, int R, boolean putLeft) {
		Stack<String> pos = construct_candidates(initial, sol);
		int z = pos.size();
		
		if (pos.size() == 0 || pos.size() == 1) {
			for (int i = 0; i < sol.length; i++)
				System.out.print(sol[i] + "/");
			System.out.println();
		}
		
		for (int i = 0; i < z; i++) {
			String temp = pos.pop();
			
			if (putLeft) {
				String store = sol[L+1];
				sol[L+1] = temp;
				String newInit = difference(initial, temp);
				boolean nextIsLeft = (temp.length() > initial.length()) ? left : right;
				
				backtrack(newInit, sol, L+1, R, nextIsLeft);
				sol[L+1] = store;
			}
			else {
				String store = sol[R-1];
				sol[R-1] = temp;
				String newInit = difference(initial, temp);
				boolean nextIsLeft = (initial.length() > temp.length()) ? left : right;
				backtrack(newInit, sol, L, R-1, nextIsLeft);
				sol[R-1] = store;
			}
		}
	}
	
	private static void setUp(String[] sol) {
		for (String word : words) {
			Stack<String> pos = construct_candidates(word, sol);
			int z = pos.size();
			
			for (int i = 0; i < z; i++) {
				String temp = pos.pop();
				String[] solu = new String[words.size()];
				solu[0] = word;
				for (int j = 1; j < solu.length - 1; j++)
					solu[j] = "";
				solu[words.size() - 1] = temp;
				backtrack(difference(word, temp), solu, 1, words.size() - 2, (word.length() > temp.length()) ? right : left);
			}
		}
	}
	
	private static String difference(String initial, String temp) {
		int diff = initial.length() - temp.length();
		
		if (diff == 0)
			return "";
		else {
			String testing = "";
		
			if (diff > 0)
				for (int i = initial.length() - diff; i < initial.length(); i++)
					testing += initial.charAt(i);
			else
				for (int i = 0; i < diff*-1; i++)
					testing += temp.charAt(i);
	
			System.out.printf("Tested: %s and %s, found diff of %s\n", initial, temp, testing);
			return testing;
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//Take in an n value
		while (scan.hasNextLine()) {
			words = new ArrayList<String>();
			
			while (scan.hasNextLine()) {
				String temp = scan.nextLine();
				if (temp.equals(""))
					break;
				else
					words.add(temp);
			}
			String[] sol = new String[words.size()];
			Arrays.fill(sol, "");
			setUp(sol);
			System.out.println();
		}
		scan.close();
	}
}
