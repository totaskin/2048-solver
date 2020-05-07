package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;
import fi.hy.demo.random.CustomRandom;
import org.junit.jupiter.api.Test;

import static fi.hy.demo.bot.Direction.RIGHT;
import static fi.hy.demo.bot.Direction.UP;
import static fi.hy.demo.engine.State.running;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MonteCarloTest {

  @Test
  public void shouldReturnMoveUp() {
    MonteCarlo monteCarlo = new MonteCarlo(10);
    Direction direction = monteCarlo.getDirection(4, 3, 2, 1);
    assertThat(direction, is(equalTo(Direction.UP)));
  }

  @Test
  public void shouldReturnMoveDown() {
    MonteCarlo monteCarlo = new MonteCarlo(10);
    Direction direction = monteCarlo.getDirection(4, 5, 2, 1);
    assertThat(direction, is(equalTo(Direction.DOWN)));
  }

  @Test
  public void shouldReturnMoveRight() {
    MonteCarlo monteCarlo = new MonteCarlo(10);
    Direction direction = monteCarlo.getDirection(4, 5, 6, 1);
    assertThat(direction, is(equalTo(RIGHT)));
  }

  @Test
  public void shouldReturnMoveLeft() {
    MonteCarlo monteCarlo = new MonteCarlo(10);
    Direction direction = monteCarlo.getDirection(4, 5, 6, 8);
    assertThat(direction, is(equalTo(Direction.LEFT)));
  }

  @Test
  public void shouldMakeCorrectMove() {
    Board boardMock = mock(Board.class);
    MonteCarlo bot = new MonteCarlo(1);
    bot.makeMove(0, boardMock);
    bot.makeMove(1, boardMock);
    bot.makeMove(2, boardMock);
    bot.makeMove(3, boardMock);

    verify(boardMock, times(1)).moveUp();
    verify(boardMock, times(1)).moveLeft();
    verify(boardMock, times(1)).moveRight();
    verify(boardMock, times(1)).moveDown();
  }

  @Test
  public void shouldDecideMoveUp() {
    Board boardMock = mock(Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    MonteCarlo bot = new MonteCarlo(1, randomMock);
    when(boardMock.copyBoard()).thenReturn(boardMock);
    when(boardMock.getGameState())
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(fi.hy.demo.engine.State.over);
    when(randomMock.nextInt(anyInt())).thenReturn(0)
      .thenReturn(1)
      .thenReturn(2)
      .thenReturn(3);

    when(boardMock.getScore()).thenReturn(10l)
      .thenReturn(20l)
      .thenReturn(30l)
      .thenReturn(40l);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, is(fi.hy.demo.bot.Direction.UP));
  }

  @Test
  public void shouldDecideMoveLeft() {
    Board boardMock = mock(Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    MonteCarlo bot = new MonteCarlo(4, randomMock);
    when(boardMock.copyBoard()).thenReturn(boardMock);
    when(boardMock.getGameState())
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(running)
      .thenReturn(fi.hy.demo.engine.State.over);
    when(randomMock.nextInt(anyInt())).thenReturn(1)
      .thenReturn(2)
      .thenReturn(3)
      .thenReturn(4);

    when(boardMock.getScore()).thenReturn(10l)
      .thenReturn(20l)
      .thenReturn(30l)
      .thenReturn(40l);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, is(Direction.LEFT));
  }


  @Test
  public void shouldDecideMoveRight() {
    Board boardMock = mock(Board.class);
    CustomRandom randomMock = mock(CustomRandom.class);
    MonteCarlo bot = new MonteCarlo(1, randomMock);
    when(boardMock.copyBoard()).thenReturn(boardMock);
    when(boardMock.getGameState())
      .thenReturn(running)
      .thenReturn(fi.hy.demo.engine.State.over);
    when(randomMock.nextInt(anyInt())).thenReturn(2);

    when(boardMock.getScore()).thenReturn(14l);

    Direction direction = bot.decideMove(boardMock);
    assertThat(direction, is(RIGHT));
  }


}

