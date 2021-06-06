// A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class FastCollinearPoints {
  private Point[]       points;
  private LineSegment[] segments;

  // finds all line segments containing 4 points
  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("points argument to FastCollinearPoints constructor is null");
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException("points argument to FastCollinearPoints constructor contains a null point");
      }
    }

    this.points   = points;
    this.segments = null;

    if (!this.arePointsUnique()) {
      throw new IllegalArgumentException("points argument to FastCollinearPoints constructor contains a repeated point");
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments().length;
  }

  // the line segments
  // The method segments() should include each line segment containing 4 points exactly once.
  // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to FastCollinearPoints that has 5 or more collinear points.
  public LineSegment[] segments() {
    if (this.segments != null) {
      return this.segments;
    }

    this.segments = new LineSegment[0];

    for (int pi1 = 0;       pi1 < this.points.length - 3; ++pi1) {
    for (int pi2 = pi1 + 1; pi2 < this.points.length - 2; ++pi2) {
    for (int pi3 = pi2 + 1; pi3 < this.points.length - 1; ++pi3) {
    for (int pi4 = pi3 + 1; pi4 < this.points.length;     ++pi4) {
      Point p1 = this.points[pi1];
      Point p2 = this.points[pi2];
      Point p3 = this.points[pi3];
      Point p4 = this.points[pi4];

      if (this.pointsAreCollinear(p1, p2, p3, p4)) {
        LineSegment segment = this.pointsToSegment(p1, p2, p3, p4);
        this.addSegment(segment);
      }
    } } } }

    return null;
  }

  private boolean pointsAreCollinear(Point p1, Point p2, Point p3, Point p4) {
    return p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p3) == p1.slopeTo(p4);
  }

  private LineSegment pointsToSegment(Point p1, Point p2, Point p3, Point p4) {
    List<Point> points = Arrays.asList(p1, p2, p3, p4);
    Point pmin = Collections.min(points);
    Point pmax = Collections.max(points);

    return new LineSegment(pmin, pmax);
  }

  private void addSegment(LineSegment segment) {
    this.segments = Arrays.copyOf(this.segments, segments.length + 1);
    this.segments[segments.length - 1] = segment;
  }

  private boolean arePointsUnique() {
    for (int i = 0;     i < this.points.length; ++i) {
    for (int j = i + 1; j < this.points.length; ++j) {
        if (this.points[i].compareTo(this.points[j]) == 0) {
          return false;
        }
      }
    }
    return true;
  }
}
