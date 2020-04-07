package fi.hy.demo.engine;


import java.util.Random;

public class Board {

  final int target = 4096;
  private final int side = 4;
  private final Random rand = new Random();
  private Tile[][] tiles;
  private int highest;
  private long score;
  private State gamestate = State.start;
  private boolean checkingAvailableMoves;

  public Board() {
    this.score = 0;
    this.highest = 0;
  }

  /**
   * Constructor for new Board.
   *
   * @param clone     game state to intitialze.
   * @param score     score to start with.
   * @param highest   highest tile at game.
   * @param gamestate State of game.
   */
  public Board(Tile[][] clone, long score, int highest, State gamestate) {
    this.tiles = clone;
    this.score = score;
    this.highest = highest;
    this.gamestate = gamestate;
  }

  public void setState(State state) {
    this.gamestate = state;
  }
  private boolean move(int countDownFrom, int incrementY, int incrementX) {
    boolean moved = false;

    for (int i = 0; i < side * side; i++) {
      int currentTileIndex = Math.abs(countDownFrom - i);

      int indexOfX = currentTileIndex / side;
      int indexOfY = currentTileIndex % side;

      if (tiles[indexOfX][indexOfY] == null) {
        continue;
      }

      int nextIndexX = indexOfX + incrementY;
      int nextIndexY = indexOfY + incrementX;

      while (nextIndexX >= 0 && nextIndexX < side && nextIndexY >= 0 && nextIndexY < side) {

        Tile nextTile = this.tiles[nextIndexX][nextIndexY];
        Tile currentTile = this.tiles[indexOfX][indexOfY];

        if (nextTile == null) {

          if (this.checkingAvailableMoves) {
            return true;
          }

          this.tiles[nextIndexX][nextIndexY] = currentTile;
          this.tiles[indexOfX][indexOfY] = null;
          indexOfX = nextIndexX;
          indexOfY = nextIndexY;
          nextIndexX += incrementY;
          nextIndexY += incrementX;
          moved = true;

        } else if (nextTile.canMergeWith(currentTile)) {

          if (this.checkingAvailableMoves) {
            return true;

          }

          int value = nextTile.mergeWith(currentTile);
          if (value > this.highest) {
            this.highest = value;
          }
          this.score += value;
          this.tiles[indexOfX][indexOfY] = null;
          moved = true;
          break;
        } else {
          break;
        }
      }
    }

    if (moved) {
      if (this.highest < target) {
        clearMerged();
        addRandomTile();
        if (!movesAvailable()) {
          this.gamestate = State.over;
        }
      } else if (highest == target) {
        this.gamestate = State.won;
      }
    }

    return moved;
  }


  private boolean movesAvailable() {
    checkingAvailableMoves = true;
    boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
    checkingAvailableMoves = false;
    return hasMoves;
  }

  private void addRandomTile() {
    int position = rand.nextInt(side * side);
    int row;
    int column;
    do {
      position = (position + 1) % (side * side);
      row = position / side;
      column = position % side;
    } while (tiles[row][column] != null);

    int val = rand.nextInt(10) == 0 ? 4 : 2;
    tiles[row][column] = new Tile(val);
  }

  private void clearMerged() {
    for (Tile[] row : tiles) {
      for (Tile tile : row) {
        if (tile != null) {
          tile.setMerged(false);
        }
      }
    }
  }

  public boolean moveUp() {
    return move(0, -1, 0);
  }

  public boolean moveDown() {
    return move(side * side - 1, 1, 0);
  }

  public boolean moveLeft() {
    return move(0, 0, -1);
  }

  public boolean moveRight() {
    return move(side * side - 1, 0, 1);
  }

  /**
   * Method to reset score and start new game.
   */
  public void restartGame() {
    if (gamestate != State.running) {
      score = 0;
      highest = 0;
      gamestate = State.running;
      tiles = new Tile[side][side];
      addRandomTile();
      addRandomTile();
    }
  }

  public State getGameState() {
    return this.gamestate;
  }

  public Tile[][] getTiles() {
    return tiles;
  }

  public long getScore() {
    return score;
  }

  public int getHighest() {
    return highest;
  }

  /**
   * Creates new board with current state.
   *
   * @return New board with current state.
   */
  public Board copyBoard() {
    Tile[][] tiles = new Tile[side][side];
    for (int x = 0; x < side; x++) {
      for (int y = 0; y < side; y++) {
        Tile tile = this.tiles[x][y];
        if (tile != null) {
          tiles[x][y] = tile.cloneTile();
        }
      }
    }


    return new Board(tiles, this.score, this.highest, this.gamestate);
  }


}
