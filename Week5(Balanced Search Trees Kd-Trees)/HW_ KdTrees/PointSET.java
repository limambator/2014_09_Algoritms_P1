import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {
	private final Set<Point2D> points;

	public PointSET() // construct an empty set of points
	{
		this.points = new TreeSet<Point2D>();// the red-black BST
	}

	public boolean isEmpty() // is the set empty?
	{
		return points.isEmpty();
	}

	public int size() // number of points in the set
	{
		return points.size();
	}

	public void insert(Point2D p) // add the point to the set (if it is not already in the set)
	{
		points.add(p);
	}

	public boolean contains(Point2D p) // does the set contain point p?
	{
		return points.contains(p);
	}

	public void draw() // draw all points to standard draw
	{
		for (Point2D point : points) {
			point.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) // all points that are inside the rectangle
	{
		if (this.points.isEmpty())
			return null;
		Stack<Point2D> range = new Stack<Point2D>();

		for (Point2D point : points) {
			if (rect.contains(point))
				range.push(point);
		}

		return range;
	}

	public Point2D nearest(Point2D p) {
		Point2D minPoint = null;
		double minDistance = Double.POSITIVE_INFINITY;
		for (Point2D point : points) {
			double distance = p.distanceSquaredTo(point);
			if (Double.compare(distance, minDistance) < 0) {
				minPoint = point;
				minDistance = distance;
			}
		}
		return minPoint;
	}
	 
}