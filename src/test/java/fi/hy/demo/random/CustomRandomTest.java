package fi.hy.demo.random;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;

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

}
