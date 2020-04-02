package fi.hy.demo.bot;

public class GameScore {
  private long time;
  private int score;
  private int highest;

  public GameScore(long time, int score, int highest) {
    this.time = time;
    this.score = score;
    this.highest = highest;
  }

  public long getTime() {
    return time;
  }

  public int getScore() {
    return score;
  }

  public int getHighest() {
    return highest;
  }
}
