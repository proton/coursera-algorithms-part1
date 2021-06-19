import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

// 2d-tree implementation. Write a mutable data type KdTree.java that uses a 2d-tree to implement the same API (but replace PointSET with KdTree). A 2d-tree is a generalization of a BST to two-dimensional keys. The idea is to build a BST with points in the nodes, using the x- and y-coordinates of the points as keys in strictly alternating sequence.

public class KdTree {
  private class Node {
    private Point2D point;
    private Node left;
    private Node right;
    private boolean isVertical;

    public Node(Point2D point, boolean isVertical) {
      this.point = point;
      this.isVertical = isVertical;
    }

    public int size() {
      int size = 1;
      if (this.left != null) {
        size += this.left.size();
      }
      if (this.right != null) {
        size += this.right.size();
      }
      return size;
    }

    public Node insert(Point2D p) {
      if (this.point.equals(p)) {
        return this;
      }
      if (this.isVertical) {
        if (p.x() < this.point.x()) {
          if (this.left == null) {
            this.left = new Node(p, false);
          } else {
            this.left = this.left.insert(p);
          }
        } else {
          if (this.right == null) {
            this.right = new Node(p, false);
          } else {
            this.right = this.right.insert(p);
          }
        }
      } else {
        if (p.y() < this.point.y()) {
          if (this.left == null) {
            this.left = new Node(p, true);
          } else {
            this.left = this.left.insert(p);
          }
        } else {
          if (this.right == null) {
            this.right = new Node(p, true);
          } else {
            this.right = this.right.insert(p);
          }
        }
      }
      return this;
    }

    public boolean contains(Point2D p) {
      if (this.point.equals(p)) {
        return true;
      }
      if (this.isVertical) {
        if (p.x() < this.point.x()) {
          if (this.left == null) {
            return false;
          }
          return this.left.contains(p);
        } else {
          if (this.right == null) {
            return false;
          }
          return this.right.contains(p);
        }
      } else {
        if (p.y() < this.point.y()) {
          if (this.left == null) {
            return false;
          }
          return this.left.contains(p);
        } else {
          if (this.right == null) {
            return false;
          }
          return this.right.contains(p);
        }
      }
    }

    public void draw() {
      this.point.draw();
      if (this.left != null) {
        this.left.draw();
      }
      if (this.right != null) {
        this.right.draw();
      }
    }
  }

  private Node root;

  // construct an empty set of points
  public KdTree() {
    this.root = null;
  }
  // is the set empty?
  public boolean isEmpty() {
    return this.root == null;
  }
  // number of points in the set
  public int size() {
    if (this.isEmpty()) return 0;
    return this.root.size();
  }
  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    if (p == null) throw new IllegalArgumentException("insert method called with a null argument");
    this.root = this.root == null ? new Node(p, true) : this.root.insert(p);
  }
  // does the set contain point p?
  public boolean contains(Point2D p) {
    if (p == null) throw new IllegalArgumentException("contains method called with a null argument");
    if (this.isEmpty()) return false;
    return this.root.contains(p);
  }
  // draw all points to standard draw
  public void draw() {
    if (this.isEmpty()) return;

    this.root.draw();
  }
  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    if (rect == null) throw new IllegalArgumentException("range method called with a null argument");

    ArrayList<Point2D> points = new ArrayList<Point2D>();
    if (this.isEmpty()) return points;

    rangeSearch(rect, root, points);

    return points;
  }

  private void rangeSearch(RectHV rect, Node node, ArrayList<Point2D> points) {
    if (node == null) return;

    if (rect.contains(node.point)) {
      points.add(node.point);
    }

    if (node.isVertical) {
      if (rect.xmin() < node.point.x()) {
        rangeSearch(rect, node.left, points);
      }
      if (rect.xmax() >= node.point.x()) {
        rangeSearch(rect, node.right, points);
      }
    } else {
      if (rect.ymin() < node.point.y()) {
        rangeSearch(rect, node.left, points);
      }
      if (rect.ymax() >= node.point.y()) {
        rangeSearch(rect, node.right, points);
      }
    }
  }
  // // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    return nearestSearch(p, this.root, null, Double.POSITIVE_INFINITY);
  }

  private Point2D nearestSearch(Point2D p, Node node, Point2D nearest, double minDistance) {
    if (node == null) return nearest;

    double distance = p.distanceSquaredTo(node.point);
    if (distance < minDistance) {
      nearest = node.point;
      minDistance = distance;
    }

    if (node.isVertical) {
      if (p.x() < node.point.x()) {
        nearest = nearestSearch(p, node.left, nearest, minDistance);
        nearest = nearestSearch(p, node.right, nearest, nearest.distanceSquaredTo(p));
      } else {
        nearest = nearestSearch(p, node.right, nearest, minDistance);
        nearest = nearestSearch(p, node.left, nearest, nearest.distanceSquaredTo(p));
      }
    } else {
      if (p.y() < node.point.y()) {
        nearest = nearestSearch(p, node.left, nearest, minDistance);
        nearest = nearestSearch(p, node.right, nearest, nearest.distanceSquaredTo(p));
      } else {
        nearest = nearestSearch(p, node.right, nearest, minDistance);
        nearest = nearestSearch(p, node.left, nearest, nearest.distanceSquaredTo(p));
      }
    }

    return nearest;
  }

  // unit testing of the methods (optional)
  public static void main(String[] args) {
    // TODO:
  }
}
