package fi.hy.demo.engine;

public class Tile {

  boolean merged;
  int value;

  Tile(int val) {
    value = val;
  }

  public Tile(int value, boolean merged) {
    this.merged = merged;
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setMerged(boolean merged) {
    this.merged = merged;
  }

  boolean canMergeWith(Tile other) {
    return !merged && other != null && !other.merged && value == other.getValue();
  }

  /**
   * Method to merge tiles.
   *
   * @param other tile to merge with.
   * @return value of merged tile.
   */
  public int mergeWith(Tile other) {
    if (canMergeWith(other)) {
      value *= 2;
      merged = true;
      return value;
    }
    return -1;
  }

  public Tile cloneTile() {
    return new Tile(this.value, this.merged);
  }
}
