package fi.hy.demo.random;

import fi.hy.demo.bot.Direction;
import fi.hy.demo.bot.RandomBot;
import org.junit.jupiter.api.Test;

import static fi.hy.demo.bot.Direction.DOWN;
import static fi.hy.demo.bot.Direction.LEFT;
import static fi.hy.demo.bot.Direction.RIGHT;
import static fi.hy.demo.bot.Direction.UP;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomRandomTest {

  public static final int MAX_VALUE = 5;

  @Test
  public void testRandom() {
    CustomRandom customRandom = new CustomRandom();
    int max = 0;
    for (int i = 0; i < 100; i++) {
      int nextInt = customRandom.nextInt(MAX_VALUE);
      if (nextInt > max) {
        max = nextInt;
      }
    }
    assertThat(max, is(lessThan(MAX_VALUE)));
    assertThat(max, is(greaterThanOrEqualTo(0)));
  }

  @Test
  public void shouldDecideMoveUp() {
    fi.hy.demo.engine.Board boardMock = mock(fi.hy.demo.engine.Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    RandomBot bot = new RandomBot(randomMock);
    when(randomMock.nextInt(anyInt())).thenReturn(0);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, org.hamcrest.Matchers.is(UP));
  }

  @Test
  public void shouldDecideMoveRight() {
    fi.hy.demo.engine.Board boardMock = mock(fi.hy.demo.engine.Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    RandomBot bot = new RandomBot(randomMock);
    when(randomMock.nextInt(anyInt())).thenReturn(1);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, org.hamcrest.Matchers.is(RIGHT));
  }

  @Test
  public void shouldDecideMoveDown() {
    fi.hy.demo.engine.Board boardMock = mock(fi.hy.demo.engine.Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    RandomBot bot = new RandomBot(randomMock);
    when(randomMock.nextInt(anyInt())).thenReturn(2);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, org.hamcrest.Matchers.is(DOWN));
  }

  @Test
  public void shouldDecideMoveLeft() {
    fi.hy.demo.engine.Board boardMock = mock(fi.hy.demo.engine.Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    RandomBot bot = new RandomBot(randomMock);
    when(randomMock.nextInt(anyInt())).thenReturn(3);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, org.hamcrest.Matchers.is(LEFT));
  }
}
