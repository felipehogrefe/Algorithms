import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private Point[] points;
	private LineSegment[] segments = null;

	public FastCollinearPoints(Point[] points) {
		if (points == null)
			throw new IllegalArgumentException();
		this.points = points;
		runChecks();
		Arrays.sort(points);
		checkDuplicates();
		defineSegments();

	}

	private void checkDuplicates() {
		for (int i = 0; i < points.length - 1; i++) {
			if (points[i].compareTo(points[i + 1]) == 0)
				throw new IllegalArgumentException();
		}

	}

	private void runChecks() {
		if (points == null)
			throw new IllegalArgumentException();
		if (points.length <= 0)
			throw new IllegalArgumentException();

		for (Point p : points) {
			if (p == null)
				throw new IllegalArgumentException();
		}

	}

	private void defineSegments() {

		List<LineSegment> list = new LinkedList<LineSegment>();
		int pointsLength = points.length;
		for (int i = 0; i < pointsLength; i++) {
			Point p = points[i];


			Point[] orderedBySlope = points.clone();

			Arrays.sort(orderedBySlope, p.slopeOrder());

			int index = 1;

//			while (index < pointsLength) {
				LinkedList<Point> toBeAdded = new LinkedList<>();

				final double currentSlope = p.slopeTo(orderedBySlope[index]);

				
				
				
				do {
					toBeAdded.add(orderedBySlope[index++]);
				} while (index < pointsLength && p.slopeTo(orderedBySlope[index]) == currentSlope);

				if(toBeAdded.size() >= 3 && p.compareTo(toBeAdded.peek())<0) {
					list.add(new LineSegment(p,toBeAdded.removeLast()));
				}
				
//			}

		}
		segments = list.toArray(new LineSegment[0]);

	}

	public int numberOfSegments() {
		return segments.length;
	}

	public LineSegment[] segments() {
		if (segments == null) {
			defineSegments();
		}
		return segments;
	}

	public static void main(String[] args) {
		// read the n points from a file
		In in = new In(args[0]);
		int n = in.readInt();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

//		 draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
