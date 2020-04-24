package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;
import fi.hy.demo.engine.State;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MonteCarloGameTreeTest {

  @Test
  public void testDirection() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Direction direction = bot.getDirection(1, 2, 3, 4);
    assertThat(direction, is(equalTo(Direction.LEFT)));

    direction = bot.getDirection(1, 2, 5, 4);
    assertThat(direction, is(equalTo(Direction.RIGHT)));

    direction = bot.getDirection(1, 6, 5, 4);
    assertThat(direction, is(equalTo(Direction.DOWN)));

    direction = bot.getDirection(7, 6, 5, 4);
    assertThat(direction, is(equalTo(Direction.UP)));
  }

  @Test
  public void testMoveDown() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Board mock = mock(Board.class);
    bot.makeMove(Direction.DOWN, mock);
    verify(mock).moveDown();
  }

  @Test
  public void testMoveUp() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Board mock = mock(Board.class);
    bot.makeMove(Direction.UP, mock);
    verify(mock).moveUp();
  }

  @Test
  public void testMoveRight() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Board mock = mock(Board.class);
    bot.makeMove(Direction.RIGHT, mock);
    verify(mock).moveRight();
  }

  @Test
  public void testMoveLeft() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Board mock = mock(Board.class);
    bot.makeMove(Direction.LEFT, mock);
    verify(mock).moveLeft();
  }

  @Test
  public void testRandom() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    for( int i = 0; i< 100; i++) {
      Direction random = bot.getRandom();
      assertThat(random, isOneOf(Direction.DOWN, Direction.UP, Direction.LEFT, Direction.RIGHT));
    }
  }

  @Test
  public void testGetScore() {
    MonteCarloGameTree bot = new MonteCarloGameTree(1);
    Board mock = mock(Board.class);
    when(mock.getGameState()).thenReturn(State.running)
      .thenReturn(State.won);
    when(mock.getScore()).thenReturn(100l);
    when(mock.copyBoard()).thenReturn(mock);

    long score = bot.getScore(mock, Direction.DOWN);
    assertThat(score, is(100l));

  }
}
