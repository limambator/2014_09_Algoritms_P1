import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Brute {

	private static void findCollinearPoints(Point[] points) {
		Point[] pointspr = new Point[4];
		
		for (int a = 0; a < points.length; a++) {
			Point aPoint = points[a];
			for (int b = a + 1; b < points.length; b++) {
				Point bPoint = points[b];
				double ab = aPoint.slopeTo(bPoint);
				for (int c = b + 1; c < points.length; c++) {
					Point cPoint = points[c];
					double ac = aPoint.slopeTo(cPoint);
					for (int d = c + 1; d < points.length; d++) {
						Point dPoint = points[d];
						double ad = aPoint.slopeTo(dPoint);
						if (ab == ac && ab == ad) {
							pointspr[0] = aPoint; pointspr[1] = bPoint; pointspr[2] = cPoint; pointspr[3] = dPoint; 
							Arrays.sort(pointspr);
							
							StdOut.print(pointspr[0].toString() + pointspr[1].toString()
									+ pointspr[2].toString() + pointspr[3].toString());
							StdOut.println();
						}
					}
				}

			}

		}
	}

	public static void main(String[] args) {

		/*
		 * String filename = args[0]; In in = new In(filename); int N =
		 * in.readInt(); Point[] points = new Point[N];
		 * 
		 * for (int i = 0; i < N; i++) { int x = in.readInt(); int y =
		 * in.readInt(); points[i] = new Point(x, y);
		 * 
		 * }
		 */

		File file = new File("input6.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			int N = sc.nextInt();
			Point[] points = new Point[N];
			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				points[i] = new Point(x, y);
			}
			findCollinearPoints(points);
			/*
			 * while(sc.hasNextLine()){ System.out.println(sc.nextLine()); };
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
