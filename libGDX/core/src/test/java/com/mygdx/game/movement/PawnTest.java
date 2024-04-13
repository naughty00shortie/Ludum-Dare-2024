package com.mygdx.game.movement;

import com.mygdx.game.movement.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "SameParameterValue"})
class PawnTest {

  Pawn pawn;

  Board board;

  @BeforeEach
  void setUp() {
    pawn = new Pawn();
    board = new Board();
  }

  // Player pawns can only move forward on rank, if there are no enemy pawns to capture.

  @Test
  void movesFromBottomLeft() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    List<CoOrdinatePair> expectedMoveSet = Collections.singletonList(new CoOrdinatePair(0, 1));
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertEquals(1, actualMoveSet.size(), "Only one possible move from this position.");
    assertTrue(expectedMoveSet.containsAll(actualMoveSet));
  }

  @Test
  void movesFromBottomRight() {
    CoOrdinatePair origin = new CoOrdinatePair(7, 0);
    List<CoOrdinatePair> expectedMoveSet = Collections.singletonList(new CoOrdinatePair(7, 1));
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertEquals(1, actualMoveSet.size(), "Only one possible move from this position.");
    assertTrue(expectedMoveSet.containsAll(actualMoveSet));
  }

  // Player pawns can't move past their final rank (enemy home rank).

  @Test
  void movesFromTopLeft() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 7);
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(actualMoveSet.isEmpty());
  }

  @Test
  void movesFromTopRight() {
    CoOrdinatePair origin = new CoOrdinatePair(7, 7);
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(actualMoveSet.isEmpty());
  }

  // --- --- Captures --- ---

  @Test
  void canCaptureEnemy() {
    placeEnemyPieceAt(1, 1);
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    Set<CoOrdinatePair> moveSet = pawn.moveSet(origin, board);
    assertEquals(2, moveSet.size(), "Two possible moves from this position.");
    assertTrue(moveSet.contains(new CoOrdinatePair(1, 1)), "Capture on 1,1 should be possible.");
  }

  @Test
  void cannotCaptureAlly() {
    placeAllyPieceAt(1, 1);
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    assertEquals(1, pawn.moveSet(origin, board).size(), "Only one possible move from this position.");
  }

  // --- --- Utilities --- ---

  void placeEnemyPieceAt(int x, int y) {
    placePieceAt(x, y, false);
  }

  void placeAllyPieceAt(int x, int y) {
    placePieceAt(x, y, true);
  }

  private void placePieceAt(int x, int y, boolean isPlayerPiece) {
    board.getCell(new CoOrdinatePair(x, y)).get().placePiece(new Pawn(isPlayerPiece));
  }

}