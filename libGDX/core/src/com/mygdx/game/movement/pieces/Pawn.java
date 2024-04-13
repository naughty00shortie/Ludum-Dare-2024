package com.mygdx.game.movement;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Pawn implements Piece {

  @Override
  public boolean isPlayerPiece() {
    return false;
  }

  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Cell[][] board) {
    CoOrdinatePair origin = new CoOrdinatePair(xOrigin, yOrigin);
    Set<CoOrdinatePair> possibleMoves = new HashSet<>();
    //todo if 1st move can move 2 spaces forward
    //basic movement moves 1 forward "up"
    CoOrdinatePair oneForward = new CoOrdinatePair(xOrigin, yOrigin + 1);
    possibleMoves.add(oneForward);// todo assumed up is positive direction
    //todo check if there are valid capture moves
    //get diagonal cells
    Cell cellLeftDiagonal = board[xOrigin - 1][yOrigin + 1];
    Cell cellRightDiagonal = board[xOrigin + 1][yOrigin + 1];
    //check if theres a piece
    if (cellLeftDiagonal.isOccupied()) {
      possibleMoves.add(new CoOrdinatePair(xOrigin - 1, yOrigin + 1));
    }
    if (cellRightDiagonal.isOccupied()) {
      possibleMoves.add(new CoOrdinatePair(xOrigin + 1, yOrigin + 1));
    }
    return possibleMoves;
  }
}
