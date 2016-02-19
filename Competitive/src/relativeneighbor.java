import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class relativeneighbor {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		//Scan for a Case
		while (scan.hasNextLine()) {
			
			String casename = scan.nextLine();
			
			//If it is a Case, the next input should be an Int
			if (scan.hasNextInt()) {
				int N = scan.nextInt();
				
				point[] points = new point[N];
				boolean[] marked = new boolean[N];
				String[] chosen = new String[N - 1];
				
				//Take in all Points
				for (int i = 0; i < N; i++) {
					if (scan.hasNextDouble())
						if (scan.hasNextDouble())
							points[i] = new point(scan.nextDouble(), scan.nextDouble());
				}
				
				PriorityQueue<edge> eq = new PriorityQueue<edge>(N, new Comparator<edge>() {
					public int compare(edge a, edge b) {
						return (a.gDiff() > b.gDiff()) ? 1 : (a.gDiff() < b.gDiff()) ? -1 : 0;
					}});
				
				//Take in all possible Edges, sorting through a priority queue
				for (int j = 0; j < N; j++) {
					for (int k = j + 1; k < N ; k++) {
						eq.add(new edge(j, k, points[j], points[k]));
					}
				}
				
				int qSize = eq.size();
				int p = 0;
				
				//Pop the shortest edge, store for recollection
				//							if it includes an unreviewed point
				for (int m = 0; m < qSize; m++) {
					edge temp = eq.poll();
					
					if (!marked[temp.pnt1()] || !marked[temp.pnt2()]) {
						marked[temp.pnt1()] = true;
						marked[temp.pnt2()] = true;
						chosen[p++] = (temp.pnt1() + 1) + " " + (temp.pnt2() + 1);
					}
				}
				
				//Sort and Output
				Arrays.sort(chosen);
				
				System.out.println(casename);
				
				for (int r = 0; r < N - 1; r++)
					System.out.println(chosen[r]);
			}
		}
	}
}

class edge {
	private point p1;
	private point p2;
	private int pt1;
	private int pt2;
	private double diff;
	
	public edge(int pt1, int pt2, point p1, point p2) {
		this.pt1 = pt1;
		this.pt2 = pt2;
		this.p1 = p1;
		this.p2 = p2;
		this.diff = Math.sqrt(
				      Math.pow(p1.gX() - p2.gX(), 2) 
				    + Math.pow(p1.gY() - p2.gY(), 2));
	}
	
	public int pnt1() {
		return pt1;
	}
	
	public int pnt2() {
		return pt2;
	}

	public double gDiff() {
		return diff;
	}
}

class point {
	private double x;
	private double y;
	
	public point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double gX() {
		return x;
	}

	public double gY() {
		return y;
	}
}
