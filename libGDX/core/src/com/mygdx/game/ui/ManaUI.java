package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ManaUI extends ApplicationAdapter {

  private static final int BAR_WIDTH = 500;

  private static final int BAR_HEIGHT = 20;

  private static final int MANA_REGEN = 1;

  private static final int POS_X = 1300;

  private static final int POS_Y = 50;

  private static int maxMana = 100;

  private static int manaAmount = 100;

  private ShapeRenderer shapeRenderer;


  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
  }

  @Override
  public void render() {
    manaAmount--;
    if (manaAmount < 0) {
      manaAmount = maxMana;
    }

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(Color.BLUE);
    shapeRenderer.rect(POS_X, POS_Y, (float) manaAmount / maxMana * BAR_WIDTH, BAR_HEIGHT);
    shapeRenderer.end();

    shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
    shapeRenderer.setColor(Color.WHITE);
    shapeRenderer.rect(POS_X, POS_Y, BAR_WIDTH, BAR_HEIGHT);
    shapeRenderer.end();
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}
