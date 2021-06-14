import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {
  private Board initial;
  private int moves;
  private boolean solvable;
  private Stack<Board> solution;

  // search node
  private static class Node {
    Board board;
    int moves;
    int priority;
    Node prev;
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException("initial argument to Solver constructor is null");
    }
    this.initial = initial;
    solve();
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return this.solvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return this.moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    return this.solution;
  }

  private void solve() {
    this.moves = -1;
    this.solvable = true;
    this.solution = new Stack<Board>();

    MinPQ<Node> pq = initPQ(initial);
    MinPQ<Node> twinpq = initPQ(initial.twin());

    while (true) {
      Node searchNode = pq.delMin();
      Node prevNode = searchNode.prev;
      Board searchBoard = searchNode.board;

      if (searchBoard.isGoal()) {
        this.solvable = true;
        this.moves = searchNode.moves;
        this.solution = findPath(searchNode);
        break;
      }

      for (Board b : searchBoard.neighbors()) {
        if (prevNode == null || !prevNode.board.equals(b)) {
          Node temp = new Node();
          temp.prev = searchNode;
          temp.board = b;
          temp.moves = searchNode.moves + 1;
          temp.priority = temp.moves + b.manhattan();
          pq.insert(temp);
        }
      }

      Node twinSearchNode = twinpq.delMin();
      Node twinPrevNode = twinSearchNode.prev;
      Board twinSearchBoard = twinSearchNode.board;

      if (twinSearchBoard.isGoal()) {
        this.solvable = false;
        break;
      }

      for (Board b : twinSearchBoard.neighbors()) {
        if (twinPrevNode == null || twinPrevNode.board.equals(b)) {
          Node temp = new Node();
          temp.prev = twinSearchNode;
          temp.board = b;
          temp.moves = twinSearchNode.moves + 1;
          temp.priority = temp.moves + b.manhattan();
          twinpq.insert(temp);
        }
      }
    }
  }

  private Comparator<Node> priority() {
    return new ByPriority();
  }

  private class ByPriority implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
      return n1.priority > n2.priority ? 1 : n1.priority < n2.priority ? -1 : 0;
    }
  }

  private MinPQ<Node> initPQ(Board root) {
    Node rootNode = new Node();
    rootNode.board = root;
    rootNode.moves = 0;
    rootNode.priority = root.manhattan();
    rootNode.prev = null;

    MinPQ<Node> pq = new MinPQ<Node>(priority());
    pq.insert(rootNode);
    return pq;
  }

  private Stack<Board> findPath(Node leaf) {
    Stack<Board> path = new Stack<Board>();
    Node temp = leaf;
    while (temp != null) {
      path.push(temp.board);
      temp = temp.prev;
    }
    return path;
  }

  // test client (see below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) {
      tiles[i][j] = in.readInt();
    }

    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");
    }
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) {
        StdOut.println(board);
      }
    }
  }
}
