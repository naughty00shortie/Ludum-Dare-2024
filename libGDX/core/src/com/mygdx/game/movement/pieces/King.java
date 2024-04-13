package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mygdx.game.utils.CellNavigationUtils.*;

public class King implements Piece {

    @Override
    public boolean isPlayerPiece() {
        return false;
    }

    @Override
    public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
        Set<CoOrdinatePair> possibleMoves = possibleCoordinates(xOrigin, yOrigin);
        return possibleMoves.stream().filter(coOrdinatePair -> {
            Cell cell = board.getCell(coOrdinatePair).orElse(new Cell());
            if (cell.getX() == -1) return false;
            if (cell.isOccupied()) {
                return ! cell.getPiece().get().isPlayerPiece();
            }
            return true;
        }).collect(Collectors.toSet());
    }

    private Set<CoOrdinatePair> possibleCoordinates(int xOrigin, int yOrigin) {
        Set<CoOrdinatePair> coords = new HashSet<>();
        coords.add(moveOneCellDown(xOrigin, yOrigin));
        coords.add(moveOneCellUp(xOrigin, yOrigin));
        coords.add(moveOneCellLeft(xOrigin, yOrigin));
        coords.add(moveOneCellRight(xOrigin, yOrigin));
        coords.add(moveOneCellDiagonalBottomRight(xOrigin, yOrigin));
        coords.add(moveOneCellDiagonalBottomLeft(xOrigin, yOrigin));
        coords.add(moveOneCellDiagonalTopLeft(xOrigin, yOrigin));
        coords.add(moveOneCellDiagonalTopRight(xOrigin, yOrigin));
        return coords;
    }
}
