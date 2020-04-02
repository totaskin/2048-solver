package fi.hy.demo.bot;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
    assertThat(direction, is(equalTo(Direction.RIGHT)));
  }

  @Test
  public void shouldReturnMoveLeft() {
    MonteCarlo monteCarlo = new MonteCarlo(10);
    Direction direction = monteCarlo.getDirection(4, 5, 6, 8);
    assertThat(direction, is(equalTo(Direction.LEFT)));
  }
}
