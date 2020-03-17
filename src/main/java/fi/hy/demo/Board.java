package fi.hy.demo;

import java.util.Random;

public class Board {

    enum State {
        start, won, running, over
    }

    private Tile[][] tiles;
    static int highest;
    static int score;
    private State gamestate = State.start;
    private boolean checkingAvailableMoves;
    private int side = 4;
    private Random rand = new Random();
    final static int target = 2048;

    private boolean move(int countDownFrom, int yIncr, int xIncr) {
        boolean moved = false;

        for (int i = 0; i < side * side; i++) {
            int j = Math.abs(countDownFrom - i);

            int r = j / side;
            int c = j % side;

            if (tiles[r][c] == null)
                continue;

            int nextR = r + yIncr;
            int nextC = c + xIncr;

            while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {

                Tile next = tiles[nextR][nextC];
                Tile curr = tiles[r][c];

                if (next == null) {

                    if (checkingAvailableMoves)
                        return true;

                    tiles[nextR][nextC] = curr;
                    tiles[r][c] = null;
                    r = nextR;
                    c = nextC;
                    nextR += yIncr;
                    nextC += xIncr;
                    moved = true;

                } else if (next.canMergeWith(curr)) {

                    if (checkingAvailableMoves)
                        return true;

                    int value = next.mergeWith(curr);
                    if (value > highest)
                        highest = value;
                    score += value;
                    tiles[r][c] = null;
                    moved = true;
                    break;
                } else
                    break;
            }
        }

        if (moved) {
            if (highest < target) {
                clearMerged();
                addRandomTile();
                if (!movesAvailable()) {
                    gamestate = State.over;
                }
            } else if (highest == target)
                gamestate = State.won;
        }

        return moved;
    }


    private boolean movesAvailable() {
        checkingAvailableMoves = true;
        boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
        checkingAvailableMoves = false;
        return hasMoves;
    }

    private void addRandomTile() {
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos = (pos + 1) % (side * side);
            row = pos / side;
            col = pos % side;
        } while (tiles[row][col] != null);

        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = new Tile(val);
    }

    private void clearMerged() {
        for (Tile[] row : tiles)
            for (Tile tile : row)
                if (tile != null)
                    tile.setMerged(false);
    }

    public boolean moveUp() {
        return move(0, -1, 0);
    }

    public boolean moveDown() {
        return move(side * side - 1, 1, 0);
    }

    public boolean moveLeft() {
        return move(0, 0, -1);
    }

    public boolean moveRight() {
        return move(side * side - 1, 0, 1);
    }

    public void restartGame() {
        if (gamestate != State.running) {
            System.out.println("start");
            score = 0;
            highest = 0;
            gamestate = State.running;
            tiles = new Tile[side][side];
            addRandomTile();
            addRandomTile();
        }
    }

    public State getGameState() {
        return this.gamestate;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getScore() {
        return score;
    }

    public int getHighest() {
        return highest;
    }


}
