package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;

import java.util.HashSet;
import java.util.Set;

public class Queen implements Piece{

    @Override
    public boolean isPlayerPiece() {
        return false;
    }

    @Override
    public Set<CoOrdinatePair> moveSet(int xOrigin, int yOrigin, Board board) {
        Set<CoOrdinatePair> possibleMoves = new HashSet<>();
        int boardSize = board.getCells().length;


        for(int i = xOrigin; i < boardSize; i++) { // check right
            CoOrdinatePair coOrdinatePair = new CoOrdinatePair(i, yOrigin);
            Cell c = board.getCell(coOrdinatePair).get();
            if(c.isOccupied()) { // TODO check if enemy piece
                break;
            }
            possibleMoves.add(coOrdinatePair);
        }
        for(int i = xOrigin; i >= 0; i--) { // check left
            CoOrdinatePair coOrdinatePair = new CoOrdinatePair(i, yOrigin);
            Cell c = board.getCell(coOrdinatePair).get();
            if(c.isOccupied()) { // TODO check if enemy piece
                break;
            }
            possibleMoves.add(coOrdinatePair);
        }
        for(int i = yOrigin; i >= 0; i--) { // check down
            CoOrdinatePair coOrdinatePair = new CoOrdinatePair(i, yOrigin);
            Cell c = board.getCell(coOrdinatePair).get();
            if(c.isOccupied()) { // TODO check if enemy piece
                break;
            }
            possibleMoves.add(coOrdinatePair);
        }
        for(int i = yOrigin; i < boardSize; i++) { // check up
            CoOrdinatePair coOrdinatePair = new CoOrdinatePair(xOrigin, i);
            Cell c = board.getCell(coOrdinatePair).get();
            if(c.isOccupied()) { // TODO check if enemy piece
                break;
            }
            possibleMoves.add(coOrdinatePair);
        }

        return null;
    }
}
