import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class pagerank {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String casename = scan.nextLine();
			assert(scan.hasNextLine());
			
			String[] nalphak = scan.nextLine().split("\\s+");
			assert(nalphak.length == 3);
			
			//Set Case's values
			int n = Integer.parseInt(nalphak[0]);
			double alpha = Double.parseDouble(nalphak[1]);
			double ahpla = 1.0 - alpha;
			int k = Integer.parseInt(nalphak[2]);
			
			//Use n+1 since pages are listed beginning from 1 in input
			ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>(n + 1);
			int[] nodeEdgeSum = new int[n+1];

			for (int i = 1; i <= n; i++) {
				//Read in page links
				String[] temp = scan.nextLine().split("\\s+");
				nodeEdgeSum[i] = temp.length - 1;
				ArrayList<Integer> inner = new ArrayList<Integer>();
				
				for (int j = 0; j < nodeEdgeSum[i]; j++)
					inner.add(Integer.parseInt(temp[j]));
				
				outer.add(inner);
			}
			
			double[] pagerankOLD = new double[n+1];
			double[] pagerankNEW = new double[n+1];
			
			double equalProb = 1.0 / n;
			
			//First Iteration
			Arrays.fill(pagerankOLD, equalProb);
				
			//For each iteration until k
			for (int r = 2; r <= k; r++) {
				//For each node
				for (int j = 1; j <= n; j++) {
					
					pagerankNEW[j] = 0.0;
					
					//Iterate through all the nodes, aggregating probability
					for (int i = 1; i <= n; i++) {
						
						if (outer.get(i - 1).contains(j)) {	//CASE: The page has a link to our page
							double count = 0;
							
							for (int q : outer.get(i - 1))
								if (q == j)
									count++;
							
							pagerankNEW[j] += (( ahpla * equalProb ) 
												+ (alpha * count / nodeEdgeSum[i] )) * pagerankOLD[i];
							
							//System.out.println("Node: " + j + " is linked by node: " + i + " " + count + " times");
						}
						else if (nodeEdgeSum[i] == 0)	//CASE: Page is Source of 0 Links
							pagerankNEW[j] += equalProb * pagerankOLD[i];
						
						else							//CASE: Page isn't a link to our page but page has links to other pages
							pagerankNEW[j] += ahpla * equalProb * pagerankOLD[i];
					}
				}
				
				for (int m = 1; m <= n; m++)
					pagerankOLD[m] = pagerankNEW[m];
			}
			
			//Print the results
			System.out.println(casename);
			
			for (int i = 1; i <= n; i++)
				System.out.println(i + " " + String.format("%.6f", pagerankOLD[i]));
			}
		
		scan.close();
	}
}
