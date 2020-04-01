package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;

/**
 * Random bot that makes pseudo random moves.
 */
public class RandomBot implements Bot {

  /**
   * Decide move return random direction.
   *
   * @param tiles Can be used to implement best move.
   * @return Direction to go.
   */
  @Override
  public Direction decideMove(Board tiles) {
    double random = Math.random();
    if (random < 0.25) {
      return Direction.UP;
    } else if (random < 0.5) {
      return Direction.RIGHT;
    } else if (random < 0.75) {
      return Direction.DOWN;
    } else {
      return Direction.LEFT;
    }
  }
}
