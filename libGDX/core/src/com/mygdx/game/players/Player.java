package com.mygdx.game.players;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.movement.pieces.Piece;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The Mana Strategy (Suggestion):
 * <p>
 * Your mana corresponds to the total value of pieces you have.
 * Each piece has a value, in classical chess it is as follows:
 * Pawn: 1 point.
 * Knight: 3 points.
 * Bishop: 3 points.
 * Rook: 5 points.
 * Queen: 9 points.
 * <p>
 * In classical chess, at the beginning of the game, you have 8 pawns, 2 knights,
 * 2 bishops , and 2 rooks and 1 one queen, which leaves you with 8 + 6 + 6 + 10 + 9 points
 * <p>
 * <p>
 * However at the beginning,  if we start off with each player having too much manner, it means they can
 * summon really powerful pieces from the start.
 * Thus, at the start each player starts off with a set amount of pieces but 0 mana.
 * <p>
 * When you lose a piece, you lose mana. Eg. If you lose a pawn, you lose 1 point of mana.
 * <p>
 * When you take a piece, you gain mana. Eg. If you take a pawn, you earn 1 point of mana.
 * <p>
 * You can only summon pieces that you have taken. When you summon a piece, you use mana.
 * Eg. If you summon a pawn, you use a 1 point of mana.
 * <p>
 * There is no relation between manner and each turn.
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
    board.getCell(from).ifPresent(fromCell -> board.getCell(to).ifPresent(toCell -> fromCell.getPiece().ifPresent(fromPiece -> {
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

  public int getMana() {
    return mana;
  }
}
