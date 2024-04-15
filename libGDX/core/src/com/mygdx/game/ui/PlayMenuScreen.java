package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayMenuScreen extends ApplicationAdapter {

  public static final int BUTTON_SPACING = 20;

  public static final int BUTTON_WIDTH = 150;

  public static final int BUTTON_HEIGHT = 75;

  private static final int X_POS = 1200;

  private static final int Y_POS = 200;

  private static final int BACKGROUND_WIDTH = Gdx.graphics.getWidth() / 4;

  private static final int BACKGROUND_HEIGHT = Gdx.graphics.getHeight() / 4 + 300;

  private Stage stage;

  private OrthographicCamera camera;

  private SpriteBatch batch;

  private BitmapFont font;

  private Texture backgroundTexture;

  private Sprite backgroundSprite;

  private Skin skin;

  @Override
  public void create() {

    backgroundTexture = new Texture("options_background.png");

    backgroundSprite = new Sprite(backgroundTexture);
    backgroundSprite.setPosition(X_POS, Y_POS);
    backgroundSprite.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
    batch = new SpriteBatch();
    font = new BitmapFont();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    stage = new Stage();
    Gdx.input.setInputProcessor(stage);

    skin = new Skin();

    skin.add("buttonUp", new Texture("button_base.png"));
    skin.add("buttonDown", new Texture("button_hover.png"));

    TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();

    textButtonStyle.up = skin.getDrawable("buttonUp");
    textButtonStyle.over = skin.getDrawable("buttonDown");

    textButtonStyle.font = font;

    TextButton moveButton = new TextButton("Move", textButtonStyle);
    TextButton summonButton = new TextButton("Summon", textButtonStyle);

    moveButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
    summonButton.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);

    float fontScale = 1.5f;
    font.getData().setScale(fontScale);

    moveButton.setPosition(X_POS + BACKGROUND_WIDTH / 2 - moveButton.getWidth() / 2,
            Y_POS + BACKGROUND_HEIGHT / 2 + BUTTON_SPACING / 2);
    summonButton.setPosition(X_POS + BACKGROUND_WIDTH / 2 - summonButton.getWidth() / 2,
            Y_POS + BACKGROUND_HEIGHT / 2 - summonButton.getHeight() - BUTTON_SPACING / 2);


    moveButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("Move button clicked!");
      }
    });

    summonButton.addListener(new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
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
    backgroundSprite.draw(batch);
    batch.end();
    stage.draw();
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
    backgroundTexture.dispose();
    font.dispose();
  }

  public Stage getStage() {
    return stage;
  }
}
