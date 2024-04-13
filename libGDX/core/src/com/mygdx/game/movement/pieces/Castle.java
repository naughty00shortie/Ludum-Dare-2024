package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.utils.CellNavigationUtils;

import java.util.HashSet;
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
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    Set<CoOrdinatePair> possibleMoves = new HashSet<>();
    //todo perhaps add direction abstractions
    //todo add all co-ordinates to the right until we hit an obstacle
    //x +
    Cell origin = board.getCell(new CoOrdinatePair(xOrigin, yOrigin)).orElseThrow(() -> new RuntimeException("X or Y origin out of bounds"));
    int boardSize = board.getCells().length;
    for(int i = xOrigin; i < boardSize; i++) {

    }
    Cell nextRight = CellNavigationUtils.moveOneCellRight(origin);
//    CoOrdinatePair o = new CoOrdinatePair(xOrigin, yOrigin);
    if (!nextRight.isOccupied()) { //todo out of bounds check
      possibleMoves.add(new CoOrdinatePair(nextRight.getX(), nextRight.getY()));
    }
    return possibleMoves;
  }
}
