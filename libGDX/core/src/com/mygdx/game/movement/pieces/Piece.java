package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.players.Player;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface Piece {



  /**
   * @return true if your piece. false if enemy piece
   */
  boolean isPlayerPiece(Player player);

  /**
   * A Piece can only occupy empty Cells,
   * or Cells controlled by Pieces of the opposing faction.
   *
   * @param cell to validate movement to.
   * @return true if the Piece can occupy the Cell.
   */
  default boolean canOccupyCell(Cell cell) {
    Optional<Piece> piece = cell.getPiece();
    return piece.map(value -> (value.isPlayerPiece(getOwner()) != this.isPlayerPiece(getOwner()))).orElse(true);
  }

  /**
   * @return Set of CoOrdinates this Piece has valid moves on.
   */
  default Set<CoOrdinatePair> moveSet(CoOrdinatePair coOrdinatePair, Board board) {
    return potentialMoveSet(coOrdinatePair).stream()
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
   * @see Piece#moveSet(CoOrdinatePair, Board)
   */
  Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin);

  default boolean validMove(CoOrdinatePair from, CoOrdinatePair to) {
    return moveSet(from, getBoard()).contains(to);
  }

  int value();

  Player getOwner();

  Board getBoard();

}
