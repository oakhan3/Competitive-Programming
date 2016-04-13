import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;

public class alphawhat {
	private static HashMap<String, ArrayList<String>> relations;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		relations = new HashMap<String, ArrayList<String>>();
		
		while (scan.hasNextLine()) {
			String temp = scan.nextLine();
			if (temp.equals(""))
				break;
			else if (temp.equals("*")) {
				solve();
				relations = new HashMap<String, ArrayList<String>>();
			}
			else {
				String less[] = temp.split("<");
				
				if (relations.containsKey(less[1])) {
					ArrayList<String> comp = relations.get(less[1]);
					comp.add(less[0]);
					relations.put(less[1], comp);
				}
				else {
					ArrayList<String> comp = new ArrayList<String>();
					comp.add(less[0]);
					relations.put(less[1], comp);
				}
				
				if (!relations.containsKey(less[0]))
					relations.put(less[0], new ArrayList<String>());
			}
		}
		
		scan.close();
	}

	private static void solve() {
		ArrayList<String> added = new ArrayList<String>();
		LinkedList<String> all = new LinkedList<String>();
		
		//Iterate over all relations, adding the dependent ones to your list
		for (Entry<String, ArrayList<String>> i : relations.entrySet()) {
			if (i.getValue().isEmpty())
				added.add(i.getKey());
			else
				all.add(i.getKey());
		}
		
		while (!all.isEmpty()) {
			ArrayList<String> remove = new ArrayList<String>();
			
			for (String i : all) {
				ArrayList<String> must = relations.get(i);
				boolean add = true;
				
				for (String j : must) {
					if (!added.contains(j)) {
						add = false;
						break;
					}
				}
				
				if (add) {
					remove.add(i);
					added.add(i);
				}
			}
			
			for (String k : remove)
				all.remove(k);
		}
		
		System.out.println(added.toString().replaceAll("\\[|\\]|,", ""));		
	}
}
