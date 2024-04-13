package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.utils.CellNavigationUtils;

import java.util.*;

public class Knight implements Piece {
  @Override
  public boolean isPlayerPiece() {
    return false;
  }

  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    Set<CoOrdinatePair> possibleMoves = new HashSet<>();
    List<CoOrdinatePair> knightMoveset = new ArrayList<>();
    CoOrdinatePair upLeft = new CoOrdinatePair(xOrigin - 1, yOrigin + 2);
    CoOrdinatePair downLeft = new CoOrdinatePair(xOrigin - 1, yOrigin - 2);
    CoOrdinatePair upRight = new CoOrdinatePair(xOrigin + 1, yOrigin + 2);
    CoOrdinatePair downRight = new CoOrdinatePair(xOrigin + 1, yOrigin - 2);
    knightMoveset.add(upLeft);
    knightMoveset.add(downLeft);
    knightMoveset.add(upRight);
    knightMoveset.add(downRight);
    for (CoOrdinatePair coOrdinatePair : knightMoveset) {
      Optional<Cell> cell = board.getCell(coOrdinatePair);
      if (cell.isPresent() && cell.get().getPiece().get().isPlayerPiece()) {
        possibleMoves.add(coOrdinatePair);
      }
    }
    return null;
  }
}
