package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.Set;

public class Castle implements Piece {

  @Override
  public boolean isPlayerPiece() {
    return false;
  }

  /**
   * can move any amount of spaces unless there is an obstacle (out of bounds or another piece)
   *
   * @param xOrigin
   * @param yOrigin
   * @param
   * @return
   */
  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Cell[][] board) {
    //todo perhaps add direction abstractions
    //todo add all co-ordinates to the right until we hit an obstacle
    //x +
    Cell origin = board[xOrigin][yOrigin];
    Cell nextCell = origin;

    return null;
  }
}
