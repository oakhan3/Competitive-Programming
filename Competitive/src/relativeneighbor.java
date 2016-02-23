import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class relativeneighbor {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	
		//Scan for a Case
		while (scan.hasNextLine()) {
			
			String casename = scan.nextLine();
			
			//If it is a Case, the next input should be an int
			if (scan.hasNextInt()) {
				int N = scan.nextInt();
				
				point[] points = new point[N];
				ArrayList<edge> chosen = new ArrayList<edge>();
				
				//Take in all Points
				for (int i = 0; i < N; i++)
					if (scan.hasNextDouble())
							points[i] = new point(scan.nextDouble(), scan.nextDouble());
					
				double[][] matrix = new double[N][N];
				
				PriorityQueue<edge> eq = new PriorityQueue<edge>
				(N, new Comparator<edge>() {
					public int compare(edge a, edge b) {
						return (a.gDiff() > b.gDiff()) ? 1 : (a.gDiff() < b.gDiff()) ? -1 : 0;
						}
					}
				);
				
				//Take in all possible Edges, sorting through a priority queue
				for (int j = 0; j < N; j++) {
					for (int k = j + 1; k < N ; k++) {			
						edge temp = new edge(j, k, points[j], points[k]);
						matrix[j][k] = temp.gDiff();
						eq.add(temp);
					}
				}

				int qSize = eq.size();
				int e = 0;
				boolean isRelative;
				
				//Pop the shortest edge, store if relative
				for (int m = 0; m < qSize; m++) {
					edge temp = eq.poll();
					isRelative = true;
					
					for (int y = 0; y < N; y++)
						if (y != temp.pnt1() && y != temp.pnt2())
							if (matrix[Math.min(temp.pnt1(), y)][Math.max(temp.pnt1(), y)] < temp.gDiff() 
									&& matrix[Math.min(temp.pnt2(), y)][Math.max(temp.pnt2(), y)] < temp.gDiff()) {
								isRelative = false;
								break;
								}
					
					if (isRelative) {
						chosen.add(temp);
						e++;
						isRelative = false;
					}
					
					if (e == (N - 1))
						break;
				}
				
				//Sort the output, TODO: implement better sorting method
				for (int i = 0; i < chosen.size(); i++) {
					for (int j = 0; j < chosen.size(); j++) {
						if (i != j) {
							if (chosen.get(i).pnt1() < chosen.get(j).pnt1())
								chosen.add(j, chosen.remove(i));
							else if (chosen.get(i).pnt1() == chosen.get(j).pnt1()) {
								if (chosen.get(i).pnt2() < chosen.get(j).pnt2())
									chosen.add(j, chosen.remove(i));
							}
						}
					}
				}
				
				//Print final output
				System.out.println(casename);
				
				for (edge a : chosen)
					System.out.println(a.tS());
			}
		}
		scan.close();
	}
	
}

class edge {
	private int pt1;
	private int pt2;
	private double diff;
	
	public edge(int pt1, int pt2, point p1, point p2) {
		this.pt1 = pt1;
		this.pt2 = pt2;
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
	
	public String tS() {
		return (pt1 + 1) + " " + (pt2 + 1);
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
