package fi.hy.demo.bot;

import static fi.hy.demo.bot.Direction.DOWN;
import static fi.hy.demo.bot.Direction.LEFT;
import static fi.hy.demo.bot.Direction.RIGHT;
import static fi.hy.demo.bot.Direction.UP;

import fi.hy.demo.engine.Board;
import fi.hy.demo.engine.State;
import fi.hy.demo.random.CustomRandom;
/**
 * Monte Carlo bot for 2048 game.
 */

public class MonteCarloGameTree implements Bot {

  private final Integer runs;
  private final CustomRandom customRandom;

  public MonteCarloGameTree(Integer runs) {
    this.runs = runs;
    this.customRandom = new CustomRandom();
  }

  public MonteCarloGameTree(int runs, fi.hy.demo.random.CustomRandom customRandom) {
    this.runs = runs;
    this.customRandom = customRandom;
  }

  /**
   * Decide move return random direction.
   *
   * @param board Used for implementing best move.
   * @return Direction to go.
   */
  @Override
  public Direction decideMove(Board board) {
    long moveUpScore = getScore(board, UP);
    long moveDownCount = getScore(board, DOWN);
    long moveLeftScore = getScore(board, LEFT);
    long moveRightScore = getScore(board, RIGHT);
    return getDirection(moveUpScore, moveDownCount, moveRightScore, moveLeftScore);
  }

  protected long getScore(Board board, Direction direction) {
    long tileScore = 0;
    for (int i = 0; i < runs; i++) {
      Board boardCopy = board.copyBoard();
      makeMove(direction, boardCopy);


      while (boardCopy.getGameState().equals(State.running)) {
        Direction randomDirection = getRandom();
        makeMove(randomDirection, boardCopy);
      }
      tileScore += boardCopy.getScore();
    }
    return tileScore / runs;
  }

  protected Direction getRandom() {
    int random = customRandom.nextInt(4) + 1;
    switch (random) {
      case 1:
        return UP;
      case 2:
        return DOWN;
      case 3:
        return RIGHT;
      default:
        return LEFT;
    }
  }

  /**
   * Helper method to calculate best move.
   *
   * @param moveUp    How many times move up is best.
   * @param moveDown  How many times move down is best.
   * @param moveRight How many times move right is best.
   * @param moveLeft  How many times move left is best.
   * @return Direction
   */
  protected Direction getDirection(long moveUp, long moveDown, long moveRight, long moveLeft) {
    if (moveUp >= moveDown && moveUp >= moveLeft && moveUp >= moveRight) {
      return UP;
    } else if (moveRight >= moveDown && moveRight >= moveLeft) {
      return RIGHT;
    } else if (moveLeft >= moveDown) {
      return LEFT;
    } else {
      return DOWN;
    }
  }

  protected void makeMove(Direction direction, Board boardCopy) {
    switch (direction) {
      case DOWN:
        boardCopy.moveDown();
        break;
      case RIGHT:
        boardCopy.moveRight();
        break;
      case LEFT:
        boardCopy.moveLeft();
        break;
      default:
        boardCopy.moveUp();
        break;
    }
  }
}
