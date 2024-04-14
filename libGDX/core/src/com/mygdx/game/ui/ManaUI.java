package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ManaUI extends ApplicationAdapter {

  private static final int BAR_WIDTH = 500;

  private static final int BAR_HEIGHT = 20;

  private static final int MAX_MANA = 100;

  private static int manaAmount = 100;

  private ShapeRenderer shapeRenderer;


  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
  }

  @Override
  public void render() {

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    manaAmount--;

    if (manaAmount < 0) {
      manaAmount = MAX_MANA;
    }

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(Color.BLUE);
    shapeRenderer.rect(100, 100, (float) manaAmount / MAX_MANA  * BAR_WIDTH, BAR_HEIGHT);
    shapeRenderer.end();

    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(100, 100, BAR_WIDTH, BAR_HEIGHT);
    shapeRenderer.end();

  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
