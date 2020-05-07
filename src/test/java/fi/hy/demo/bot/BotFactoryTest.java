package fi.hy.demo.bot;

import org.junit.jupiter.api.Test;

import static fi.hy.demo.bot.BotFactory.getBot;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BotFactoryTest {

  @Test
  public void testBotFactory() throws BotNotFoundException {

    assertThat(getBot("monte carlo", 1), instanceOf(MonteCarlo.class));
    assertThat(getBot("MCPP", 1), instanceOf(MonteCarloGameTree.class));
    assertThat(getBot("random", 1), instanceOf(RandomBot.class));
  }


  @Test
  public void testBotNotFound() throws BotNotFoundException {
    assertThrows(BotNotFoundException.class, () -> {
      getBot("notFound", 1);
    });
  }
}
