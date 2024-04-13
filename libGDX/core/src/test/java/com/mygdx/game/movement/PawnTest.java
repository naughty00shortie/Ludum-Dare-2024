package com.mygdx.game.movement;

import com.mygdx.game.movement.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {

  Pawn pawn;

  Board board;

  @BeforeEach
  void setUp() {
    pawn = new Pawn();
    board = new Board();
  }

  /**
   * Player pawns can only move forward on rank, if there are no enemy pawns to capture.
   */
  @Test
  void movesFromBottomLeft() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    List<CoOrdinatePair> expectedMoveSet = Collections.singletonList(new CoOrdinatePair(0, 1));
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(expectedMoveSet.containsAll(actualMoveSet));
  }

  /**
   * Player pawns can only move forward on rank, if there are no enemy pawns to capture.
   */
  @Test
  void movesFromBottomRight() {
    CoOrdinatePair origin = new CoOrdinatePair(7, 0);
    List<CoOrdinatePair> expectedMoveSet = Collections.singletonList(new CoOrdinatePair(7, 1));
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(expectedMoveSet.containsAll(actualMoveSet));
  }

  /**
   * Player pawns can't move past their final rank (enemy home rank).
   */
  @Test
  void movesFromTopLeft() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 7);
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(actualMoveSet.isEmpty());
  }

  /**
   * Player pawns can't move past their final rank (enemy home rank).
   */
  @Test
  void movesFromTopRight() {
    CoOrdinatePair origin = new CoOrdinatePair(7, 7);
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board);
    assertTrue(actualMoveSet.isEmpty());
  }

}