import java.util.Scanner;
/*NOT FINISHED*/
public class pagerank {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			//Get Case Name
			String name = scan.nextLine();

			//Scan and Parse N ALPHA K
			assert (scan.hasNextLine());
			String nalphak = scan.nextLine();
			String[] parts = nalphak.split("\\s");
			
			assert (parts.length == 3);
			int N = Integer.parseInt(parts[0]);
			double alpha = Double.parseDouble(parts[1]);
			int k = Integer.parseInt(parts[2]);	
			//System.out.println(N + " " + alpha + " " + k);
			
			//Scan and Parse the Pages
			int[][] pages = new int[N][];
				
			for (int i = 0; i < N; i++) {
				assert (scan.hasNextLine());
				String[] temp = scan.nextLine().split("\\s");
				pages[i] = new int[temp.length];
				for (int j = 0; j < temp.length; j++)
					pages[i][j] = Integer.parseInt(temp[j]);
			}
			
			/*
			To move from a current page to the next page, the surfer
			does the following.  If the current page is the source
			of zero links, the surfer chooses the next page randomly
			from among the N pages of the web.  Otherwise, with
			probability 1-ALPHA, the surfer does the same thing (as
			if the page sourced zero links), and with probability
			ALPHA, the surfer chooses a link sourced at the current
			page at random and follows that link.

			When choosing from among a set of pages or links at ran-
			dom the surfer gives equal probability to each page or
			link that might be chosen.  So to choose at random from
			among the N pages of the web, each page has probability
			1/N of being chosen.  And to choose at random from
			among Q links sourced at the current page, each link has
			probability 1/Q of being chosen.

			You have been asked to compute the probability for each
			page that that page will be the K'th page visited by the
			surfer, for a given web graph and value of K.
			*/
			
				
				
		}
	}
}
