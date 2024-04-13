package com.mygdx.game.movement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

  @Test
  void moveFromOrigin_0_0() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    List<CoOrdinatePair> expectedMoveSet = Arrays.asList(new CoOrdinatePair(1, -1), new CoOrdinatePair(1, 0), new CoOrdinatePair(1, 1));
    Set<CoOrdinatePair> actualMoveSet = pawn.moveSet(origin, board.getCells());
    assertTrue(expectedMoveSet.containsAll(actualMoveSet));

  }
}