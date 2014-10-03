import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class Fast {

	private static void findCollinearPoints(Point[] points) {
		Point[] points1 = new Point[points.length];
		Point[] pointspr = new Point[4];
		//double[] Slopes = new double[points.length-1];
		//Arrays.sort(points,1,2);
		for (int i = 0; i < points.length; i++) {
			//for (int j = i+1; j < points.length - 1; j++)
			//{
				//points1 = Arrays.copyOfRange(points,i+1,points.length);
			    //points1 = Arrays.copyOf(points,points.length);
			    System.arraycopy(points, 0, points1, 0, points.length);
			    //points1[i]= null;
				Arrays.sort(points1,  points[i].SLOPE_ORDER);//
				//Arrays.sort(points1);
				double t1 = points[i].slopeTo(points1[0]);
				
				if ((points1[0].slopeTo(points1[1]) == points1[0].slopeTo(points1[2])) &&(points1[0].slopeTo(points1[1]) == points1[0].slopeTo(points1[3])))
				{
					pointspr[0] = points[i]; pointspr[1] = points1[1]; pointspr[2] = points1[2]; pointspr[3] = points1[3]; 
					//Arrays.sort(pointspr);
					
					StdOut.print(pointspr[0].toString() + pointspr[1].toString()
							+ pointspr[2].toString() + pointspr[3].toString());
					StdOut.println();
			//};
		};
		
		};
	}
	public static void main(String[] args) {
	
	File file = new File("input8.txt");
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