package fi.hy.demo;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MonteCarloTest {

    @Test
    public void shouldReturnMoveUp() {
        MonteCarlo monteCarlo = new MonteCarlo(10);
        Bot.direction direction = monteCarlo.getDirection(4, 3, 2, 1);
        assertThat(direction, is(equalTo(Bot.direction.UP)));
    }

    @Test
    public void shouldReturnMoveDown() {
        MonteCarlo monteCarlo = new MonteCarlo(10);
        Bot.direction direction = monteCarlo.getDirection(4, 5, 2, 1);
        assertThat(direction, is(equalTo(Bot.direction.DOWN)));
    }

    @Test
    public void shouldReturnMoveRight() {
        MonteCarlo monteCarlo = new MonteCarlo(10);
        Bot.direction direction = monteCarlo.getDirection(4, 5, 6, 1);
        assertThat(direction, is(equalTo(Bot.direction.RIGHT)));
    }

    @Test
    public void shouldReturnMoveLeft() {
        MonteCarlo monteCarlo = new MonteCarlo(10);
        Bot.direction direction = monteCarlo.getDirection(4, 5, 6, 8);
        assertThat(direction, is(equalTo(Bot.direction.LEFT)));
    }
}
