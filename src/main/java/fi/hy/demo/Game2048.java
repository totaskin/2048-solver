package fi.hy.demo;

import fi.hy.demo.bot.Bot;
import fi.hy.demo.bot.MonteCarlo;
import fi.hy.demo.ui.GameEngine2048;

/**
 * Main class to run, will start default Monte carlo bot.
 *
 * @Author Tommi Taskinen
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
}
