package fi.hy.demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameEngine2048 extends JPanel {


    private final Board board;
    private Bot bot;
    private int side;
    private Color gridColor = new Color(0xBBADA0);
    private Color emptyColor = new Color(0xCDC1B4);
    private Color startColor = new Color(0xFFEBCD);
    final Color[] colorTable = {
            new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
            new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
            new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
            new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)};

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
            public void mousePressed(MouseEvent e) {
                board.restartGame();
                repaint();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        System.out.println("start bot");
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
                }
                repaint();
            }
        });
    }

    private void startBot() {
        Thread t = new Thread(new Runnable() {
            private Robot robObject;

            @Override
            public void run() {
                try {
                    this.robObject = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                while (board.getGameState() == Board.State.running) {
                    move();
                }
                System.out.println("endded" + board.getGameState());
            }

            private void move() {
                try {
                    System.out.println("sleep" );
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("get direction");
                Bot.direction direction = bot.decideMove(board);
                System.out.println("decide" + direction);
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
                    case RIGHT:
                        robObject.keyPress(KeyEvent.VK_RIGHT);
                        break;
                }
            }
        });
        t.start();


    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g);
    }


    void drawGrid(Graphics2D g) {
        g.setColor(gridColor);
        g.fillRoundRect(200, 100, 499, 499, 15, 15);

        if (board.getGameState() == Board.State.running) {

            for (int r = 0; r < side; r++) {
                for (int c = 0; c < side; c++) {
                    if (board.getTiles()[r][c] == null) {
                        g.setColor(emptyColor);
                        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
                    } else {
                        drawTile(g, r, c);
                    }
                }
            }
        } else {
            g.setColor(startColor);
            g.fillRoundRect(215, 115, 469, 469, 7, 7);

            g.setColor(gridColor.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 128));
            g.drawString("2048", 310, 270);

            g.setFont(new Font("SansSerif", Font.BOLD, 20));

            if (board.getGameState() == Board.State.won) {
                g.drawString("you made it! Score: " + board.getScore() + ", highest tile:  " + board.getHighest(), 250, 350);

            } else if (board.getGameState() == Board.State.over)
                g.drawString("game over! Score: " + board.getScore() + ", highest tile: " + board.getHighest(), 250, 350);

            g.setColor(gridColor);
            g.drawString("click to start a new game", 330, 470);
            g.drawString("press enter to start bot", 340, 500);
            g.drawString("(use arrow keys to move tiles)", 310, 530);
        }
    }

    void drawTile(Graphics2D g, int r, int c) {
        int value = board.getTiles()[r][c].getValue();

        g.setColor(colorTable[(int) (Math.log(value) / Math.log(2)) + 1]);
        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
        String s = String.valueOf(value);

        g.setColor(value < 128 ? colorTable[0] : colorTable[1]);

        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();

        int x = 215 + c * 121 + (106 - fm.stringWidth(s)) / 2;
        int y = 115 + r * 121 + (asc + (106 - (asc + dec)) / 2);

        g.drawString(s, x, y);
    }

    public void startGame(Bot bot) {
        this.bot = bot;
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("2048");
            f.setResizable(true);
            f.add(new GameEngine2048(bot), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}

