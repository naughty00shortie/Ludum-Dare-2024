package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {

  SpriteBatch batch;

  ChessBoard chessBoard;

  SummoningCircle summoningCircle;

  @Override
  public void create() {
    //summoningCircle = new SummoningCircle();
    //summoningCircle.create();
    chessBoard = new ChessBoard();
    chessBoard.create();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    chessBoard.render();
    //summoningCircle.render();
  }

  @Override
  public void dispose() {
    chessBoard.dispose();
//    batch.dispose();
    //summoningCircle.dispose();
    //img.dispose();
  }
}
