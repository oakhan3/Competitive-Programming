import java.util.Scanner;

/* Omar Khan 
 *  * Spoonerisms Submission
 *   * */

public class spoonerisms {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		while (scan.hasNextLine()) {
			String temp = scan.nextLine();
			String[] words = temp.split(" ");
			int t = words.length - 1;
			
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			
			for (int i = 0; i < words[0].length(); i++) {
				if (words[0].charAt(i) != 'a' && 
						words[0].charAt(i) != 'e' && 
						words[0].charAt(i) != 'i' && 
						words[0].charAt(i) != 'o' && 
						words[0].charAt(i) != 'u') {
					if (words[0].charAt(i) == 'y' && i != 0) {
						for (int j = i; j < words[0].length(); j++) {
							sb2.append(words[0].charAt(j));
						}
						break;
					}
					sb.append(words[0].charAt(i));
				}
				else {
					for (int j = i; j < words[0].length(); j++) {
						sb2.append(words[0].charAt(j));
					}
					break;
				}
			}
			
			StringBuilder sb3 = new StringBuilder();
			StringBuilder sb4 = new StringBuilder();
			
			for (int i = 0; i < words[t].length(); i++) {
				if (words[t].charAt(i) != 'a' && 
						words[t].charAt(i) != 'e' && 
						words[t].charAt(i) != 'i' && 
						words[t].charAt(i) != 'o' && 
						words[t].charAt(i) != 'u') {
					if (words[t].charAt(i) == 'y' && i != 0) {
						for (int j = i; j < words[t].length(); j++) {
							sb4.append(words[t].charAt(j));
						}
						break;
					}
					sb3.append(words[t].charAt(i));
					
				}
				else {
					for (int j = i; j < words[t].length(); j++) {
						sb4.append(words[t].charAt(j));
					}
					break;
				}
			}
			System.out.print(sb3.toString() + sb2.toString() + " ");
			
			if (words.length > 2) {
				for (int i = 1; i < t; i++) {
					System.out.print(words[i]+ " ");
				}
			}
			
			System.out.print(sb.toString() + sb4.toString() + "\n");
		}
		scan.close();

	}

}

