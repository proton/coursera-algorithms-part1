import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
  private TreeSet<Point2D> tree;

  // construct an empty set of points
  public PointSET() {
    this.tree = new TreeSet<Point2D>();
  }
  // is the set empty?
  public boolean isEmpty() {
    return this.tree.isEmpty();
  }
  // number of points in the set
  public int size() {
    return tree.size();
  }
  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException("insert method called with a null argument");
    tree.add(p);
  }
  // // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException("contains method called with a null argument");
    return tree.contains(p);
  }
  // draw all points to standard draw
  public void draw() {
    for (Point2D p : tree) {
      p.draw();
    }
  }
  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException("range method called with a null argument");
    ArrayList<Point2D> points = new ArrayList<Point2D>();
    for (Point2D p : tree) {
      if (rect.contains(p)) {
        points.add(p);
      }
    }
    return points;
  }
  // // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (p == null) throw new IllegalArgumentException("nearest method called with a null argument");
    Point2D nearest = null;
    double minDistance = Double.POSITIVE_INFINITY;
    for (Point2D point : tree) {
      double distance = p.distanceSquaredTo(point);
      if (distance < minDistance) {
        nearest = point;
        minDistance = distance;
      }
    }
    return nearest;
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
    // TODO:
  }
}
