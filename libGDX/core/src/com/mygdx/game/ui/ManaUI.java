package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ManaUI extends ApplicationAdapter {

  private static final int BAR_WIDTH = 200;

  private static final int BAR_HEIGHT = 50;

  private static final int MANA_REGEN = 1;

  private static final int POS_X = 1350;

  private static final int POS_Y = 650;

  private static int maxMana = 100;

  private static int manaAmount = 100;

  private ShapeRenderer shapeRenderer;

  private Texture texture;

  private Sprite sprite;

  private SpriteBatch batch;

  @Override
  public void create() {
    shapeRenderer = new ShapeRenderer();
    texture = new Texture(Gdx.files.internal("manabar_2.png"));
    sprite = new Sprite(texture);
    batch = new SpriteBatch();
  }

  @Override
  public void render() {

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(Color.BLUE);
    shapeRenderer.rect(POS_X, POS_Y + 10, (float) manaAmount / maxMana * BAR_WIDTH - 5, BAR_HEIGHT - 35);
    sprite.setPosition(POS_X, POS_Y - BAR_HEIGHT);
    sprite.setSize(BAR_WIDTH, BAR_HEIGHT + 100);
    shapeRenderer.end();
    batch.begin();
    sprite.draw(batch);
    batch.end();
  }

  @Override
  public void dispose() {
    shapeRenderer.dispose();
    batch.dispose();
    texture.dispose();
  }
}
