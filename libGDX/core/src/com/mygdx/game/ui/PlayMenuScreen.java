package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayMenuScreen extends ApplicationAdapter {
  private Stage stage;
  private OrthographicCamera camera;
  private SpriteBatch batch;
  private BitmapFont font;
  private Skin skin;

  @Override
  public void create() {
    batch = new SpriteBatch();
    font = new BitmapFont();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    stage = new Stage();
    Gdx.input.setInputProcessor(stage);

    skin = new Skin();
    skin.add("buttonUp", new Texture("badlogic.jpg"));
    skin.add("buttonDown", new Texture("badlogic.jpg"));
    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("buttonUp");
    textButtonStyle.down = skin.getDrawable("buttonDown");

    textButtonStyle.font = font;

    TextButton moveButton = new TextButton("Move", textButtonStyle);
    TextButton summonButton = new TextButton("Summon", textButtonStyle);

    moveButton.setPosition(Gdx.graphics.getWidth() / 2 - moveButton.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 + 50);
    summonButton.setPosition(Gdx.graphics.getWidth() / 2 - summonButton.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - summonButton.getHeight() - 50);

    moveButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        // Handle move button click
        System.out.println("Move button clicked!");
      }
    });

    summonButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        // Handle summon button click
        System.out.println("Summon button clicked!");
      }
    });

    stage.addActor(moveButton);
    stage.addActor(summonButton);
  }

  @Override
  public void render() {
    camera.update();
    batch.setProjectionMatrix(camera.combined);

    batch.begin();
    stage.draw();
    batch.end();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose() {
    stage.dispose();
    batch.dispose();
    skin.dispose();
    font.dispose();
  }
}
