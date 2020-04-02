package fi.hy.demo.bot;

public class GameScore {
  private long time;
  private long score;
  private int highest;

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
