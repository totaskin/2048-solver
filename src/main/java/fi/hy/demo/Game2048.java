package fi.hy.demo;

public class Game2048 {

    public static void main(String[] args) {

        Bot monteCarlo = new MonteCarlo(100);
        GameEngine2048 gameEngine2048 = new GameEngine2048(monteCarlo);
        gameEngine2048.startGame(monteCarlo);
    }
}
