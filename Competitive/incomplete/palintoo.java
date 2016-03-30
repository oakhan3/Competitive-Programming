import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class palintoo {
	private static ArrayList<String> words;
	
	private static Stack<String> construct_candidates(String init, HashSet<String> used) {
		Stack<String> pos = new Stack<String>();
		
		for (String aWord : words) {
			if (used.contains(aWord))
				continue;
			
			boolean include = true;
			
			for (int i = 0; i < Math.min(init.length(), aWord.length()); i++) {
				if (init.charAt(i) != aWord.charAt(aWord.length() - 1 - i))
					include = false;
			}
			
			if (include)
				pos.push(aWord);
		}
		System.out.println("init is " + init + " found: " + pos.toString());
		return pos;
	}
	
	private static void backtrack(String init, String[] sol, boolean isLeft, HashSet<String> used, int start, int end) {
		if (isPalin(sol)) {
			for (int i = 0; i < sol.length; i++) {
				System.out.print(sol[i]);
			}
			System.out.println("\n-------------------------------------------");
		}
				
		if (used.size() != words.size()) {
			
			Stack<String> pos = construct_candidates(init, used);
			int choices = pos.size();
			
			for (int i = 0; i < choices; i++) {
				sol[isLeft ? start : end] = pos.pop();
				used.add( isLeft? sol[start] : sol[end] );
				String newInit;
				System.out.println("Sol so far: " + Arrays.deepToString(sol));
				if (init.equals(""))
					newInit = (isLeft? sol[start] : sol[end]);
				else
					newInit = (isLeft? diff(sol, start, end + 1) : diff(sol, start - 1, end));
				
				boolean newDirec = nextPos(sol, newInit, start, end);
				
				if (isLeft)
					backtrack(newInit, sol, newDirec, used, start + 1, end);
				else
					backtrack(newInit, sol, newDirec, used, start, end - 1);
					
				used.remove(sol[isLeft ? start : end]);
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

	private static boolean nextPos(String[] sol, String newInit, int start, int end) {
		if (sol[start].contains(newInit)) {
			//System.out.println(newInit + " is in " + sol[start]);
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