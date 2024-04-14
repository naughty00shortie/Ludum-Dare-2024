package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.awt.*;


public class MenuSummonScreen extends ApplicationAdapter {


  public static final int BUTTON_WIDTH = 300;

  public static final int BUTTON_HEIGHT = 200;

  public static final int BUTTON_PADDING = 100;

  public static final int POS_X = 1050;

  public static final int POS_Y = 180;

  private Stage stage;

  private ImageButton[] buttons;

  private Skin[] skins;

  private String[] skinNames = {"pawn", "knight", "bishop", "rook", "queen", "king"};


  private ShapeRenderer shapeRenderer;

  private OrthographicCamera camera;

  @Override
  public void create() {
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    skins = new Skin[3];

    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.WHITE);
    pixmap.fill();
    for (int i = 0; i < skins.length; i++) {
      skins[i] = new Skin();
    }

    skins[0].add("pawn", new Texture(Gdx.files.internal("grunt_back.png")));
    skins[1].add("knight", new Texture(Gdx.files.internal("hopper_forward.png")));
    skins[2].add("bishop", new Texture(Gdx.files.internal("sentinel_forward.png")));

    ImageButton.ImageButtonStyle[] ImageButtonStyles = new ImageButton.ImageButtonStyle[6];

    buttons = new ImageButton[3];
    for (int i = 0; i < 3; i++) {
      ImageButtonStyles[i] = new ImageButton.ImageButtonStyle();
      ImageButtonStyles[i].up = skins[i].newDrawable(skinNames[i], Color.WHITE);
      ImageButtonStyles[i].down = skins[i].newDrawable(skinNames[i], new Color(1f, 0, 0, 1));
      ImageButtonStyles[i].checked = skins[i].newDrawable(skinNames[i], new Color(0.1f, 0.8f, 1, 1));
      ImageButtonStyles[i].over = skins[i].newDrawable(skinNames[i], Color.DARK_GRAY);

      skins[i].add(skinNames[i], ImageButtonStyles[i]);
      buttons[i] = new ImageButton(skins[i].getDrawable(skinNames[i]));
      buttons[i].setStyle(ImageButtonStyles[i]);


      buttons[i].setPosition(POS_X + i * (BUTTON_PADDING) + 250, POS_Y+ (BUTTON_PADDING));

      int finalI = i;
      buttons[i].addListener(new ChangeListener() {
        public void changed(ChangeEvent event, Actor actor) {
          System.out.println("Clicked! Is checked:  " + finalI + " " + buttons[finalI].isChecked());
        }
      });

      stage.addActor(buttons[i]);
    }

    shapeRenderer = new ShapeRenderer();
    Vector3 touchPos = new Vector3();
    touchPos.set(POS_X , Gdx.graphics.getHeight() - POS_Y + POS_Y - BUTTON_HEIGHT, 0);
    camera.unproject(touchPos);
  }

  @Override
  public void render() {
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose() {
    stage.dispose();
    for (int i = 0; i < skins.length; i++) {
      skins[i].dispose();
      buttons[i].clear();
    }
    shapeRenderer.dispose();
  }

  public Stage getStage() {
    return stage;
  }
}
