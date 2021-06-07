// A faster, sorting-based solution. Remarkably, it is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.
// - Think of p as the origin.
// - For each other point q, determine the slope it makes with p.
// - Sort the points according to the slopes they makes with p.
// - Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
// - Applying this method for each of the n points in turn yields an efficient algorithm to the problem. The algorithm solves the problem because points that have equal slopes with respect to p are collinear, and sorting brings such points together. The algorithm is fast because the bottleneck operation is sorting.

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FastCollinearPoints {
  private final Point[]       points;
  private       LineSegment[] segments;
  private       int           segmentsCount;

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

    this.points = new Point[points.length];
    for (int i = 0; i < points.length; i++) {
      this.points[i] = points[i];
    }

    if (!this.arePointsUnique()) {
      throw new IllegalArgumentException("points argument to FastCollinearPoints constructor contains a repeated point");
    }

    this.calculateSegments();
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segmentsCount;
  }

  // the line segments
  // The method segments() should include each line segment containing 4 points exactly once.
  // If 4 points appear on a line segment in the order p→q→r→s, then you should include either the line segment p→s or s→p (but not both) and you should not include subsegments such as p→r or q→r. For simplicity, we will not supply any input to FastCollinearPoints that has 5 or more collinear points.
  public LineSegment[] segments() {
    LineSegment[] copy = new LineSegment[this.segments.length];
    for (int i = 0; i < this.segments.length; i++) {
        copy[i] = this.segments[i]; // new LineSegment(segments[i].p, segments[i].q);
    }
    return copy;
  }

  private void calculateSegments() {
    this.segments = new LineSegment[0];

    Point p0 = this.points[0];

    Arrays.sort(this.points, (p1, p2) -> Double.compare(p0.slopeTo(p1), p0.slopeTo(p2)));

    int start  = 1;
    int finish = 1;
    double currentSlope = p0.slopeTo(this.points[0]);

    for(int i = 1; i < this.points.length; ++i) {
      double slope = p0.slopeTo(this.points[i]);

      if (slope == currentSlope) {
        finish = i;
      }

      if (slope != currentSlope || i == this.points.length - 1) {
        if (finish - start + 1 >= 4) {
          List<Point> sub = Arrays.asList(this.points).subList(start, finish + 1);
          Point pmin = Collections.min(sub);
          Point pmax = Collections.max(sub);

          this.segments = Arrays.copyOf(this.segments, segments.length + 1);
          this.segments[segments.length - 1] = new LineSegment(pmin, pmax);
        }

        start = i;
        finish = i;
        currentSlope = slope;
      }
    }

    this.segmentsCount = this.segments.length;
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
