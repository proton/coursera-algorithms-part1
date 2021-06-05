// A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

import java.util.HashSet;

public class FastCollinearPoints {
  private Point[] points;

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

    HashSet<Point> pointSet = new HashSet<>();

    for (Point point : points) {
      pointSet.add(point);
    }

    if (pointSet.size() != points.length) {
      throw new IllegalArgumentException("points argument to FastCollinearPoints constructor contains a repeated point");
    }

    this.points = points;
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments().length;
  }

  // the line segments
  // The method segments() should include each line segment containing 4 points exactly once.
  // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to FastCollinearPoints that has 5 or more collinear points.
  public LineSegment[] segments() {
    return null;
  }
}
