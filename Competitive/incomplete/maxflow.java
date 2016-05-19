import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class maxflow {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) {
			String start = scan.next();
			String end = scan.next();

			HashMap<String, Integer> keys = new HashMap<String, Integer>();
			ArrayList<EdgeFlow> edges = new ArrayList<EdgeFlow>();

			int n = 0;

			while (true) {
				String a = scan.next();
				if ("*".equals(a)) {
					scan.nextLine();
					break;
				}
				else {
					if (!keys.containsKey(a)){
						keys.put(a, n++);
						System.out.println(a + " is " + (n-1));
					}
					
					String b = scan.next();

					if (!keys.containsKey(b)){
						keys.put(b, n++);
						System.out.println(b + " is " + (n-1));
					}	
					edges.add(new EdgeFlow(keys.get(a), keys.get(b), scan.nextInt()));
				}
			}

			boolean[][] hasEdge = new boolean[n][n];
			int[][] edgeWeight = new int[n][n];

			for (EdgeFlow temp : edges) {
				hasEdge[temp.start][temp.end] = true;
				edgeWeight[temp.start][temp.end] = temp.length;
			}
			
			System.out.println("The start is: " +  keys.get(start));
			System.out.println("The end is: " +  keys.get(end));
			
			if (keys.get(end) == null)
				System.out.println("NIL");

			System.out.println(Arrays.deepToString(hasEdge));
		}
	}
}

class EdgeFlow {
	int start;
	int end;
	int length;

	EdgeFlow(int s, int e, int len) {
		start = s;
		end = e;
		length = len;
	}
}
