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
    String s = new String();

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
      if (tiles[i][j] != goalAt(i, j)) {
        cnt++;
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

      int xd = Math.abs(x - j);
      int xy = Math.abs(y - i);

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
    return null;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    return null;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    //
  }

  private int goalAt(int i, int j) {
    return i * n + j + 1;
  }
}
