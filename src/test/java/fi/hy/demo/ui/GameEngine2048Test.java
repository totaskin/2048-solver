package fi.hy.demo.ui;

import fi.hy.demo.bot.Bot;
import fi.hy.demo.bot.BotFactory;
import fi.hy.demo.bot.BotNotFoundException;
import fi.hy.demo.engine.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameEngine2048Test {

  @Mock
  private GameEngine2048 gameEngine2048;

  @Mock
  private Graphics2D graphics2D;


  @BeforeAll
  public void setUp() throws  BotNotFoundException {
    int width = 1000;
    int height = 1000;

    Bot randomBot = BotFactory.getBot("random", 10);
    gameEngine2048 = spy(new GameEngine2048(randomBot));
    doNothing().when(gameEngine2048).repaint();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    graphics2D = image.createGraphics();

  }

  @Test
  public void testCreatingGameEngine() {
    GameEngine2048 gameEngine2048 = new GameEngine2048(null);
    assertNotNull(gameEngine2048);
  }

  @Test
  public void testStartBot() throws BotNotFoundException, InvocationTargetException, InterruptedException {
    Bot randomBot = BotFactory.getBot("random", 10);
    gameEngine2048.startGame(randomBot);
    gameEngine2048.pressKey(KeyEvent.VK_ENTER);
    gameEngine2048.pressKey(KeyEvent.VK_UP);
    gameEngine2048.pressKey(KeyEvent.VK_DOWN);
    gameEngine2048.pressKey(KeyEvent.VK_LEFT);
    gameEngine2048.pressKey(KeyEvent.VK_RIGHT);
    gameEngine2048.pressKey(KeyEvent.VK_UNDEFINED);
    assertNotNull(gameEngine2048);
  }

  @Test
  public void testDrawGrid() throws BotNotFoundException {
    Bot randomBot = BotFactory.getBot("random", 10);
    GameEngine2048 gameEngine2048 = new GameEngine2048(randomBot);
    gameEngine2048.startGame(randomBot);
    gameEngine2048.drawGrid(graphics2D);
  }

  @Test
  public void testDrawing() throws BotNotFoundException {
    Bot randomBot = BotFactory.getBot("random", 10);
    GameEngine2048 gameEngine2048 = new GameEngine2048(randomBot);
    gameEngine2048.startGame(randomBot);
    gameEngine2048.restartGame();
    gameEngine2048.drawGrid(graphics2D);
  }

  @Test
  public void testWon() throws BotNotFoundException {
    Bot randomBot = BotFactory.getBot("random", 10);
    GameEngine2048 gameEngine2048 = new GameEngine2048(randomBot);
    gameEngine2048.startGame(randomBot);
    gameEngine2048.restartGame();
    gameEngine2048.setGameState(State.won);
    gameEngine2048.drawGrid(graphics2D);
  }

  @Test
  public void testOver() throws BotNotFoundException {
    Bot randomBot = BotFactory.getBot("random", 10);
    GameEngine2048 gameEngine2048 = new GameEngine2048(randomBot);
    gameEngine2048.startGame(randomBot);
    gameEngine2048.restartGame();
    gameEngine2048.setGameState(State.over);
    gameEngine2048.drawGrid(graphics2D);
  }


}
