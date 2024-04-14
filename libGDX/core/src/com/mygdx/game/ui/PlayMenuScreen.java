package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.*;

public class PlayMenuScreen extends ApplicationAdapter {

  private static final int X_POS = 0;

  private static final int Y_POS = -300;

  private Stage stage;

  private OrthographicCamera camera;

  private SpriteBatch batch;

  private BitmapFont font;

  private Skin skin;

  private Rectangle background;

  private ShapeRenderer shapeRenderer;

  @Override

  public void create() {
    shapeRenderer = new ShapeRenderer();
    background = new Rectangle((Gdx.graphics.getWidth() / 4) + 800 + X_POS, Gdx.graphics.getHeight() / 4 + Y_POS, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 2);
    batch = new SpriteBatch();
    font = new BitmapFont();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    stage = new Stage();
    Gdx.input.setInputProcessor(stage);

    skin = new Skin();
    skin.add("buttonUp", new Texture("png/button_blank.png"));
    skin.add("buttonDown", new Texture("png/button_blank.png"));
    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    textButtonStyle.up = skin.getDrawable("buttonUp");
    textButtonStyle.down = skin.getDrawable("buttonDown");

    textButtonStyle.font = font;

    TextButton moveButton = new TextButton("Move", textButtonStyle);
    TextButton summonButton = new TextButton("Summon", textButtonStyle);

    float buttonSpacing = 20;
    float buttonWidth = 150;
    float buttonHeight = 75;
    moveButton.setSize(buttonWidth, buttonHeight);
    summonButton.setSize(buttonWidth, buttonHeight);

    float fontScale = 1.5f;
    font.getData().setScale(fontScale);


    moveButton.setPosition(background.x + background.width / 2 - moveButton.getWidth() / 2,
            background.y + background.height / 2 + buttonSpacing / 2);
    summonButton.setPosition(background.x + background.width / 2 - summonButton.getWidth() / 2,
            background.y + background.height / 2 - summonButton.getHeight() - buttonSpacing / 2);


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
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.setColor(0, 1, 1, 1);
    shapeRenderer.rect(background.x, background.y, background.width, background.height);
    shapeRenderer.end();
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

  public Stage getStage() {
    return stage;
  }
}
