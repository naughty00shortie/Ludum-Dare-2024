package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.mygdx.game.ui.ChessBoardRenderer;
import com.mygdx.game.ui.ManaUI;
import com.mygdx.game.ui.MenuSummonScreen;

public class MyGdxGame extends ApplicationAdapter {

  ChessBoardRenderer chessBoardRenderer;

  MenuSummonScreen menuScreen;

  @Override
  public void create() {
    menuScreen = new MenuSummonScreen();
    menuScreen.create();
    //chessBoardRenderer = new ChessBoardRenderer();
    //chessBoardRenderer.create();
    //todo add this to main game class to enable sound
    SoundUtils.create();
  }

  @Override
  public void render() {
    //chessBoardRenderer.render();
    menuScreen.render();
  }

  @Override
  public void dispose() {
    menuScreen.dispose();
    //chessBoardRenderer.dispose();
    //todo call this to dispose of all sound assets loaded
    SoundUtils.dispose();
  }
}
