package com.mygdx.game.movement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Pawn implements Piece{

  @Override
  public boolean isPlayerPiece() {
    return false;
  }

  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin) {
    //basic movement moves 1 forward "up"
    Set<CoOrdinatePair> possibleMoves = new HashSet<>();
    possibleMoves.add(new CoOrdinatePair(xOrigin, yOrigin + 1));// todo assumed up is positive direction
    //todo

    return Collections.emptySet();
  }
}
