/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new S_ORDER();

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		/* YOUR CODE HERE */
		// formula (y1 - y0) / (x1 - x0).
		int dx = that.x - this.x;
		int dy = that.y - this.y;

		if (dy == 0 && dx == 0)
			return Double.NEGATIVE_INFINITY;
		if (dx == 0)
			return Double.POSITIVE_INFINITY;
		if (dy == 0)
			return 0.0;

		return (double) dy / dx;

	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		/* YOUR CODE HERE */
		if (that.y == this.y)
			return (this.x - that.x);
		else
			return this.y - that.y;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	private class S_ORDER implements Comparator<Point> {

		public int compare(Point a, Point c) {
			double res = slopeTo(a) - slopeTo(c);
			if (res < 0.0)
				return -1;
			else if (res > 0.0)
				return 1;
			else
				return 0;
		}

	}

	// unit test
	public static void main(String[] args) {
		/* YOUR CODE HERE */
		Point p, q, r;
		p = new Point(87, 479);
		q = new Point(87, 479);
		assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
		p = new Point(25681, 22210);
		q = new Point(25681, 22210);
		assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
		p = new Point(3, 4);
		q = new Point(3, 4);
		assert p.slopeTo(q) == Double.NEGATIVE_INFINITY;
		p = new Point(8, 5);
		q = new Point(9, 4);
		r = new Point(8, 5);
		assert p.SLOPE_ORDER.compare(q, r) == 1;
		p.SLOPE_ORDER.compare(q, r);
		assert p.slopeTo(q) == -1.0;
		assert p.slopeTo(r) == Double.NEGATIVE_INFINITY;
	}

}
