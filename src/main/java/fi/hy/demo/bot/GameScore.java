package fi.hy.demo.bot;

public class GameScore {
  private long time;
  private long score;
  private int highest;

  /**
   * Constructor to create game score class.
   * @param time How long bot used time to run
   * @param score Score after game ended
   * @param highest Highest time
   */
  public GameScore(long time, long score, int highest) {
    this.time = time;
    this.score = score;
    this.highest = highest;
  }

  public long getTime() {
    return time;
  }

  public long getScore() {
    return score;
  }

  public int getHighest() {
    return highest;
  }
}
