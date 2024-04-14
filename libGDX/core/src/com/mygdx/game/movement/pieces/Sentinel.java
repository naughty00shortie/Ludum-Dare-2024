package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.players.Player;

import java.util.HashSet;
import java.util.Set;

public class Sentinel implements Piece {

  private Player owner;

  private Board board;

  public Sentinel(Player owner) {
    this.owner = owner;
  }

  @Override
  public boolean isPlayerPiece(Player player) {
    return owner.equals(player);
  }

  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(origin.up());
    moveSet.add(origin.up().left());
    moveSet.add(origin.up().right());
    moveSet.add(origin.left());
    moveSet.add(origin.right());
    moveSet.add(origin.down());
    moveSet.add(origin.down().left());
    moveSet.add(origin.down().right());
    return moveSet;
  }

  @Override
  public int value() {
    return 3;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public Board getBoard() {
    return board;
  }

}
