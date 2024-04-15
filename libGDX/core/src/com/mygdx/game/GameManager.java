package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.round.RoundManager;
import com.mygdx.game.ui.ChessBoardRenderer;
import com.mygdx.game.ui.ManaUI;
import com.mygdx.game.ui.MenuSummonScreen;
import com.mygdx.game.ui.PlayMenuScreen;

public class GameManager extends ApplicationAdapter {

  ChessBoardRenderer chessBoardRenderer;

  MenuSummonScreen summonScreen;

  PlayMenuScreen playMenuScreen;

  ManaUI manaUI;

  @Override
  public void create() {
    chessBoardRenderer = new ChessBoardRenderer();
    chessBoardRenderer.create();

    playMenuScreen = new PlayMenuScreen();
    playMenuScreen.create();

    summonScreen = new MenuSummonScreen();
    summonScreen.create();

    manaUI = new ManaUI();
    manaUI.create();

    InputMultiplexer multiplexer = new InputMultiplexer();
    multiplexer.addProcessor(playMenuScreen.getStage());
    multiplexer.addProcessor(summonScreen.getStage());
    multiplexer.addProcessor(chessBoardRenderer.getStage());

    Gdx.input.setInputProcessor(multiplexer);
    SoundUtils.create();
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    playMenuScreen.render();
    summonScreen.render();
    manaUI.render();
    chessBoardRenderer.render();
    RoundManager.INSTANCE.run();
  }

  @Override
  public void dispose() {
    playMenuScreen.dispose();
    chessBoardRenderer.dispose();
    summonScreen.dispose();
    manaUI.dispose();
  }
}
