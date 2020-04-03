package fi.hy.demo;

import fi.hy.demo.bot.BotNotFoundException;
import fi.hy.demo.bot.GameScore;
import fi.hy.demo.libs.Plot;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.io.IOException;

class Game2048SlowTest {

  @Test
  void testMonteCarlo() throws Exception, BotNotFoundException {
    Game2048 game2048 = new Game2048();
    int runs = 8;
    int repets = 8;
    int scores[] = new int[runs];
    int highest[] = new int[runs];
    int times[] = new int[runs];
    for (int i = 0; i < runs; i++) {
      int score = 0;
      int high = 0;
      int time = 0;
      for (int j = 0; j < repets; j++) {
        long start = System.currentTimeMillis();
        GameScore gameScore = game2048.runBot("monte-carlo", (i + 1) * 10);
        score += gameScore.getScore();
        high += gameScore.getHighest();
        time += gameScore.getTime();
        long end = System.currentTimeMillis();

        long timerunning = end - start;
        System.out.println("round " + j + ", " + timerunning);
      }
      scores[i] = score / repets;
      highest[i] = high / repets;
      times[i] = time / repets;
      System.out.println("run " + i);
    }
    printScores(scores, highest, times, runs, "monte-carlo");
  }

  @Test
  void testRandomBot() throws Exception, BotNotFoundException {
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

        GameScore gameScore = game2048.runBot("random", (i + 1) * 10);
        score += gameScore.getScore();
        high += gameScore.getHighest();
        time += gameScore.getTime();

      }
      scores[i] = score / repets;
      highest[i] = high / repets;
      times[i] = time / repets;
      System.out.println("run " + i);
    }
    printScores(scores, highest, times, runs, "random-bot");
  }

  @Test
  void testWithRuns() throws BotNotFoundException, Exception {
    int score = 0;
    int high = 0;
    int time = 0;
    int repets = 10;
    int runs = 600;
    for (int i = 0; i < repets; i++) {
      Game2048 game2048 = new Game2048();
      GameScore gameScore = game2048.runBot("MCPP", runs);
      score += gameScore.getScore();
      high += gameScore.getHighest();
      time += gameScore.getTime();
      System.out.println(gameScore.getScore() + ", " + gameScore.getHighest() + ", " + gameScore.getTime());
    }
    System.out.println("averages" + score / repets + ", " + high / repets + ", " + time / repets);
  }

  private void printScores(int[] scores, int[] highest, int[] times, int runs, String fileName) throws IOException {


    Plot.Data timeData = Plot.data();
    Plot.Data highestData = Plot.data();
    for (int i = 0; i < runs; i++) {
      int iterations = (i + 1) * 10;
      timeData.xy(iterations, times[i]);
      highestData.xy(iterations, highest[i]);

    }

    Plot plot = Plot.plot(Plot.plotOpts()
      .title("Ajan ja keskimäräinen tulis ajettujen kierrosten lisääntyessä")
      .legend(Plot.LegendFormat.BOTTOM))
      .series("Aika", timeData, null)
      .series("Keskimääräinen tulos", highestData, Plot.seriesOpts().
        color(Color.RED));
    plot.save(fileName, "png");


  }


}
