import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Comparator;

public class huffman {
	private String key;
	private int val;
	private static int n = 0;
	private static HashMap<Character, Integer> nodes;
	
	private huffman(String ch, int val) {
		this.key = ch;
		this.val = val;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String input = scan.nextLine();
			n++;
			nodes = new HashMap<Character, Integer>();
			
			for (int i = 0; i < input.length(); i++) {
				if (nodes.containsKey(input.charAt(i)))
					nodes.put(input.charAt(i), nodes.get(input.charAt(i)) + 1);
				else
					nodes.put(input.charAt(i), 1);
			}
			
			PriorityQueue<huffman> q = new PriorityQueue<huffman>
			(nodes.size(), new Comparator<huffman>() {
				public int compare(huffman a, huffman b) {
					return (a.val > b.val) ? 1 : (a.val < b.val) ? -1 : a.key.compareTo(b.key);
					}
				}
			);
			
			for (Entry<Character, Integer> temp : nodes.entrySet())
				q.add(new huffman(temp.getKey().toString(), temp.getValue()));
			
			while (q.size() != 1) {
				huffman one = q.poll();
				huffman two = q.poll();
				int i, j;
				
				for (i = 0; i < one.key.length(); i++)
					if (one.key.charAt(i) >= 'a' && one.key.charAt(i) <= 'z')
						break;
				
				for (j = 0; j < two.key.length(); j++)
					if (two.key.charAt(j) >= 'a' && two.key.charAt(j) <= 'z')
						break;
				
				if (one.key.charAt(i) < two.key.charAt(j))
					q.add(new huffman("(" + one.key + ")" + "(" + two.key + ")", one.val + two.val));
				else
					q.add(new huffman("(" + two.key + ")" + "(" + one.key + ")", one.val + two.val));	
			}
			
			System.out.println("String " + n + ": " + q.poll().key);
		}
		scan.close();
	}
}
