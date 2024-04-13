package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.ui.MenuSummonScreen;

public class MyGdxGame extends ApplicationAdapter {

  ChessBoardRenderer chessBoardRenderer;

  MenuSummonScreen menuScreen;

  @Override
  public void create() {

     menuScreen = new MenuSummonScreen();
     menuScreen.create();
    //chessBoardRenderer = new ChessBoardRenderer();
//    chessBoardRenderer.create();
  }

  @Override
  public void render() {
//    chessBoardRenderer.render();
    menuScreen.render();
  }

  @Override
  public void dispose() {
    menuScreen.dispose();
//    chessBoardRenderer.dispose();
  }
}
