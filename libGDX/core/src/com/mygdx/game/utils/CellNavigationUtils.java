package com.mygdx.game.utils;

import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

public class CellNavigationUtils {

    public static CoOrdinatePair moveOneCellUp(int x, int y) {
        return new CoOrdinatePair(x, y + 1);
    }

    public static CoOrdinatePair moveOneCellDown(int x, int y) {
        return new CoOrdinatePair(x, y - 1);
    }

    public static CoOrdinatePair moveOneCellLeft(int x, int y) {
        return new CoOrdinatePair(x - 1, y);
    }

    public static CoOrdinatePair moveOneCellRight(int x, int y) {
        return new CoOrdinatePair(x + 1, y);
    }

    public static CoOrdinatePair moveOneCellDiagonalTopLeft(int x, int y) {
        return new CoOrdinatePair(x - 1, y + 1);
    }

    public static CoOrdinatePair moveOneCellDiagonalBottomLeft(int x, int y) {
        return new CoOrdinatePair(x - 1, y - 1);
    }

    public static CoOrdinatePair moveOneCellDiagonalBottomRight(int x, int y) {
        return new CoOrdinatePair(x + 1, y - 1);
    }

    public static CoOrdinatePair moveOneCellDiagonalTopRight(int x, int y) {
        return new CoOrdinatePair(x + 1, y + 1);
    }

    public static Cell moveOneCellUp(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x, y + 1, cell.getRectangle());
    }

    public static Cell moveOneCellDown(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x, y - 1, cell.getRectangle());
    }

    public static Cell moveOneCellLeft(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x - 1, y, cell.getRectangle());
    }

    public static Cell moveOneCellRight(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x + 1, y, cell.getRectangle());
    }

    public static Cell moveOneCellDiagonalTopLeft(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x - 1, y + 1, cell.getRectangle());
    }

    public static Cell moveOneCellDiagonalBottomLeft(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x - 1, y - 1, cell.getRectangle());
    }

    public static Cell moveOneCellDiagonalBottomRight(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x + 1, y - 1, cell.getRectangle());
    }

    public static Cell moveOneCellDiagonalTopRight(Cell cell) {
        int x = cell.getXCoOrdinate();
        int y = cell.getYCoOrdinate();
        return new Cell(x + 1, y + 1, cell.getRectangle());
    }

}
