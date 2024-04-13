package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Set;

public class Knight implements Piece{
  @Override
  public boolean isPlayerPiece() {
    return false;
  }

  @Override
  public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
    //L
    CoOrdinatePair origin = new CoOrdinatePair(xOrigin, yOrigin);
    applyL(origin);

    return null;
  }

  private CoOrdinatePair applyL(CoOrdinatePair origin) {
    throw new NotImplementedException();
  }
}
