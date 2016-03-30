import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class palintoo {
	private static ArrayList<String> words;
	
	private static Stack<String> construct_candidates(String init, HashSet<String> used, boolean isLeft) {
		Stack<String> pos = new Stack<String>();
		
		for (String aWord : words) {
			if (used.contains(aWord)) {
				//System.out.println("Skipping : " + aWord);
				continue;
			}
			
			boolean include = true;
			
			if (!isLeft) {
				for (int i = 0; i < Math.min(init.length(), aWord.length()); i++) {
					if (init.charAt(i) != aWord.charAt(aWord.length() - 1 - i))
						include = false;
				}
			}
			
			else {
				for (int i = 0; i < Math.min(init.length(), aWord.length()); i++) {
					if (init.charAt(init.length() - 1 - i) != aWord.charAt(i))
						include = false;
				}
			}
			
			if (include)
				pos.push(aWord);
		}
		
		//System.out.println(pos.toString());
		return pos;
	}
	
	private static void backtrack(String init, String[] sol, boolean isLeft, HashSet<String> used, int start, int end) {
		if (isPalin(sol)) {
			System.out.print("FOUND:\t\t");
			for (int i = 0; i < sol.length; i++) {
				System.out.print(sol[i]);
				//gnu redo code rung
			}
			System.out.println("\n");
		}
				
		if (used.size() != words.size()) {
			
			Stack<String> pos = construct_candidates(init, used, isLeft);
			int choices = pos.size();
			
			for (int i = 0; i < choices; i++) {
				
				String temp = pos.pop();
				sol[(isLeft ? start : end)] = temp;
				used.add(temp);
				
				String newInit;
				boolean newDirec;
				
				if (init.equals("")) {
					newInit = temp; //If the init is "", then the word to compare will be the temp
					newDirec = !isLeft;
					System.out.println("NewInit: " + newInit + "\t\tSo Far: " + Arrays.deepToString(sol));
				}
				else {
					/*
					 * At this point sol contains at least two words
					 * 
					 */
					
					
					
					//Otherwise, it will be the difference between the new start and end
					newInit = (isLeft? diff(sol, start, end + 1) : diff(sol, start - 1, end));
					System.out.println("NewInit: " + newInit + "\t\tSo Far: " + Arrays.deepToString(sol));
					newDirec = nextPos(sol, newInit, start, end);
				}
				
				if (isLeft) {
					backtrack(newInit, sol, newDirec, used, start + 1, end);
				}
				else {
					backtrack(newInit, sol, newDirec, used, start, end - 1);
				}
					
				used.remove(temp);
				sol[isLeft ? start : end] = "";
			}
		}
	}
	
	private static boolean isPalin(String[] sol) {
		StringBuilder sb = new StringBuilder();
		
		for (String aWord : sol) {
			sb.append(aWord);
		}

		if (sb.toString().equals(sb.reverse().toString()))
			return true;
		else
			return false;
	}

	private static String diff(String[] sol, int start, int end) {
		int diff = sol[start].length() - sol[end].length();
		
		if (diff == 0)
			return "";
		else {
			String testing = "";
		
			if (diff > 0)
				for (int i = sol[start].length() - diff; i < sol[start].length(); i++)
					testing += sol[start].charAt(i);
			else
				for (int i = 0; i < diff * -1; i++)
					testing += sol[end].charAt(i);
	
			System.out.printf("Tested: %s and %s, found diff of %s\n", sol[start], sol[end], testing);
			return testing;
		}
	}

	private static boolean nextPos(String[] sol, String init, int start, int end) {
		if (sol[start].contains(init)) {
			return false;
		}
		else
			return true;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
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
			
			backtrack("", sol, true, new HashSet<String>(), 0, sol.length - 1);
			
			System.out.println();
		}
		scan.close();
	}

	
}