package com.mygdx.game.movement;

import java.awt.*;
import java.util.Optional;

public class Cell {

  private Rectangle rectangle;

  private Piece piece;

  public Cell(int xCoOrdinate, int yCoOrdinate, int size) {
    this.rectangle = new Rectangle(xCoOrdinate * size, yCoOrdinate * size, size, size);
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  /**
   * @return Piece if present on Cell.
   */
  public Optional<Piece> getPiece() {
    return Optional.ofNullable(piece);
  }

  public void placePiece(Piece piece) throws IllegalStateException {
    if (this.piece != null) {
      throw new IllegalStateException("I have a piece bro");
    }
    this.piece = piece;
  }

  public Piece removePiece() throws IllegalStateException {
    if (this.piece == null) {
      throw new IllegalStateException("I have no piece");
    }
    Piece tempPiece = this.piece;
    this.piece = null;
    return tempPiece;
  }

}
