import java.util.Hashtable;
import java.util.Scanner;

public class passwords {
	
	private static boolean isPunctuation(char c) {
		return 	   (c >= '!' && c <= '/')
				|| (c >= ':' && c <= '@')
				|| (c >= '[' && c <= '\'')
				|| (c >= '{' && c <= '~');
	}

	public static void main(String[] args) {
		//Create the special word HashTable
		Hashtable<String, Character> isSpecial = new Hashtable<String, Character>();
		
		isSpecial.put("and", '&');
		isSpecial.put("or", '|');
		isSpecial.put("not", '!');
		isSpecial.put("equal", '=');
		isSpecial.put("plus", '+');
		isSpecial.put("minus", '-');
		isSpecial.put("times", '*');
		isSpecial.put("slash", '/');
		isSpecial.put("dollar", '$');
		isSpecial.put("percent", '%');
		isSpecial.put("at", '@');
		isSpecial.put("zero", '0');
		isSpecial.put("one", '1');
		isSpecial.put("two", '2');
		isSpecial.put("three", '3');
		isSpecial.put("four", '4');
		isSpecial.put("five", '5');
		isSpecial.put("six", '6');
		isSpecial.put("seven", '7');
		isSpecial.put("eight", '8');
		isSpecial.put("nine", '9');
		isSpecial.put("to", '2');
		isSpecial.put("for", '4');
		isSpecial.put("ate", '8');
		
		Scanner scan = new Scanner(System.in);
		
		//Loop for each sentence
		while (scan.hasNextLine()) {
			StringBuilder pass = new StringBuilder();
			
			String temp = scan.nextLine().toLowerCase();
			String temp2;
			
			for (int i = 0; i < temp.length(); i++) {
				if (isPunctuation(temp.charAt(i))) {
					if (temp.charAt(i) != '.')
						pass.append(temp.charAt(i));
				}
				else if (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') {
					temp2 = "";
					while (temp.charAt(i) >= 'a' && temp.charAt(i) <= 'z') {
						temp2 += temp.charAt(i);
						i++;
					}
					if (isSpecial.containsKey(temp2)) {
						pass.append(isSpecial.get(temp2));
						i--;
					}
					else {
						pass.append(temp2.charAt(0));
						i--;
					}
				}
			}
			System.out.println(pass.toString());
		}	
	}
}
