package com.mygdx.game.round;

import com.mygdx.game.players.Player;

@FunctionalInterface
public interface Move {
  public void execute();
}

