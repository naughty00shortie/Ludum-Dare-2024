package com.mygdx.game.movement;

import com.mygdx.game.movement.pieces.Piece;

import java.awt.*;
import java.util.Optional;

public class Cell {

  private final Rectangle rectangle;

  private final int xCoOrdinate;

  private final int yCoOrdinate;

  private Piece piece;

  public Cell(int xCoOrdinate, int yCoOrdinate, int offset, int size) {
    this.xCoOrdinate = xCoOrdinate;
    this.yCoOrdinate = yCoOrdinate;
    this.rectangle = new Rectangle(xCoOrdinate * offset, yCoOrdinate * offset, size, size);
  }

  public Cell(int xCoOrdinate, int yCoOrdinate, Rectangle rect) {
    this.xCoOrdinate = xCoOrdinate;
    this.yCoOrdinate = yCoOrdinate;
    this.rectangle = rect;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  /**
   * @return true if there is a Piece present.
   */
  public boolean isOccupied() {
    return piece != null;
  }

  /**
   * @return Piece if present on Cell.
   */
  public Optional<Piece> getPiece() {
    return Optional.ofNullable(piece);
  }

  /**
   * @param piece to place on Cell.
   * @throws IllegalStateException if the Cell is already Occupied.
   */
  public void placePiece(Piece piece) throws IllegalStateException {
    if (isOccupied()) {
      throw new IllegalStateException("I have a piece bro");
    }
    this.piece = piece;
  }

  /**
   * Remove the Piece on this Cell.
   *
   * @return Piece that was removed.
   * @throws IllegalStateException if there was no Piece on the Cell.
   */
  public Piece removePiece() throws IllegalStateException {
    if (!isOccupied()) {
      throw new IllegalStateException("I have no piece");
    }
    Piece tempPiece = piece;
    piece = null;
    return tempPiece;
  }

  public int getX() {
    return xCoOrdinate;
  }

  public int getY() {
    return yCoOrdinate;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Cell cell = (Cell) o;
    return xCoOrdinate == cell.xCoOrdinate && yCoOrdinate == cell.yCoOrdinate;
  }

  @Override
  public int hashCode() {
    int result = xCoOrdinate;
    result = 31 * result + yCoOrdinate;
    return result;
  }
}
