package littleBishops;

public class binarylist {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		
		for  (int i = 0; i < Math.pow(2, n); i++)
			System.out.println(Integer.toBinaryString(i));

	}
}
