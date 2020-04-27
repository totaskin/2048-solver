package fi.hy.demo.random;

import java.util.Random;

public class CustomRandom {

  private final long start;
  private final Random random;

  public CustomRandom() {
    this.start = System.nanoTime();
    this.random = new Random();
  }

  /**
   * Method to create naive random number.
   * Not suitable for testing because cannot reproduce random sequences.
   *
   * @param max maximum number to get as result
   * @return Random number between 0 and max
   */
  public int nextInt(int max) {
    long l = System.nanoTime();
    long l1 = this.start ^ l;
    return (int) (l1 % max);
  }
}
