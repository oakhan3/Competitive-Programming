import java.util.Scanner;

public class buffalo {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String line;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] arr = line.split(" ");
			Point2 p1 = new Point2(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			Point2 p2 = new Point2(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
			Point2 p3 = new Point2(Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
			Point2 test = new Point2(Integer.parseInt(arr[6]), Integer.parseInt(arr[7]));
			
			System.out.print(isRight(p1, p2, test));
			System.out.print(isRight(p2, p3, test));
			System.out.print(isRight(p3, p1, test) + "\n");
		}
	}
	
	public static String isRight(Point2 a, Point2 b, Point2 c) {
		//determinant
		int t = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
		if (t <= 0) return "R";
		else return "L";
	}

}

class Point2{
	int x, y;
	
	public Point2(int p1, int p2) {
		this.x = p1;
		this.y = p2;
	}
}

