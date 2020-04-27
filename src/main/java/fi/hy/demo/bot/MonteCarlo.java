package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;
import fi.hy.demo.engine.State;
import fi.hy.demo.random.CustomRandom;

/**
 * Monte Carlo bot for 2048 game.
 */
public class MonteCarlo implements Bot {

  private final Integer runs;
  private final CustomRandom customRandom;

  public MonteCarlo(Integer runs) {
    this.runs = runs;
    this.customRandom = new CustomRandom();
  }

  /**
   * Decide move return random direction.
   *
   * @param board Used for implementing best move.
   * @return Direction to go.
   */
  @Override
  public Direction decideMove(Board board) {

    long moveUp = 0;
    long moveUpCount = 1;

    long moveDown = 0;
    long moveDownCount = 1;

    long moveRight = 0;
    long moveRightCount = 1;

    long moveLeft = 0;
    long moveLeftCount = 1;

    int input;
    for (int i = 0; i < runs; i++) {

      Board boardCopy = board.copyBoard();
      long count = 0;
      long firstMove = -1;

      while (boardCopy.getGameState().equals(State.running)) {
        //generate random next move
        input = getRandom();

        makeMove(input, boardCopy);

        if (count == 0) {
          firstMove = input;
          if (input == 0) {
            moveUpCount++;
          } else if (input == 1) {
            moveDownCount++;
          } else if (input == 2) {
            moveRightCount++;
          } else if (input == 3) {
            moveLeftCount++;
          }
        }
        count++;
      }

      // sum random game scores
      if (firstMove == 0) {
        moveUp += boardCopy.getScore();
      } else if (firstMove == 1) {
        moveDown += boardCopy.getScore();
      } else if (firstMove == 2) {
        moveRight += boardCopy.getScore();
      } else {
        moveLeft += boardCopy.getScore();
      }
    }

    // random game averages
    moveUp = moveUp / moveUpCount;
    moveDown = moveDown / moveDownCount;
    moveRight = moveRight / moveRightCount;
    moveLeft = moveLeft / moveLeftCount;

    return getDirection(moveUp, moveDown, moveRight, moveLeft);
  }

  private int getRandom() {
    return customRandom.nextInt(4);
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
      return Direction.UP;
    } else if (moveRight >= moveDown && moveRight >= moveLeft) {
      return Direction.RIGHT;
    } else if (moveLeft >= moveDown) {
      return Direction.LEFT;
    } else {
      return Direction.DOWN;
    }
  }

  protected void makeMove(int input, Board boardCopy) {
    switch (input) {
      case 0:
        boardCopy.moveUp();
        break;
      case 1:
        boardCopy.moveDown();
        break;
      case 2:
        boardCopy.moveRight();
        break;
      case 3:
        boardCopy.moveLeft();
        break;
      default:
        boardCopy.moveUp();
        break;
    }
  }
}
