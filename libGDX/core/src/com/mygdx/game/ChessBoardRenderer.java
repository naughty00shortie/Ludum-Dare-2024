package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;

import java.awt.*;

public class ChessBoardRenderer extends ApplicationAdapter {

  public static final int BOARD_SIZE = 8;

  public static final int SIZE = 100;

  public static final int OFFSET = 100;

  private final Board board = new Board();

  private ShapeRenderer shapeRenderer;

  private final boolean renderFriendlySummoningPosition = false;

  private final boolean renderEnemySummoningPosition = false;

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
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
          if (j == BOARD_SIZE - 1) //white squares in the last row
            setEnemySummoningPositionWhite();
        } else {//Else block for black squares
          setSummoningPositionBlack();
          if (j == 0) setFriendlySummoningPositionBlack();//black squares in the first row
          if (j == BOARD_SIZE - 1) setEnemySummoningPositionBlack();//black squares in the last row
        }
        Rectangle rect = board.getCells()[i][j].getRectangle();
        shapeRenderer.rect(rect.x + 100, rect.y + 100, rect.width, rect.height);
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




