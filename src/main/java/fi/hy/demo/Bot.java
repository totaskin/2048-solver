package fi.hy.demo;

public interface Bot {
    enum direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }
    direction decideMove(Board tiles);
}
