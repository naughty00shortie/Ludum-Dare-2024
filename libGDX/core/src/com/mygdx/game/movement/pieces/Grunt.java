package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    CoOrdinatePair forward = new CoOrdinatePair(xOrigin, yOrigin + 1);
    CoOrdinatePair left = new CoOrdinatePair(xOrigin + 1, yOrigin + 1);
    CoOrdinatePair right = new CoOrdinatePair(xOrigin - 1, yOrigin - 1);
    moveSet.add(forward);
    moveSet.add(left);
    moveSet.add(right);
    return moveSet;
  }

}
