package fi.hy.demo;

public class MonteCarlo implements Bot{


    @Override
    public direction decideMove(Tile[][] tiles) {
        double random = Math.random();
        if(random < 0.25)
            return direction.UP;
        else if(random < 0.5)
            return direction.RIGHT;
        else if(random < 0.75)
            return direction.DOWN;
        else
            return direction.LEFT;
    }
}
