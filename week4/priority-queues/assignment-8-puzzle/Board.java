import java.util.ArrayList;

public class Board {
  private int n;
  private int[][] tiles;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    this.n = tiles.length;
    this.tiles = new int[n][n];
    for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j) {
      this.tiles[i][j] = tiles[i][j];
    }
  }

  // string representation of this board
  public String toString() {
    String s = "";

    s = s.concat(dimension() + "\n");

    for (int i = 0; i < dimension(); i++) {
      for (int j = 0; j < dimension(); j++) {
        s = s.concat(" " + tiles[i][j]);
      }
      s = s.concat("\n");
    }

    return s;
  }

  // board dimension n
  public int dimension() {
    return n;
  }

  // number of tiles out of place
  public int hamming() {
    int cnt = 0;
    for (int i = 0; i < dimension(); ++i)
    for (int j = 0; j < dimension(); ++j) {
      int v = tiles[i][j];
      if (v == 0) continue;

      if (v != goalAt(i, j)) {
        ++cnt;
      }
    }
    return cnt;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int cnt = 0;
    for (int i = 0; i < dimension(); ++i)
    for (int j = 0; j < dimension(); ++j) {
      int v = tiles[i][j];
      v = v == 0 ? n * n : v;
      int x = (v - 1) / n;
      int y = (v - 1) % n;

      int xd = Math.abs(x - i);
      int xy = Math.abs(y - j);

      cnt += xd + xy;
    }
    return cnt;
  }

  // is this board the goal board?
  public boolean isGoal() {
    for (int i = 0; i < dimension(); ++i)
    for (int j = 0; j < dimension(); ++j) {
      if (tiles[i][j] != goalAt(i, j)) {
        return false;
      }
    }
    return true;
  }

  // does this board equal y?
  public boolean equals(Object y) {
    if (y == this) return true;
    if (y == null) return false;
    if (getClass() != y.getClass()) return false;

    Board other = (Board) y;
    if (dimension() != other.dimension()) return false;
    if (hamming()   != other.hamming())   return false;
    if (manhattan() != other.manhattan()) return false;

    return true;

    // for (int i = 0; i < dimension(); ++i) {
    // for (int j = 0; j < dimension(); ++j) {
    //   if (tiles[i][j] != other.tiles[i][j]) {
    //     return false;
    //   }
    // } }
    // return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    int emptyX = -1;
    int emptyY = -1;
    for (int i = 0; i < dimension(); ++i)
    for (int j = 0; j < dimension(); ++j) {
      if (tiles[i][j] == 0) {
        emptyX = i;
        emptyY = j;
        break;
      }
    }

    ArrayList<Board> neighbors = new ArrayList<Board>();
    if (emptyX > 0) neighbors.add(neighbour(emptyX, emptyY, -1,  0));
    if (emptyX < 2) neighbors.add(neighbour(emptyX, emptyY,  1,  0));
    if (emptyY > 0) neighbors.add(neighbour(emptyX, emptyY,  0, -1));
    if (emptyY < 2) neighbors.add(neighbour(emptyX, emptyY,  0,  1));
    return neighbors;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    Board twinBoard = new Board(tiles);

    if (tiles[0][0] == 0) twinBoard.swap(0, 1, 1, 1);
    else if (tiles[0][1] == 0) twinBoard.swap(0, 0, 1, 0);
    else twinBoard.swap(0, 0, 0, 1);

    return twinBoard;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    //
  }

  private int goalAt(int i, int j) {
    return i * n + j + 1;
  }

  private Board neighbour(int x, int y, int dx, int dy) {
    swap(x, y, x + dx, y + dy);
    Board board = new Board(tiles);
    swap(x, y, x + dx, y + dy);
    return board;
  }

  private void swap(int i, int j, int x, int y) {
    int tmp = tiles[i][j];
    tiles[i][j] = tiles[y][x];
    tiles[x][y] = tmp;
  }
}
