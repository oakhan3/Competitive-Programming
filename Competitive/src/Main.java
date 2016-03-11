import java.util.Hashtable;
import java.util.Scanner;

class Main implements Runnable{
	static int n, k, solution;	
	
	@SuppressWarnings("unchecked")
	private static void backtrack(int b, int x, int y, Hashtable<String, Boolean> frwd, Hashtable<String, Boolean> back) {
		if (b < k) {
			if (markable(x, y, frwd, back)) {
				@SuppressWarnings("unchecked")
				Hashtable<String, Boolean> frwdcop = (Hashtable<String, Boolean>) frwd.clone();
				Hashtable<String, Boolean> backcop= (Hashtable<String, Boolean>) back.clone();
				
				markDiagonal(x, y, frwd, back);
				
				if (b + 1 == k)
					solution++;
							
				if (x < n - 1)
					backtrack(b + 1, x + 1, y, frwd, back);
				else if (y < n - 1) 
					backtrack(b + 1, 0, y + 1, frwd, back);
					
				frwd = (Hashtable<String, Boolean>) frwdcop.clone();
				back = (Hashtable<String, Boolean>) backcop.clone();
			}
				
			 if (x < n - 1)
				backtrack(b, x + 1, y, frwd, back);
			 else if (y < n - 1) 
				backtrack(b, 0, y + 1, frwd, back);
		}
	}
	
	private static void markDiagonal(int x, int y, Hashtable<String, Boolean> frwd, Hashtable<String, Boolean> back){//, boolean[] frwd, boolean[] back) {
		
		//gives top right
		int a = x - Math.min(x, y);
		int b = y - Math.min(x, y);
		
		//gives top left
		while (x > 0 && y < n - 1) {
			x--;
			y++;
		}

		frwd.put(x+" "+y, false);
		back.put(a+" "+b, false);
		//System.out.println(a + " " + b); //lean back
		//System.out.println(x + " " + y); //lean frwd
	}
	
	private static boolean markable(int x, int y, Hashtable<String, Boolean> diagf, Hashtable<String, Boolean> diagb) {
		int a = x - Math.min(x, y);
		int b = y - Math.min(x, y);
		
		//gives top left
		while (x > 0 && y < n - 1) {
			x--;
			y++;
		}

		//System.out.println(a + " " + b); //lean back
		if (!diagb.get(a+" "+b))
			return false;
		//System.out.println(x + " " + y); //lean frwd
		if(!diagf.get(x+" "+y))
			return false;
			
		return true;
	}
	
	private static void setup() {
		Hashtable<String, Boolean> diagb = new Hashtable<String, Boolean>();
		Hashtable<String, Boolean> diagf = new Hashtable<String, Boolean>();
		
//		diagfrwd = new int[(n*2)-1][2];
//		diagback = new int[(n*2)-1][2];
		
//		System.out.println((n/2)+1);

		int a = n-1;
		int b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (a < 0)
				a = 0;
			if (i > ((n*2)-1)/2)
				b++;
			//diagback[i][0] = a;
			//diagback[i][1] = b;
			diagb.put(a+" "+b, true);
			a--;
		}
		
//		for (int  i = 0; i < diagback.length; i++) {
//			System.out.print(diagback[i][0]);
//			System.out.print(diagback[i][1] + " ");	
//		}
		
		a = 0;
		b = 0;
		
		for (int i = 0; i < (n*2)-1; i++) {
			if (i > ((n*2)-1)/2)
				a++;
			//diagfrwd[i][0] = a;
			//diagfrwd[i][1] = b;
			diagf.put(a+" "+b, true);
			if (b != (n-1))
				b++;
		}
		
//		for (int  i = 0; i < diagback.length; i++) {
//			System.out.print(diagfrwd[i][0]);
//			System.out.print(diagfrwd[i][1] + " ");	
//		}
		backtrack(0, 0, 0, diagf, diagb);
	}
	
	public static void main(String args[])  {
		Main myWork = new Main();
	    myWork.run();
	}
	
    public void run(){
    	Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			n = scan.nextInt();
			k = scan.nextInt();
			
			if (n != 0 || k != 0) {
				if (n == 0 || k == 0) {
					System.out.println("0");
				}
				else {
					solution = 0;
					setup();
					
					//System.out.println(solution);
				}
			}
		}
		scan.close();
    }
}
