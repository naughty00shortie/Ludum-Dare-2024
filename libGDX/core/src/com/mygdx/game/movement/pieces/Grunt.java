package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    return potentialPositions(xOrigin, yOrigin).stream()
            .map(board::getCell)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(this::canOccupyCell)
            .map(Cell::getCoOrdinatePair)
            .collect(Collectors.toSet());
  }

  /**
   * @return all potential positions the Grunt could move to.
   */
  private Set<CoOrdinatePair> potentialPositions(int xOrigin, int yOrigin) {
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    CoOrdinatePair step = (isPlayerPiece()) ? origin.up() : origin.down();
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    CoOrdinatePair forward = new CoOrdinatePair(xOrigin, yOrigin + 1);
    CoOrdinatePair left = new CoOrdinatePair(xOrigin + 1, yOrigin + 1);
    CoOrdinatePair right = new CoOrdinatePair(xOrigin - 1, yOrigin - 1);
    moveSet.add(forward);
    moveSet.add(left);
    moveSet.add(right);
    moveSet.add(step);
    moveSet.add(step.left());
    moveSet.add(step.right());
    return moveSet;
  }

}
