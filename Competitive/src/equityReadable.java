import java.util.Arrays;
import java.util.Scanner;

public class equityReadable {
	//Use one array since data only depends on year prior
	private static int[] data = new int[13];
	//Keep these static for access in static methods
	private static int income, earnings, cash, inventory, receivables, 
				  		currentAssets, fixedAssets, totalAssets, 
				  		payables, debt, totalLiabilities;
	
	//Set to default public to access members, 
	enum in {
		Y(0), S(1), R(2), O(3), P(4), C(5), D(6), I(7), T(8), V(9), E(10), B(11), G(12);
		//Init
		int dex;
		in(int index) { dex = index; }
	}
	
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
							pl("");
							pending = false;
						}
						
						emptyStat();
						pl(temp.substring(1));
						pl("");
						break;	
					case 'Y':
						if (pending) {
							printStat();
							pl("");
						}
						else
							pending = true;
						
						Arrays.fill(data, 0);
						data[0] = dePrefix(temp);
						break;	//current-year>
					default:
						data[(type == 'S') ? 1 :
						     (type == 'R') ? 2 :
						     (type == 'O') ? 3 :
						     (type == 'P') ? 4 :
						     (type == 'C') ? 5 :
						     (type == 'D') ? 6 :
						     (type == 'I') ? 7 :
						     (type == 'T') ? 8 :
						     (type == 'V') ? 9 :
						     (type == 'E') ? 10 :
						     (type == 'B') ? 11 :
						     (type == 'G') ? 12 : 0] = dePrefix(temp);
				}
			}
		}
		
		if (pending)
			printStat();
		
		scan.close();
	}

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

	private static void printStat() {
		pl(data[in.Y.dex] + " Income Statement:");
		pf("  Operating Revenue: %19d\n", data[in.S.dex]);
		pf("  Operating Cost: %22d\n",  data[in.O.dex]);
		pf("  Depreciation: %24d\n",  data[in.D.dex]);
		
		income = data[in.S.dex] - data[in.O.dex] - data[in.D.dex];
		
		pf("  Operating Income: %20d\n",  income);
		pf("  Interest: %28d\n",  data[in.I.dex]);
		pf("  Taxes: %31d\n",  data[in.T.dex]);
		
		earnings = income - data[in.I.dex] - data[in.T.dex];
		
		pf("  Earnings: %28d\n",  earnings);
		pf("  Dividends: %27d\n",  data[in.V.dex]);
		pf("  Transfer to Equity: %18d\n\n",   (earnings - data[in.V.dex]));
		
		pl(data[in.Y.dex] + " Balance Sheet:");
		
		//Ca +=         R      -         P      -         C      +         E      +         B      -         I      -         T      -         V
		cash += data[in.R.dex] - data[in.P.dex] - data[in.C.dex] + data[in.E.dex] + data[in.B.dex] - data[in.I.dex] - data[in.T.dex] - data[in.V.dex];
		pf("  Cash: %32d\n",  cash);
		
		inventory += data[in.G.dex];
		pf("  Inventory: %27d\n",  inventory);
		
		receivables += data[in.S.dex] - data[in.R.dex];
		pf("  Receivables: %25d\n",  receivables);
		
		currentAssets = cash + inventory + receivables;
		pf("  Current Assets: %22d\n",  currentAssets);

		fixedAssets += data[in.C.dex] - data[in.D.dex];
		pf("  Fixed Assets: %24d\n",  fixedAssets);
		
		totalAssets = currentAssets + fixedAssets;
		pf("  Total Assets: %24d\n", totalAssets);
		
		payables += data[in.O.dex] - data[in.P.dex];
		pf("  Payables: %28d\n", payables);
		pf("  Current Liabilities: %17d\n",  payables);
		
		debt += data[in.B.dex];
		pf("  Debt: %32d\n",  debt);
		
		totalLiabilities = payables + debt;
		pf("  Total Liabilities: %19d\n",  totalLiabilities);
		
		pf("  Equity: %30d\n",  (totalAssets - totalLiabilities));
	}
	
	private static int dePrefix(String str)      { return Integer.parseInt(str.substring(1)); }
	private static void pf(String line, int arg) { System.out.printf(line, arg); }
	private static void pl(String line)          { System.out.println(line); }	
}
