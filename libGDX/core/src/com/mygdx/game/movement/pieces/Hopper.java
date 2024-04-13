package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Set;

public class Hopper implements Piece {

  private final boolean isPlayerPiece;

  public Hopper(boolean isPlayerPiece) {
    this.isPlayerPiece = isPlayerPiece;
  }

  public Hopper() {
    this(true);
  }

  @Override
  public boolean isPlayerPiece() {
    return isPlayerPiece;
  }

  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(origin.down());

    moveSet.add(origin.left());
    moveSet.add(origin.left().left());

    moveSet.add(origin.right());
    moveSet.add(origin.right().right());

    moveSet.add(origin.up());
    moveSet.add(origin.up().up());
    moveSet.add(origin.up().up().up());
    return moveSet;
  }
}
