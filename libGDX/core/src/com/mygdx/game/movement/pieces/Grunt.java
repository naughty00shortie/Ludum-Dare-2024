package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Set;

/**
 * Basic Summon of the game. Marches and captures in one direction.
 */
public class Grunt implements Piece {

  private final boolean isPlayerPiece;

  public Grunt(boolean isPlayerPiece) {
    this.isPlayerPiece = isPlayerPiece;
  }

  public Grunt() {
    this(true);
  }

  @Override
  public boolean isPlayerPiece() {
    return isPlayerPiece;
  }

  /**
   * Player Pieces move up the Board, increasing in Y, whereas
   * Enemy Pieces move down the Board, decreasing in Y.
   */
  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    CoOrdinatePair step = (isPlayerPiece()) ? origin.up() : origin.down();
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(step);
    moveSet.add(step.left());
    moveSet.add(step.right());
    return moveSet;
  }

}
