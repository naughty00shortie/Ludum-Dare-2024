package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.players.Player;

import java.util.HashSet;
import java.util.Set;

public class Hopper implements Piece {


  private Player owner;

  private Board board;

  public Hopper(Player owner) {
    this.owner = owner;
  }


  @Override
  public boolean isPlayerPiece(Player player) {
    return owner.equals(player);
  }

  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(origin.down());

    moveSet.add(origin.left());
    moveSet.add(origin.left().left());

    moveSet.add(origin.right());
    moveSet.add(origin.right().right());

    moveSet.add(origin.up());
    moveSet.add(origin.up().up());
    moveSet.add(origin.up().up().up());
    return moveSet;
  }

  @Override
  public int value() {
    return 5;
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
