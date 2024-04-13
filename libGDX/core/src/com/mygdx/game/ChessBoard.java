package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class ChessBoard extends ApplicationAdapter {

  public static final int BOARD_SIZE = 8;

  private Rectangle[][] board = new Rectangle[BOARD_SIZE][BOARD_SIZE];

  private ShapeRenderer shapeRenderer;


  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        board[i][j] = new Rectangle(i * 100, j * 100, 100, 100);
      }
    }
  }

  @Override
  public void render() {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if ((i + j) % 2 == 0) {//if block for white squares
          shapeRenderer.setColor(1, 1, 1, 1);
          if (j == 0) //white squares in the first row
            setFriendlySummoningPositionLight();
          if (j == 7) //white squares in the last row
            setEnemySummoningPositionWhite();
        } else {//Else block for black squares
          setSummoningPositionBlack();
          if (j == 0)
            setFriendlySummoningPositionBlack();//black squares in the first row
          if (j == 7)
            setEnemySummoningPositionBlack();//black squares in the last row
        }
        shapeRenderer.rect(board[i][j].x+100, board[i][j].y+100, board[i][j].width, board[i][j].height);
      }
    }
    shapeRenderer.end();
  }

  private void setEnemySummoningPositionBlack() {
    shapeRenderer.setColor(0.5f, 0.1f, 0.1f, 1);
  }

  private void setFriendlySummoningPositionBlack() {
    shapeRenderer.setColor(0, 0, 0.5F, 1);
  }

  private void setSummoningPositionBlack() {
    shapeRenderer.setColor(0, 0, 0, 1);
  }

  private void setEnemySummoningPositionWhite() {
    shapeRenderer.setColor(1, 0.5F, 0.5F, 1);
  }

  private void setFriendlySummoningPositionLight() {
    shapeRenderer.setColor(0.5F, 0.69F, 1F, 1);
  }

  @Override
  public void dispose() {
    shapeRenderer.dispose();
  }
}




