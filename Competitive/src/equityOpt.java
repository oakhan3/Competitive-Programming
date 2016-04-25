import java.util.Arrays;
import java.util.Scanner;

public class equityOpt {
	private static int[] data = new int[13];
	private static int income, earnings, cash, inventory, receivables, 
				  		currentAssets, fixedAssets, totalAssets, 
				  		payables, debt, totalLiabilities;

	private static int dePrefix(String str) { return Integer.parseInt(str.substring(1)); }

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
							System.out.println("");
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
		/* Y = 0, S = 1, R = 2, O = 3, P = 4, C = 5, D = 6, I = 7, T = 8, V = 9, E = 10, B = 11, G = 12 */
		
		System.out.println(data[0] + " Income Statement:");
		System.out.printf("  Operating Revenue: %19d\n", data[1]);
		System.out.printf("  Operating Cost: %22d\n",  data[3]);
		System.out.printf("  Depreciation: %24d\n",  data[6]);
		
		income = data[1] - data[3] - data[6];
		
		System.out.printf("  Operating Income: %20d\n",  income);
		System.out.printf("  Interest: %28d\n",  data[7]);
		System.out.printf("  Taxes: %31d\n",  data[8]);
		
		earnings = income - data[7] - data[8];
		
		System.out.printf("  Earnings: %28d\n",  earnings);
		System.out.printf("  Dividends: %27d\n",  data[9]);
		System.out.printf("  Transfer to Equity: %18d\n\n",   (earnings - data[9]));
		
		System.out.println(data[0] + " Balance Sheet:");
		
		//Ca +=   R     -    P    -   C     +    E     +   B      -   I     -    T    -   V
		cash += data[2] - data[4] - data[5] + data[10] + data[11] - data[7] - data[8] - data[9];
		System.out.printf("  Cash: %32d\n",  cash);
		
		inventory += data[12];
		System.out.printf("  Inventory: %27d\n",  inventory);
		
		receivables += data[1] - data[2];
		System.out.printf("  Receivables: %25d\n",  receivables);
		
		currentAssets = cash + inventory + receivables;
		System.out.printf("  Current Assets: %22d\n",  currentAssets);
		
		fixedAssets += data[5] - data[6];
		System.out.printf("  Fixed Assets: %24d\n",  fixedAssets);
		
		totalAssets = currentAssets + fixedAssets;
		System.out.printf("  Total Assets: %24d\n", totalAssets);
		
		payables += data[3] - data[4];
		System.out.printf("  Payables: %28d\n", payables);
		System.out.printf("  Current Liabilities: %17d\n",  payables);
		
		debt += data[11];
		System.out.printf("  Debt: %32d\n",  debt);
		
		totalLiabilities = payables + debt;
		System.out.printf("  Total Liabilities: %19d\n",  totalLiabilities);
		
		System.out.printf("  Equity: %30d\n",  (totalAssets - totalLiabilities));
	}	
}
