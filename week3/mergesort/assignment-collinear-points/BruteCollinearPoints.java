// Brute force.
// Write a program BruteCollinearPoints.java that examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments.
// To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.

import java.util.HashSet;

public class BruteCollinearPoints {
  private Point[] points;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("points argument to BruteCollinearPoints constructor is null");
    }

    for (int i = 0; i < points.length; i++) {
      if (points[i] == null) {
        throw new IllegalArgumentException("points argument to BruteCollinearPoints constructor contains a null point");
      }
    }

    HashSet<Point> pointSet = new HashSet<>();

    for (Point point : points) {
      pointSet.add(point);
    }

    if (pointSet.size() != points.length) {
      throw new IllegalArgumentException("points argument to BruteCollinearPoints constructor contains a repeated point");
    }

    this.points = points;
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments().length;
  }

  // the line segments
  // The method segments() should include each line segment containing 4 points exactly once.
  // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
  public LineSegment[] segments() {
    return null;
  }
}
