package fi.hy.demo;

import java.util.Random;

public class Board {

    enum State {
        start, won, running, over
    }

    public Board() {
        this.score = 0;
        this.highest = 0;
    }

    public Board(Tile[][] clone, int score, int highest, State gamestate) {
        this.tiles = clone;
        this.score = score;
        this.highest = highest;
        this.gamestate = gamestate;
    }

    private Tile[][] tiles;
    private int highest;
    private int score;
    private State gamestate = State.start;
    private boolean checkingAvailableMoves;
    private static int side = 4;
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

                Tile next = this.tiles[nextR][nextC];
                Tile curr = this.tiles[r][c];

                if (next == null) {

                    if (this.checkingAvailableMoves)
                        return true;

                    this.tiles[nextR][nextC] = curr;
                    this.tiles[r][c] = null;
                    r = nextR;
                    c = nextC;
                    nextR += yIncr;
                    nextC += xIncr;
                    moved = true;

                } else if (next.canMergeWith(curr)) {

                    if (this.checkingAvailableMoves)
                        return true;

                    int value = next.mergeWith(curr);
                    if (value > this.highest)
                        this.highest = value;
                    this.score += value;
                    this.tiles[r][c] = null;
                    moved = true;
                    break;
                } else
                    break;
            }
        }

        if (moved) {
            if (this.highest < this.target) {
                clearMerged();
                addRandomTile();
                if (!movesAvailable()) {
                    this.gamestate = State.over;
                }
            } else if (highest == target)
                this.gamestate = State.won;
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

    public Board copyBoard() {
        Tile[][] tiles = new Tile[side][side];
        for (int x = 0; x < side; x++) {
            for (int y = 0; y < side; y++) {
                Tile tile = this.tiles[x][y];
                if (tile != null) {
                    tiles[x][y] = tile.Clone();
                }
            }
        }


        return new Board(tiles, this.score, this.highest, this.gamestate);
    }


}
