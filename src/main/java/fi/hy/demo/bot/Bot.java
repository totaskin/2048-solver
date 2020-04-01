package fi.hy.demo.bot;

import fi.hy.demo.engine.Board;

public interface Bot {
  Direction decideMove(Board tiles);

}
