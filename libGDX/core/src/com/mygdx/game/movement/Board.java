package com.mygdx.game.movement;

import com.mygdx.game.movement.pieces.Piece;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.mygdx.game.utils.CellNavigationUtils.*;

/**
 * Top level abstraction. The board is the source of most queries from the GUI.
 * Chess board is made up of ranks --- and files | .
 */
public class Board {

  public static final int BOARD_SIZE = 8;

  public static final int SIZE = 100;

  public static final int OFFSET = 100;

  private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];

  public Board() {
    buildBoardCells();
  }

  /**
   * Initialise, or reset, the cells on this board.
   */
  private void buildBoardCells() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        cells[i][j] = new Cell(i, j, OFFSET, SIZE);
      }
    }
  }

  public Cell[][] getCells() {
    return cells;
  }

  public void setCells(Cell[][] board) {
    this.cells = board;
  }

  public Set<CoOrdinatePair> getValidMoves(int x, int y) {
    Piece p = cells[x][y].getPiece().orElseThrow(() -> new RuntimeException("No piece found"));
    return p.moveSet(new CoOrdinatePair(x, y), this);
  }

  public void movePiece(Piece piece, int oldX, int oldY, int newX, int newY) {
    // TODO bounds checking
    // summon piece
    // move piece, no piece at new position, we good
    // move piece and there is an enemy piece, capture
    Piece p = cells[oldX][oldY].removePiece();
    cells[newX][newY].placePiece(p);
  }

  /**
   * Takes the piece on the 'from' coordinate and places it on the 'to' coordinate.
   * It removes any existing piece on 'to'
   * @param from
   * @param to
   *
   * @throws IllegalStateException if the Cell is already Occupied. (from cell.placePiece(piece);)
   */
  public void movePiece(CoOrdinatePair from, CoOrdinatePair to) {
    getCell(from).ifPresentOrElse(fromCell -> {
      fromCell.getPiece().ifPresentOrElse(piece -> {
        fromCell.removePiece();
        getCell(to).ifPresentOrElse(cell -> {
          cell.removePiece();
          cell.placePiece(piece);
        }, () -> {
          throw new RuntimeException(to + ", cell does not exist on board");
        });
      }, () -> {
        throw new RuntimeException("No piece at " + from);
      });
    }, () -> {
      throw new RuntimeException(from + ", cell does not exist on board");
    });
  }

  public void summon(Piece piece, int x, int y) {
    cells[x][y].placePiece(piece);
  }

  public void summon(Piece piece, CoOrdinatePair to) {
    getCell(to).ifPresent(cell -> {
      cell.getPiece().ifPresentOrElse((p -> {
        throw new RuntimeException("Can't place piece on top of another piece");
      }), (() -> cell.placePiece(piece)));
    });
  }

  public Set<Cell> getCellsWithPieces() {
    Set<Cell> pieces = new HashSet<>();
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        Cell cell = cells[i][j];
        if (cell.isOccupied()) {
          pieces.add(cell);
        }
      }
    }
    return pieces;
  }


  public Set<Cell> getSummonableCells() {
    Set<Cell> summonablePositions = new HashSet<>();
    Set<Cell> pieces = getCellsWithPieces();
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        Cell cell = cells[i][j];
        if (!cell.isOccupied() && cellIsAdjacentToPiece(cell, pieces)) summonablePositions.add(cell);
        if (j == 0) summonablePositions.add(cell);
        if (!cell.isOccupied() && cellIsAdjacentToPiece(cell, pieces)) {
          summonablePositions.add(cell);
        }
        if (j == 0) {
          summonablePositions.add(cell);
        }
      }
    }
    return summonablePositions;
  }

  // IANDRO: "this is nasty, so fix it if it doesn't work"
  private boolean cellIsAdjacentToPiece(Cell cell, Set<Cell> pieces) {
    int cellX = cell.getXCoOrdinate();
    int cellY = cell.getYCoOrdinate();
    for (Cell piece : pieces) {
      if (moveOneCellDiagonalTopRight(piece).equals(cell)) return true;
      if (moveOneCellDiagonalBottomRight(piece).equals(cell)) return true;
      if (moveOneCellDiagonalBottomLeft(piece).equals(cell)) return true;
      if (moveOneCellDiagonalTopLeft(piece).equals(cell)) return true;
      if (moveOneCellLeft(piece).equals(cell)) return true;
      if (moveOneCellRight(piece).equals(cell)) return true;
      if (moveOneCellUp(piece).equals(cell)) return true;
      if (moveOneCellDown(piece).equals(cell)) return true;
    }
    return false;
  }


  /**
   * Get the Cell at this CoOrdinate
   *
   * @param coOrdinatePair to retrieve a Cell from.
   * @return Cell if CoOrdinate is within the bounds of the Board.
   */
  public Optional<Cell> getCell(CoOrdinatePair coOrdinatePair) {
    if (isInBounds(coOrdinatePair)) {
      return Optional.of(cells[coOrdinatePair.getX()][coOrdinatePair.getY()]);
    } else {
      return Optional.empty();
    }
  }

  private boolean isInBounds(CoOrdinatePair coOrdinatePair) {
    int x = coOrdinatePair.getX();
    int y = coOrdinatePair.getY();
    if (x < 0 || x >= BOARD_SIZE) {
      return false;
    }
    if (y < 0 || y >= BOARD_SIZE) {
      return false;
    }
    return true;
  }

}
