package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.pieces.Grunt;
import com.mygdx.game.movement.pieces.Hopper;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.movement.pieces.Sentinel;

/**
 * Static utility class to make it easier to place sprites on cells. Useful for moving pieces
 * and summoning sprites.
 */
public class SpriteManager {

  private static final Skin spriteSkins;

  static {
    spriteSkins = new Skin();
    spriteSkins.add("grunt", new Texture(Gdx.files.internal("grunt_back.png")));
    spriteSkins.add("hopper", new Texture(Gdx.files.internal("hopper_forward.png")));
    spriteSkins.add("sentinel", new Texture(Gdx.files.internal("sentinel_forward.png")));
  }

  /**
   * Static Utility Class.
   */
  private SpriteManager() {
  }

  public static void placeSpriteOn(Piece piece, Cell cell) {
    placeSpriteOn(piece.getClass(), cell);
  }

  public static void placeSpriteOn(Class<? extends Piece> pieceClass, Cell cell) {
    cell.setStyle(styleFor(pieceClass));
  }

  public static void removeSpriteFrom(Cell cell) {
    cell.setStyle(new ImageButton.ImageButtonStyle());
  }

  // Utilities API

  public static Drawable getGrunt() {
    return spriteSkins.getDrawable("grunt");
  }

  public static Drawable getSentinel() {
    return spriteSkins.getDrawable("sentinel");
  }

  public static Drawable getHopper() {
    return spriteSkins.getDrawable("hopper");
  }

  // Helper methods

  /**
   * @see SpriteManager#styleFor(Class)
   */
  public static ImageButton.ImageButtonStyle styleFor(Piece piece) {
    return styleFor(piece.getClass());
  }

  /**
   * @return new ImageButtonStyle that has the sprites for this Piece class preset.
   */
  public static ImageButton.ImageButtonStyle styleFor(Class<? extends Piece> pieceClass) {
    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
    Drawable pieceSkin;
    if (pieceClass == Grunt.class) {
      pieceSkin = getGrunt();
    } else if (pieceClass == Sentinel.class) {
      pieceSkin = getSentinel();
    } else if (pieceClass == Hopper.class) {
      pieceSkin = getHopper();
    } else {
      throw new UnsupportedOperationException("");
    }
    style.up = pieceSkin;
    style.down = pieceSkin;
    return style;
  }
}
