import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class isa {
	private static HashMap<Character, isa> nodes = new HashMap<Character, isa>();
	private static boolean found;
	private static ArrayList<String> answer = new ArrayList<String>();
	
	private char name;
	private ArrayList<isa> edgesTo;

	public isa(char a) {
		this.name = a;
		this.edgesTo = new ArrayList<isa>();
	}
	
	private static void addToStruct(char a, char b) {
		if (!nodes.containsKey(a))
			nodes.put(a, new isa(a));
		
		if (!nodes.containsKey(b))
			nodes.put(b, new isa(b));
		
		nodes.get(a).edgesTo.add(nodes.get(b));
		return;
	}
	
	private static void question(char a, char b) {
		if (nodes.isEmpty())
			answer.add("unknown");
		else {
			breadthFirst(a, b);
			if (found) {
				answer.add("true");
				found = false;
			}
			else
				answer.add("unknown");
		}
		return;
	}
	
	private static void breadthFirst(char a, char b) {
		if (nodes.get(a).edgesTo.isEmpty())
			return;
		else if (nodes.get(a).edgesTo.contains(nodes.get(b))) {
			found = true;
			return;
		}
		else {
			for (isa temp : nodes.get(a).edgesTo) {
					breadthFirst(temp.name, b);
			}
		}
		return;
	}
	
	private static void analyze(String a) {
		if (a.length() == 8) {
			addToStruct(a.charAt(0), a.charAt(7));
		}
		else if (a.length() == 9) {
			question(a.charAt(0), a.charAt(7));
		}
		else {
			answer.add(".");
			nodes.clear();
		}
		return;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String current;

		while (scan.hasNextLine()) {
            current = scan.nextLine();
            analyze(current);
        }
		scan.close();
		
		for (String temp : answer)
			System.out.println(temp);
	}
}
