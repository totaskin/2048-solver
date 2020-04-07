package fi.hy.demo.ui;

import fi.hy.demo.bot.Bot;
import fi.hy.demo.bot.Direction;
import fi.hy.demo.engine.Board;
import fi.hy.demo.engine.State;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameEngine2048 extends JPanel {


  final Color[] colorTable = {
    new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
    new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
    new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
    new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710),
    new Color(0x3e5e56), new Color(0x7c3931), new Color(0x501710),
    new Color(0x8e5e56), new Color(0xc3931), new Color(0x501710),};
  private final Board board;
  private final int side;
  private final Color gridColor = new Color(0xBBADA0);
  private final Color emptyColor = new Color(0xCDC1B4);
  private final Color startColor = new Color(0xFFEBCD);
  private Bot bot;
  private Thread thread;

  /**
   * Stary UI with desired bot.
   *
   * @param bot Bot to run game with.
   */
  public GameEngine2048(Bot bot) {
    this.bot = bot;
    side = 4;
    this.board = new Board();
    setPreferredSize(new Dimension(900, 700));
    setBackground(new Color(0xFAF8EF));
    setFont(new Font("SansSerif", Font.BOLD, 48));
    setFocusable(true);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent mouseEvent) {
        board.restartGame();
        repaint();
      }
    });
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
          case KeyEvent.VK_ENTER:
            startBot();
            break;
          case KeyEvent.VK_UP:
            board.moveUp();
            break;
          case KeyEvent.VK_DOWN:
            board.moveDown();
            break;
          case KeyEvent.VK_LEFT:
            board.moveLeft();
            break;
          case KeyEvent.VK_RIGHT:
            board.moveRight();
            break;
          default:
            System.out.println("no-op");
            break;
        }
        repaint();
      }
    });
  }

  private void startBot() {
    if (this.thread != null) {
      thread.interrupt();
    }
    this.thread = new Thread(new Runnable() {
      private Robot robObject;

      @Override
      public void run() {
        try {
          this.robObject = new Robot();
        } catch (AWTException exeption) {
          exeption.printStackTrace();
        }
        while (board.getGameState() == State.running) {
          move();
        }
        System.out.println("endded" + board.getGameState());
      }

      private void move() {
        try {
          System.out.println("sleep");
          Thread.sleep(0);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
        Direction direction = bot.decideMove(board);
        switch (direction) {
          case UP:
            robObject.keyPress(KeyEvent.VK_UP);
            break;
          case DOWN:
            robObject.keyPress(KeyEvent.VK_DOWN);
            break;
          case LEFT:
            robObject.keyPress(KeyEvent.VK_LEFT);
            break;
          default:
            robObject.keyPress(KeyEvent.VK_RIGHT);
            break;
        }
      }
    });
    thread.start();
  }

  @Override
  public void paintComponent(Graphics gg) {
    super.paintComponent(gg);
    Graphics2D graphics2D = (Graphics2D) gg;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    drawGrid(graphics2D);
  }


  void drawGrid(Graphics2D graphics2Dg) {
    graphics2Dg.setColor(gridColor);
    graphics2Dg.fillRoundRect(200, 100, 499, 499, 15, 15);

    if (board.getGameState() == State.running) {

      for (int r = 0; r < side; r++) {
        for (int c = 0; c < side; c++) {
          if (board.getTiles()[r][c] == null) {
            graphics2Dg.setColor(emptyColor);
            graphics2Dg.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
          } else {
            drawTile(graphics2Dg, r, c);
          }
        }
      }
    } else {
      graphics2Dg.setColor(startColor);
      graphics2Dg.fillRoundRect(215, 115, 469, 469, 7, 7);

      graphics2Dg.setColor(gridColor.darker());
      graphics2Dg.setFont(new Font("SansSerif", Font.BOLD, 128));
      graphics2Dg.drawString("2048", 310, 270);

      graphics2Dg.setFont(new Font("SansSerif", Font.BOLD, 20));

      if (board.getGameState() == State.won) {
        StringBuilder builder = new StringBuilder();
        builder.append("you made it! Score: ");
        builder.append(board.getScore());
        builder.append(", highest tile:  ");
        builder.append(board.getHighest());
        graphics2Dg.drawString(builder.toString(), 250, 350);
      } else if (board.getGameState() == State.over) {
        StringBuilder builder = new StringBuilder();
        builder.append("game over! Score: ");
        builder.append(board.getScore());
        builder.append(", highest tile:  ");
        builder.append(board.getHighest());
        graphics2Dg.drawString(builder.toString(), 250, 350);
      }

      graphics2Dg.setColor(gridColor);
      graphics2Dg.drawString("click to start a new game", 330, 470);
      graphics2Dg.drawString("press enter to start bot", 340, 500);
      graphics2Dg.drawString("(use arrow keys to move tiles)", 310, 530);
    }
  }

  void drawTile(Graphics2D graphics2D, int row, int column) {
    int value = board.getTiles()[row][column].getValue();

    int color = (int) ((Math.log(value) / Math.log(2)) + 1) % Integer.MAX_VALUE;
    graphics2D.setColor(colorTable[color]);
    graphics2D.fillRoundRect(215 + column * 121, 115 + row * 121, 106, 106, 7, 7);
    String string = String.valueOf(value);

    graphics2D.setColor(value < 128 ? colorTable[0] : colorTable[1]);

    FontMetrics fm = graphics2D.getFontMetrics();
    int asc = fm.getAscent();
    int dec = fm.getDescent();

    int coordinateX = 215 + column * 121 + (106 - fm.stringWidth(string)) / 2;
    int coordinateY = 115 + row * 121 + (asc + (106 - (asc + dec)) / 2);

    graphics2D.drawString(string, coordinateX, coordinateY);
  }

  /**
   * Method to start game.
   *
   * @param bot Bot to be used for solving the game.
   */
  public void startGame(Bot bot) {
    this.bot = bot;
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("2048");
      frame.setResizable(true);
      frame.add(new GameEngine2048(bot), BorderLayout.CENTER);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}

