package fi.hy.demo.bot;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GameScoreTest {

  @Test
  public void testGameScore() {
    GameScore gameScore = new GameScore(100, 10, 1);
    assertThat(gameScore.getHighest(), is(1));
    assertThat(gameScore.getScore(), is(10l));
    assertThat(gameScore.getTime(), is(100l));
  }
}
