import java.util.Scanner;

public class binary {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNext()) {
			String bin1 = scan.next();
			String bin2 = scan.next();
			
			int n = Math.max(bin1.length(), bin2.length()) + 1;
			
			if (bin1.length() > bin2.length()) {
				StringBuilder buffer = new StringBuilder();
				for (int i = 0; i < bin1.length() - bin2.length() + 1; i++)
					buffer.append(' ');
				buffer.append(bin2);
				bin2 = buffer.toString();
				bin1 = " " + bin1;
			}
			else if (bin2.length() > bin1.length()) {
				StringBuilder buffer = new StringBuilder();
				for (int i = 0; i < bin2.length() - bin1.length() + 1; i++)
					buffer.append(' ');
				buffer.append(bin1);
				bin1 = buffer.toString();
				bin2 = " " + bin2;
			}
			else {
				bin1 = " " + bin1;
				bin2 = " " + bin2;
			}
			
			char[][] print = new char[2][n];
			
			for(int i = 0; i < n; i++) {
				print[0][i] = ' ';
				print[1][i] = ' ';
				
			}

			/*
			 * 	0 + 0 + 0 ===> 0 with NO carry DONE
				0 + 0 + 1 ===> 1 with NO carry DONE
				0 + 1 + 0 ===> 1 with NO carry DONE
				0 + 1 + 1 ===> 0 with    carry
				1 + 0 + 0 ===> 1 with NO carry
				1 + 0 + 1 ===> 0 with    carry
				1 + 1 + 0 ===> 0 with    carry
				1 + 1 + 1 ===> 1 with    carry
			 */
			
			int j = n - 1;
			
			while (j != 0) {
				if ((bin1.charAt(j) == '0' || bin1.charAt(j) == ' ') 
				 && (bin2.charAt(j) == '0' || bin2.charAt(j) == ' ') 
				 && print[0][j] == ' ') 
				{
					print[0][j - 1] = ' ';
					print[1][j] = '0';
				}
				else if ((bin1.charAt(j) == '0' || bin1.charAt(j) == ' ') 
					  && (bin2.charAt(j) == '0' || bin2.charAt(j) == ' ') 
					  && print[0][j] == '1') 
				{
					print[0][j - 1] = ' '; //Carry
					print[1][j] = '1'; //Sum
				}
				else if ((bin1.charAt(j) == '0' || bin1.charAt(j) == ' ') 
					   && bin2.charAt(j) == '1' 
					   && print[0][j] == ' ') 
				{
					print[0][j - 1] = ' '; //Carry
					print[1][j] = '1'; //Sum
				}
				else if (bin1.charAt(j) == '1' 
				     && (bin2.charAt(j) == '0' || bin2.charAt(j) == ' ') 
				     && print[0][j] == ' ') 
				{
					print[0][j - 1] = ' '; //Carry
					print[1][j] = '1'; //Sum
				}
				else if (bin1.charAt(j) == '1' 
					 && (bin2.charAt(j) == '0' || bin2.charAt(j) == ' ') 
					 && print[0][j] == '1') 
				{
					print[0][j - 1] = '1'; //Carry
					print[1][j] = '0'; //Sum
				}
				else if (bin1.charAt(j) == '1' 
					  && bin2.charAt(j) == '1' 
					  && print[0][j] == ' ') 
				{
					print[0][j - 1] = '1'; //Carry
					print[1][j] = '0'; //Sum
				}
				else if (bin1.charAt(j) == '1' 
					  && bin2.charAt(j) == '1' 
					  && print[0][j] == '1') 
				{
					print[0][j - 1] = '1'; //Carry
					print[1][j] = '1'; //Sum
				}
				else
					print[1][j] = 'x';
				j--;
			}
			
			print[1][0] = (print[0][0] == '1') ? '1' : '0';
			
			System.out.println("BINARY SUM:");
			
			for (int i = 0; i < n; i++) {
				System.out.print(print[0][i]);
			}
			System.out.println();
			
			System.out.println(bin1);
			System.out.println(bin2);
			
			for (int i = 0; i < n; i++) {
				System.out.print("-");
			}
			
			System.out.println();
			
			for (int i = 0; i < n; i++) {
				System.out.print(print[1][i]);
			}
			
			System.out.println("\n");
						
		}
		
		scan.close();
	}
}
