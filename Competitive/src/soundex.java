import java.util.Hashtable;
import java.util.Scanner;

public class soundex {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Hashtable<Character, Character> swap 
						= new Hashtable<Character, Character>();
		
		swap.put('a', '-'); swap.put('e', '-'); swap.put('i', '-');
		swap.put('o', '-'); swap.put('u', '-'); swap.put('y', '-');
		
		swap.put('h', '*'); swap.put('w', '*'); 
		
		swap.put('b', '1'); swap.put('f', '1'); swap.put('p', '1'); 
		swap.put('v', '1'); 
		
		swap.put('c', '2'); swap.put('g', '2'); swap.put('j', '2'); 
		swap.put('k', '2'); swap.put('q', '2'); swap.put('s', '2');
		swap.put('x', '2'); swap.put('z', '2'); 
		
		swap.put('d', '3'); swap.put('t', '3');
		swap.put('l', '4'); 
		swap.put('m', '5'); swap.put('n', '5');
		swap.put('r', '6');
		
		//For each word
		while (scan.hasNextLine()) {
			String original = scan.nextLine();
			StringBuilder soundex = new StringBuilder();
			
			//Convert each letter via Translation Table, omitting stars
			for (int i = 0; i < original.length(); i++) {
				if (swap.get(original.charAt(i)) == '*')
					continue;
				soundex.append(swap.get(original.charAt(i))); 
			}
			
			//Delete identical following digits
			for (int j = 1; j < soundex.length(); j++)
				if (soundex.charAt(j - 1) == soundex.charAt(j) 
									&& soundex.charAt(j) != '-') {
					soundex.deleteCharAt(j - 1);
					j--;
				}
			
			//Delete all dashes
			for (int k = 0; k < soundex.length(); k++)
				if (soundex.charAt(k) == '-') {
					soundex.deleteCharAt(k);
					k--;
				}
			
			//Append or Prepend first character letter
			if (swap.get(original.charAt(0)) >= '0' 
					&& swap.get(original.charAt(0)) <= '9')
				soundex.setCharAt(0, original.charAt(0));
			else
				soundex.insert(0, original.charAt(0));
			
			//Adjust length to 4
			if (soundex.length() > 4)
				soundex.delete(4, soundex.length());
			else
				while (soundex.length() < 4)
						soundex.append('0');
			
			System.out.println(original + " => " + soundex.toString());		
		}
	}
}
