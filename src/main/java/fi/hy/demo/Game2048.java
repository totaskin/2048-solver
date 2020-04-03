package fi.hy.demo;

import fi.hy.demo.bot.Bot;
import fi.hy.demo.bot.BotFactory;
import fi.hy.demo.bot.BotNotFoundException;
import fi.hy.demo.bot.Direction;
import fi.hy.demo.bot.GameScore;
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
  public static void main(String[] args) throws BotNotFoundException {
    Bot bot = BotFactory.getBot("MCPP", 2000);
    GameEngine2048 gameEngine2048 = new GameEngine2048(bot);
    gameEngine2048.startGame(bot);
  }

  /**
   * Method to run bot without UI
   *
   * @param botName Name of the bot to run with.
   * @param runs    How many iterations to run bot
   * @return Score how well bot did
   * @throws Exception            If bot return invalid move
   * @throws BotNotFoundException Thrown if bot is not found
   */
  public GameScore runBot(String botName, Integer runs) throws BotNotFoundException, Exception {
    Board board = new Board();
    board.restartGame();

    Bot bot = BotFactory.getBot(botName, runs);
    long startTime = System.currentTimeMillis();
    long round = 0;
    while (State.running.equals(board.getGameState())) {
      Direction direction = bot.decideMove(board);
      round++;
      if (round % 100 == 0) {
        // System.out.println("round 100");
      }
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
