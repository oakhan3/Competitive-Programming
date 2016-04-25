import java.util.Arrays;
import java.util.Scanner;

public class equityDirty {
	//Use one array since data only depends on year prior
	private static int[] data = new int[13];
	//Keep these static for access in static methods
	private static int income, earnings, cash, inventory, receivables, 
				  		currentAssets, fixedAssets, totalAssets, 
				  		payables, debt, totalLiabilities;
	
	//Remove the first Character and Return the Integer Representation
	private static int dePrefix(String str) { return Integer.parseInt(str.substring(1)); }
	
	//Take in all the input
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean pending = false;

		while (scan.hasNextLine()) {
			String temp = scan.nextLine();
			
			if (!temp.equals("")) {
				char type = temp.charAt(0);
				
				switch (type) {
					case 'N':
						if (pending) {
							printStat();
							System.out.println();
							pending = false;
						}
						
						emptyStat();
						System.out.println(temp.substring(1) + "\n");
						break;	
					case 'Y':
						if (pending) {
							printStat();
							System.out.println();
						}
						else
							pending = true;
						
						Arrays.fill(data, 0);
						data[0] = dePrefix(temp);
						break;	//current-year>
					case 'S': data[1] = dePrefix(temp);  break;	//sales>
					case 'R': data[2] = dePrefix(temp);	 break;	//received>
					case 'O': data[3] = dePrefix(temp);	 break;	//operating-cost>
					case 'P': data[4] = dePrefix(temp);	 break;	//paid>
					case 'C': data[5] = dePrefix(temp);	 break;	//capital-expenditures>
					case 'D': data[6] = dePrefix(temp);	 break;	//depreciation>
					case 'I': data[7] = dePrefix(temp);	 break;	//interest>
					case 'T': data[8] = dePrefix(temp);	 break;	//taxes>
					case 'V': data[9] = dePrefix(temp);  break;	//dividends>
					case 'E': data[10] = dePrefix(temp); break;	//new-equity>
					case 'B': data[11] = dePrefix(temp); break;	//new-debt>
					case 'G': data[12] = dePrefix(temp); break;	//inventory-change
					//No Default, expect perfect output per prog description
				}
			}
		}
		
		if (pending)
			printStat();
		
		scan.close();
	}

	//Empty out the stats from the previous Company
	private static void emptyStat() {
		Arrays.fill(data, 0);
		income = 0;
		earnings = 0;
		cash = 0;
		inventory = 0;
		receivables = 0;
		currentAssets = 0;
		fixedAssets = 0;
		totalAssets = 0;
		payables = 0;	//same as currentLiabilities
		debt = 0;
		totalLiabilities = 0;	
	}

	//Set to default public to access members, 
	enum in {
		Y(0), S(1), R(2), O(3), P(4), C(5), D(6), I(7), T(8), V(9), E(10), B(11), G(12);
		//Init
		int dex;
		in(int index) { dex = index; }
	}
	
	//Print the Stats once either a Year or Company Name has been seen
	private static void printStat() {
		System.out.println(data[in.Y.dex] + " Income Statement:");
		format("Operating Revenue:", data[in.S.dex]);
		format("Operating Cost:",  data[in.O.dex]);
		format("Depreciation:",  data[in.D.dex]);
		
		income = data[in.S.dex] - data[in.O.dex] - data[in.D.dex];
		
		format("Operating Income:",  income);
		format("Interest:",  data[in.I.dex]);
		format("Taxes:",  data[in.T.dex]);
		
		earnings = income - data[in.I.dex] - data[in.T.dex];
		
		format("Earnings:",  earnings);
		format("Dividends:",  data[in.V.dex]);
		format("Transfer to Equity:",   (earnings - data[in.V.dex]));
		
		System.out.println("\n" + data[in.Y.dex] + " Balance Sheet:");
		
		//Ca +=         R      -         P      -         C      +         E      +         B      -         I      -         T      -         V
		cash += data[in.R.dex] - data[in.P.dex] - data[in.C.dex] + data[in.E.dex] + data[in.B.dex] - data[in.I.dex] - data[in.T.dex] - data[in.V.dex];
		format("Cash:",  cash);
		
		inventory += data[in.G.dex];
		format("Inventory:",  inventory);
		
		receivables += data[in.S.dex] - data[in.R.dex];
		format("Receivables:",  receivables);
		
		currentAssets = cash + inventory + receivables;
		format("Current Assets:",  currentAssets);

		fixedAssets += data[in.C.dex] - data[in.D.dex];
		format("Fixed Assets:",  fixedAssets);
		
		totalAssets = currentAssets + fixedAssets;
		format("Total Assets:", totalAssets);
		
		payables += data[in.O.dex] - data[in.P.dex];
		format("Payables:", payables);
		format("Current Liabilities:",  payables);
		
		debt += data[in.B.dex];
		format("Debt:",  debt);
		
		totalLiabilities = payables + debt;
		format("Total Liabilities:",  totalLiabilities);
		
		format("Equity:",  (totalAssets - totalLiabilities));
	}
	
	//Format the output
	private static void format(String label, int data) {
		//Use StringBuilder for Optimal Time
		//~~ Not that it matters here...
		
		StringBuilder spaces = new StringBuilder("");
		
		//40 Line limit - 2 indentation - 18 max for number
		int spaceGen = 40 - 2 - 18 - label.length(); 
		
		for (int i = 0; i < spaceGen; i++)
			spaces.append(' ');
		
		System.out.printf("  %s%s%18d\n", label, spaces.toString(), data);
	}
}
