package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Candace: Provide a concrete implementation for Piece.
 */
public interface Piece {

  /**
   * @return true if your piece. false if enemy piece
   */
  boolean isPlayerPiece();

  /**
   * A Piece can only occupy empty Cells,
   * or Cells controlled by Pieces of the opposing faction.
   *
   * @param cell to validate movement to.
   * @return true if the Piece can occupy the Cell.
   */
  default boolean canOccupyCell(Cell cell) {
    Optional<Piece> piece = cell.getPiece();
    if (!piece.isPresent()) return true;
    return (piece.get().isPlayerPiece() != this.isPlayerPiece());
  }

  /**
   * @see Piece#moveSet(int, int, Board) delegates.
   */
  default Set<CoOrdinatePair> moveSet(CoOrdinatePair coOrdinatePair, Board board) {
    return moveSet(coOrdinatePair.getX(), coOrdinatePair.getY(), board);
  }

  /**
   * @return Set of CoOrdinates this Piece has valid moves on.
   */
  default Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    return potentialMoveSet(xOrigin, yOrigin).stream()
            .map(board::getCell)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(this::canOccupyCell)
            .map(Cell::getCoOrdinatePair)
            .collect(Collectors.toSet());
  }

  /**
   * @return Set of all possible CoOrdinates this Piece could move to. These CoOrdinates do not
   * have to be valid, and are meant to be filtered.
   * @see Piece#moveSet(int, int, Board)
   */
  Set<CoOrdinatePair> potentialMoveSet(int xOrigin, int yOrigin);

}
