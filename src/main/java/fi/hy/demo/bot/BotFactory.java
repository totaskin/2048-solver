package fi.hy.demo.bot;

/**
 * Class to create bots.
 */
public class BotFactory {

  /**
   * Method to get bot implementation.
   *
   * @param botName Bot to be constructed
   * @param runs    How many iterations bot is allowed to run
   * @return Bot instance
   * @throws BotNotFoundException If bot is not found
   */
  public static Bot getBot(String botName, int runs) throws BotNotFoundException {
    if (botName.equals("monte carlo")) {
      return new MonteCarlo(runs);
    } else if (botName.equals("MCPP")) {
      return new MonteCarloGameTree(runs);
    } else if (botName.equals("random")) {
      return new RandomBot();
    }
    throw new BotNotFoundException();
  }
}
