package com.mygdx.game.movement;

/**
 * Top level abstraction. The board is the source of most queries from the GUI.
 * Chess board is made up of ranks --- and files | .
 */
public class Board {

  public static final int BOARD_SIZE = 8;

  private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];

  private void buildBoard() {

  }

  public Cell[][] getCells() {
    return cells;
  }

  public void setCells(Cell[][] board) {
    this.cells = board;
  }

  public void movePiece(Piece piece, int oldX, int oldY, int newX, int newY) {
    // TODO bounds checking
    // summon piece
    // move piece, no piece at new position, we good
    // move piece and there is an enemy piece, capture
    Piece p = cells[oldX][oldY].removePiece();
    cells[newX][newY].placePiece(p);
  }

  public void summon(Piece piece, int x, int y) {

  }


  /*

  2D array

  getRank List  Cell cells know how they should be displayed
  getFile List  Cell

   */


}
