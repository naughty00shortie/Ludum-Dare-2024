package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.players.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Basic Summon of the game. Marches and captures in one direction.
 */
public class Grunt implements Piece {

  Player owner;

  Board board;

  public Grunt(Player player) {
    this.owner = player;
  }

  @Override
  public boolean isPlayerPiece(Player player) {
    return owner.equals(player);
  }

  /**
   * Player Pieces move up the Board, increasing in Y, whereas
   * Enemy Pieces move down the Board, decreasing in Y.
   */
  @Override
  public Set<CoOrdinatePair> potentialMoveSet(CoOrdinatePair origin) {
    CoOrdinatePair step = (isPlayerPiece(owner)) ? origin.up() : origin.down();
    Set<CoOrdinatePair> moveSet = new HashSet<>();
    moveSet.add(step);
    moveSet.add(step.left());
    moveSet.add(step.right());
    return moveSet;
  }

  @Override
  public int value() {
    return 1;
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
