package fi.hy.demo;

import fi.hy.demo.bot.BotNotFoundException;
import fi.hy.demo.bot.GameScore;
import fi.hy.demo.plot.Plot;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;

class Game2048SlowTest {

  @Test
  void testBot() throws Exception, BotNotFoundException {
    Game2048 game2048 = new Game2048();
    int runs = 22;
    int repets = 10;
    int scores[] = new int[runs];
    int highest[] = new int[runs];
    int times[] = new int[runs];
    for (int i = 0; i < runs; i++) {
      int score = 0;
      int high = 0;
      int time = 0;
      for (int j = 0; j < repets; j++) {
        GameScore gameScore = game2048.runBot("monte carlo", (i + 1) * 10);
        score += gameScore.getScore();
        high += gameScore.getHighest();
        time += gameScore.getTime();
        System.out.println("round " + j);
      }
      scores[i] = score / repets;
      highest[i] = high / repets;
      times[i] = time / repets;
      System.out.println("run " + i);
    }
    printScores(scores, highest, times, runs);
  }

  private void printScores(int[] scores, int[] highest, int[] times, int runs) throws IOException {


    Plot.Data timeData = Plot.data();
    Plot.Data highestData = Plot.data();
    for (int i = 0; i < runs; i++) {
      int iterations = (i + 1) * 10;
      timeData.xy(iterations, times[i]);
      highestData.xy(iterations, highest[i]);

    }

    Plot plot = Plot.plot(null)
      .series("time", timeData, null)
      .series("highest", highestData, Plot.seriesOpts().
        color(Color.RED));
    plot.save("time", "png");

  }


}
