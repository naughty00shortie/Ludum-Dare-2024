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
  public Set<CoOrdinatePair> potentialMoveSet(int xOrigin, int yOrigin) {
    int yStep = (isPlayerPiece()) ? yOrigin + 1 : yOrigin - 1;
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    CoOrdinatePair forward = new CoOrdinatePair(xOrigin, yStep);
    CoOrdinatePair left = new CoOrdinatePair(xOrigin + 1, yStep);
    CoOrdinatePair right = new CoOrdinatePair(xOrigin - 1, yStep);
    moveSet.add(forward);
    moveSet.add(left);
    moveSet.add(right);
    return moveSet;
  }

}
