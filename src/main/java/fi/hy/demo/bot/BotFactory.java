package fi.hy.demo.bot;

public class BotFactory {

  public static Bot getBot(String botName, int runs) throws BotNotFoundException {
    if( botName.equals("monte carlo")) {
      return new MonteCarlo(runs);
    } else if (botName.equals("random")) {
      return new RandomBot();
    }
    throw new BotNotFoundException();
  }
}
