package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Set;

public class Sentinel implements Piece {

  private final boolean isPlayerPiece;

  public Sentinel(boolean isPlayerPiece) {
    this.isPlayerPiece = isPlayerPiece;
  }

  public Sentinel() {
    this(true);
  }

  @Override
  public boolean isPlayerPiece() {
    return isPlayerPiece;
  }

  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(origin.up());
    moveSet.add(origin.up().left());
    moveSet.add(origin.up().right());
    moveSet.add(origin.left());
    moveSet.add(origin.right());
    moveSet.add(origin.down());
    moveSet.add(origin.down().left());
    moveSet.add(origin.down().right());
    return moveSet;
  }

}
