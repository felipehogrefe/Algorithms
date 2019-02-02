import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private Point[] points;
	private LineSegment[] segments = null;

	public BruteCollinearPoints(Point[] points) {
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
			for (int j = i + 1; j < pointsLength; j++) {
				Point q = points[j];
				double pqSlope = p.slopeTo(q);
				for (int k = j + 1; k < pointsLength; k++) {
					Point r = points[k];
					double qrSlope = q.slopeTo(r);
					if (pqSlope == qrSlope) {
						for (int l = k + 1; l < pointsLength; l++) {
							Point s = points[l];
							double rsSlope = r.slopeTo(s);
							if (qrSlope == rsSlope) {
								// add segments
								LineSegment l1 = new LineSegment(p, s);
								if (!list.contains(l1))
									list.add(l1);
							}
						}
					}
				}
			}
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
		Point[] points = new Point[n + 1];
		for (int i = 0; i < n - 1; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		points[n] = null;

		// draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

		// print and draw the line segments
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}
