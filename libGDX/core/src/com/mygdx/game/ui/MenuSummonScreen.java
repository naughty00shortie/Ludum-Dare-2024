package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuSummonScreen extends ApplicationAdapter {
  //add a stage and add actors to the stage
  //Eventually you need to add Listeners to your button actors to switch screens
  Skin skin;
  Stage stage;
  SpriteBatch batch;
  @Override
  public void create () {
    batch = new SpriteBatch();
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    skin = new Skin();

    // Generate a 1x1 white texture and store it in the skin named "white".
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.WHITE);
    pixmap.fill();
    skin.add("white", new Texture(Gdx.files.internal("badlogic.jpg")));

    // Store the default libGDX font under the name "default".
    skin.add("default", new BitmapFont());

    // Configure a ImageButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
    ImageButton.ImageButtonStyle ImageButtonStyle = new ImageButton.ImageButtonStyle();
    ImageButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
    ImageButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
    ImageButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
    ImageButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
    skin.add("default", ImageButtonStyle);

    // Create a table that fills the screen. Everything else will go inside this table.
    Table table = new Table();
    table.setFillParent(true);
    stage.addActor(table);

    // Create a button with the "default" ImageButtonStyle. A 3rd parameter can be used to specify a name other than "default".
    ImageButton button = new ImageButton(skin);
    table.add(button);

    // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
    // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
    // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
    // revert the checked state.
    button.addListener(new ChangeListener() {
      public void changed (ChangeEvent event, Actor actor) {
        System.out.println("Clicked! Is checked: " + button.isChecked());
      }
    });

    // Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
    table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
  }

  @Override
  public void render () {
    ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);
    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
  }

  @Override
  public void resize (int width, int height) {
    stage.getViewport().update(width, height, true);
  }

  @Override
  public void dispose () {
    stage.dispose();
    skin.dispose();
  }
}
