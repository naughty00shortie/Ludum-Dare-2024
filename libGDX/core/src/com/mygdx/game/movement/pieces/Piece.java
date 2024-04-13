package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.Set;

/**
 * Candace: Provide a concrete implementation for Piece.
 */
public interface Piece {
  /**
   * @return true if your piece. false if enemy piece
   */
  boolean isPlayerPiece();

  /**
   * @return Set of CoOrdinates this Piece can move to.
   * CoOrdinates do not necessarily exist within the bounds of the Board.
   * @see Board#getCell(CoOrdinatePair) most likely to be used in combination with
   * this method to retrieve all Cells the Piece can reach.
   * @see Cell#isOccupied() might be queried along with Cell#getPiece() -> Piece#isPlayerPiece
   * to determine if it is a capturable move, etc.
   */
  Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board);

  /**
   * @see Piece#moveSet(int, int, Cell[][]) delegates.
   */
  default Set<CoOrdinatePair> moveSet(CoOrdinatePair coOrdinatePair, Board board) {
    return moveSet(coOrdinatePair.getX(), coOrdinatePair.getY(), board);
  }
}
