package com.mygdx.game.movement.pieces;

import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.CoOrdinatePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing of Grunt Piece should be sufficient to cover all other piece
 * implementations. The primary method being tested is Piece#moveSet which
 * will filter out the possible moves a Piece can reach.
 *
 * @see Piece#moveSet(CoOrdinatePair, Board)
 */
class GruntTest {

  private Grunt grunt;

  private Board board;

  @BeforeEach
  void setUp() {
    board = new Board();
    grunt = new Grunt();
  }

  @Test
  void basicMove() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    Set<CoOrdinatePair> moveSet = grunt.moveSet(origin, board);
    assertEquals(2, moveSet.size(), "Only forward and forward-right are possible.");
    assertTrue(moveSet.contains(origin.up()));
    assertTrue(moveSet.contains(origin.up().right()));
  }

  @Test
  void allyBlocking() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    board.getCell(origin.up()).get().placePiece(new Grunt());
    Set<CoOrdinatePair> moveSet = grunt.moveSet(origin, board);
    assertEquals(1, moveSet.size(), "Only forward-right is possible.");
    assertTrue(moveSet.contains(origin.up().right()));
  }

  @Test
  void enemyDoesNotBlock() {
    CoOrdinatePair origin = new CoOrdinatePair(0, 0);
    board.getCell(origin.up()).get().placePiece(new Grunt(false));
    Set<CoOrdinatePair> moveSet = grunt.moveSet(origin, board);
    assertEquals(2, moveSet.size(), "Only forward (capture) and forward-right are possible.");
    assertTrue(moveSet.contains(origin.up()));
    assertTrue(moveSet.contains(origin.up().right()));
  }

}