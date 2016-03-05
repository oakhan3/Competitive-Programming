import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class palin {
	static ArrayList<String> list;
	static String solution, temp;
	static String[] sol, tem;
	static Stack<String> posinit;
	
	private static Stack<String> construct_candidates(int k) {
		boolean legal;
		Stack<String> pos = new Stack<String>();
			
		for (String a : list) {
			legal = true;	
			for (int j = 1; j < k; j++)
				if (sol[j].equals(a)) {
					legal = false;
					break;
				}
			if (legal)
				pos.push(a);
		}
		
		return pos;
	}
	
	private static void backtrack(int k) {
		if (k > 0) {
			if (sol[1].charAt(0) == sol[k].charAt(sol[k].length()-1))
				if (validSol(k))
					if (temp.length() > solution.length()) {
						tem = Arrays.copyOf(sol, k+1);
						solution = temp;
					}
		}
		if (k < list.size()) {
			k++;
			Stack<String> pos = (k == 1) ? posinit : construct_candidates(k);
			int z = pos.size();
			for (int i = 0; i < z; i++) {
				sol[k] = pos.pop();
				backtrack(k);
			}
		}
	}
	
	private static boolean initbacktrack() {
		boolean initial = true;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < list.size(); i++)
			sb.append(sol[i]);
		
		temp = sb.toString();

		for (int i = 1; i < temp.length(); i++) {
			if (temp.charAt(i) != temp.charAt(temp.length() - 1 - i)) {
				initial = false;
				break;
			}
		}

		if (initial) {
			tem = Arrays.copyOf(sol, list.size());
			System.out.print(tem[0] + " ");
			return true;
		}
		
		posinit = new Stack<String>();
		boolean legal = true;
		
		for (String a : list) {
			legal = false;
			for (String b : list) {
				if (!a.equals(b))
					if (a.charAt(0) == b.charAt(b.length() - 1)) {
						legal = true;
						break;
					}
			}
			if (legal)
				posinit.push(a);
		}
		return false;
	}

	private static boolean validSol(int k) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= k; i++)
			sb.append(sol[i]);
		
		temp = sb.toString();
		
		for (int i = 1; i < temp.length(); i++) {
			if (temp.charAt(i) != temp.charAt(temp.length() - 1 - i))
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			list = new ArrayList<String>();
			
			while (scan.hasNextLine()) {
				String t = scan.nextLine();
				if (t.equals("")) {
					solution = "";
					sol = new String[list.size()+1];
					for (int i = 0; i < list.size(); i++)
						sol[i] = list.get(i);
					if(initbacktrack())
						break;
					else {
						backtrack(0);
						sol[list.size()] = "";
					}
					break;
				}
				list.add(t);
			}
			for (int i = 1; i < tem.length; i++) {
				System.out.print(tem[i]);
				if (i == tem.length - 1)
					System.out.println();
				else
					System.out.print(" ");
			}
		}
		scan.close();
	}
}
