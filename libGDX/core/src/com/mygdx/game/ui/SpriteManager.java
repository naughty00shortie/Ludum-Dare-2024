package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
    spriteSkins.add("enemy", new Texture(Gdx.files.internal("summons/enemy_back.png")));
    spriteSkins.add("grunt", new Texture(Gdx.files.internal("summons/grunt_forward.png")));
    spriteSkins.add("hopper", new Texture(Gdx.files.internal("summons/hopper_forward.png")));
    spriteSkins.add("sentinel", new Texture(Gdx.files.internal("summons/sentinel_forward.png")));

    spriteSkins.add("possible_move", new Texture(Gdx.files.internal("utility_sprites/possible_move.png")));
    spriteSkins.add("selected_square", new Texture(Gdx.files.internal("utility_sprites/selected_square.png")));
    spriteSkins.add("summoning_circle", new Texture(Gdx.files.internal("utility_sprites/summoning_circle.png")));
  }

  /**
   * Static Utility Class.
   */
  private SpriteManager() {
  }

  public static void placeSpriteOn(Drawable sprite, Cell cell) {
    ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
    style.up = sprite;
    style.down = sprite;
    cell.setStyle(style);
  }

  // Delegators for convenience.

  public static void placeSpriteOn(Piece piece, Cell cell) {
    placeSpriteOn(piece.getClass(), cell);
  }

  public static void placeSpriteOn(Class<? extends Piece> pieceClass, Cell cell) {
    placeSpriteOn(spriteFor(pieceClass), cell);
  }

  public static void removeSpriteFrom(Cell cell) {
    cell.setStyle(new ImageButton.ImageButtonStyle());
  }

  // Utilities Accessors

  public static Drawable getPossibleMove() {
    return spriteSkins.getDrawable("possible_move");
  }

  public static Drawable getSelectedSquare() {
    return spriteSkins.getDrawable("selected_square");
  }

  public static Drawable getSummoningCircle() {
    return spriteSkins.getDrawable("summoning_circle");
  }

  // Summons Accessors

  public static Drawable getEnemy() {
    return spriteSkins.getDrawable("enemy");
  }

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
   * @see SpriteManager#spriteFor(Class)
   */
  public static Drawable spriteFor(Piece piece) {
    return spriteFor(piece.getClass());
  }

  /**
   * @return new ImageButtonStyle that has the sprites for this Piece class preset.
   */
  public static Drawable spriteFor(Class<? extends Piece> pieceClass) {
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
    return pieceSkin;
  }

  public static Drawable tintForSprite(Piece piece, Color color) {
    return tintForSprite(spriteFor(piece), color);
  }

  public static Drawable tintForSprite(Drawable sprite, Color color) {
    return spriteSkins.newDrawable(sprite, color);
  }
}
