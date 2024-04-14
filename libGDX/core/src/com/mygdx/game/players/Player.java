package com.mygdx.game.players;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.movement.pieces.Piece;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Each piece has a value. At the beginning you are given 9 units of mana.
 * The cost of summoning pieces:
 * Grunt: 1
 * Sentinal: 3
 * Hopper: 5
 *
 * When you capture a piece, you earn mana according to the value of the captured piece.
 */
public class Player {

  private static final int INIT_MANA = 0;

  private int mana = INIT_MANA;

  private final Board board;


  private final Collection<Piece> summonedPieces = new LinkedList<>();

  public Player(Board board) {
    this.board = board;
  }

  public void movePiece(CoOrdinatePair from, CoOrdinatePair to) {
    board.getCell(from)
            .ifPresent(fromCell -> board.getCell(to)
                    .ifPresent(toCell -> fromCell.getPiece()
                            .ifPresent(fromPiece -> {
                              if (fromPiece.isPlayerPiece(this) && fromPiece.validMove(from, to)) {
                                toCell.getPiece()
                                        .ifPresent(toPiece -> {
                                          // fromPiece.validMove(from, to) should return false if this is the players piece
                                          summonedPieces.add(toPiece); //you took the opponents toPiece, now it's yours
                                          mana += toPiece.value();
                                          toPiece.getOwner().reduceMannaBy(toPiece.value());
                                        });
                                board.movePiece(from, to); //also removes the piece on from, if there is a piece. Places piece on to.
                              }
                            })));

//    TODO: throw some exceptions
  }

  public void placeSummonedPiece(Piece p, CoOrdinatePair to) {
    if(mana < p.value()) throw new IllegalStateException("You do now have enough mana to summon this piece");
    board.getCell(to).ifPresent(toCell -> {
      if (! toCell.isOccupied()) {
        board.summon(p, to);
        summonedPieces.remove(p);
        mana -= p.value();
      }
    });
    //    TODO: throw some exceptions
  }

  public Collection<Piece> getSummonedPieces() {
    return summonedPieces;
  }

  public void reduceMannaBy(int amount) {
    mana -= amount;
  }

  public void increaseMannaBy(int amount) {
    mana += amount;
  }

  public int getMana() {
    return mana;
  }
}
