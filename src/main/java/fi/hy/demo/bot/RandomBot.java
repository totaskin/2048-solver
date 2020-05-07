package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;
import fi.hy.demo.random.CustomRandom;

/**
 * Random bot that makes pseudo random moves.
 */
public class RandomBot implements Bot {

  private final CustomRandom customRandom;

  public RandomBot() {
    this.customRandom = new CustomRandom();
  }

  public RandomBot(fi.hy.demo.random.CustomRandom customRandom) {
    this.customRandom = customRandom;

  }

  /**
   * Decide move return random direction.
   *
   * @param tiles Can be used to implement best move.
   * @return Direction to go.
   */
  @Override
  public Direction decideMove(Board tiles) {
    int random = customRandom.nextInt(3);
    if (random == 0) {
      return Direction.UP;
    } else if (random == 1) {
      return Direction.RIGHT;
    } else if (random == 2) {
      return Direction.DOWN;
    } else {
      return Direction.LEFT;
    }
  }
}
