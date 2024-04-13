package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class BottomBarUI extends ApplicationAdapter {
  Skin skin;
  Stage stage;
  SpriteBatch batch;
  ImageButton[] pieceButtons = new ImageButton[6];
  Table table = new Table();

  @Override
  public void create() {
    stage.addActor(table);
  }
}
