

import java.util.Scanner;


public class buffalo {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String line;
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] arr = line.split(" ");
			Point p1 = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
			Point p2 = new Point(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
			Point p3 = new Point(Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
			Point test = new Point(Integer.parseInt(arr[6]), Integer.parseInt(arr[7]));
			
			System.out.print(isRight(p1, p2, test));
			System.out.print(isRight(p2, p3, test));
			System.out.print(isRight(p3, p1, test) + "\n");
			
			
		}
	}
	
	
	public static String isRight(Point a, Point b, Point c) {
		int t = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
		if (t <= 0) return "R";
		else return "L";
	}

}




class Point{
	int x, y;
	
	public Point(int p1, int p2) {
		this.x = p1;
		this.y = p2;
	}
	
	public Point coefficientMult(int p) {
		int x = this.x * p;
		int y = this.y * p;
		
		return new Point(x, y);
	}
	
	public Point pointAddition(Point p1) {
		int x = this.x + p1.getX();
		int y = this.y + p1.getY();
		return new Point(x, y);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String toString() {
		return  String.format("%5.1f", this.x)+ String.format("%5.1f", this.y);
	}
}

