package fi.hy.demo;

import fi.hy.demo.bot.Bot;
import fi.hy.demo.bot.BotFactory;
import fi.hy.demo.bot.BotNotFoundException;
import fi.hy.demo.bot.Direction;
import fi.hy.demo.bot.GameScore;
import fi.hy.demo.bot.MonteCarlo;
import fi.hy.demo.engine.Board;
import fi.hy.demo.engine.State;
import fi.hy.demo.ui.GameEngine2048;

/**
 * Main class to run, will start default Monte carlo bot.
 */
public class Game2048 {

  /**
   * Main method which starts UI and defult bot.
   *
   * @param args Can give how many rounds to iterage.
   */
  public static void main(String[] args) {
    Bot monteCarlo = new MonteCarlo(1000);
    GameEngine2048 gameEngine2048 = new GameEngine2048(monteCarlo);
    gameEngine2048.startGame(monteCarlo);
  }


  public GameScore runBot(String botName, Integer runs) throws Exception, BotNotFoundException {
    Board board = new Board();
    board.restartGame();

    Bot bot = BotFactory.getBot(botName, runs);
    long startTime = System.currentTimeMillis();
    while (State.running.equals(board.getGameState())) {
      Direction direction = bot.decideMove(board);
      switch (direction) {
        case UP:
          board.moveUp();
          break;
        case DOWN:
          board.moveDown();
          break;
        case LEFT:
          board.moveLeft();
          break;
        case RIGHT:
          board.moveRight();
          break;
        default:
          throw new Exception("Invalida decision");
      }
    }

    long totalTime = System.currentTimeMillis() - startTime;
    return new GameScore(totalTime, board.getScore(), board.getHighest());
  }
}
