package com.mygdx.game.movement;

import java.awt.*;
import java.util.List;

/**
 * Top level abstraction. The board is the source of most queries from the GUI.
 * Chess board is made up of ranks --- and files | .
 */
public class Board {

  public static final int BOARD_SIZE = 8;

  private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

  private void buildBoard(){

  }

  /*

  2D array

  getRank List  Cell cells know how they should be displayed
  getFile List  Cell

   */


}
