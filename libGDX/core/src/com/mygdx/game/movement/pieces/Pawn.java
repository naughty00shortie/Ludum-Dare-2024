package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.utils.CellNavigationUtils;

import java.util.HashSet;
import java.util.Set;

public class Pawn implements Piece {

  private final boolean playerPiece;

  public Pawn(boolean isPlayerPiece) {
    this.playerPiece = isPlayerPiece;
  }

  public Pawn() {
    this(true);
  }

  @Override
  public boolean isPlayerPiece() {
    return playerPiece;
  }

  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    CoOrdinatePair origin = new CoOrdinatePair(xOrigin, yOrigin);
    Set<CoOrdinatePair> possibleMoves = new HashSet<>();
    //todo if 1st move can move 2 spaces forward
    //basic movement moves 1 forward "up"
    CoOrdinatePair oneForward = new CoOrdinatePair(xOrigin, yOrigin + 1);
    if (board.getCell(oneForward).isPresent()) {
      possibleMoves.add(oneForward);// todo assumed up is positive direction
    }
    //todo check if there are valid capture moves
    //get diagonal cells

    Cell cellLeftDiagonal = board.getCell(new CoOrdinatePair(xOrigin - 1, yOrigin + 1)).orElse(new Cell());
    Cell cellRightDiagonal = board.getCell(new CoOrdinatePair(xOrigin + 1, yOrigin + 1)).orElse(new Cell());
    //check if there's a piece
    if (cellLeftDiagonal.isOccupied()) {
      possibleMoves.add(CellNavigationUtils.moveOneCellDiagonalTopLeft(origin.getX(), origin.getY()));
    }
    if (cellRightDiagonal.isOccupied()) {
      possibleMoves.add(CellNavigationUtils.moveOneCellDiagonalTopRight(origin.getX(), origin.getY()));
    }
    return possibleMoves;
  }
}