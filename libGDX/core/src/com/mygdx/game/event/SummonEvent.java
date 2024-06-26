package com.mygdx.game.event;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.SoundUtils;
import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.ui.SpriteManager;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

/**
 * A new SummonEvent is created, then started, to bring a Player piece to the board!
 * `new SummonEvent(board, piece).start();` - it will clean itself up afterward.
 * For simplicity's sake a SummonEvent is only applicable for a Player.
 * It manages the selection of the Player's Cell choices.
 */
public class SummonEvent {

  private final Board board;

  private final Piece piece;

  private final Drawable summoningCircle;

  public SummonEvent(Board board, Piece piece) {
    this.board = board;
    this.piece = piece;
    summoningCircle = SpriteManager.getSummoningCircle();
  }

  // --- API ---

  /**
   * We begin the SummonEvent by highlighting available Cells and setting
   * action listeners on their cells.
   */
  public void start() {
    summonableCells().forEach(this::markAsTargetForSummon);
  }

  /**
   * Clean up action listeners on Cells. Should not be necessary to call this manually, but I've left it public
   * just in case.
   */
  public void end() {
    summonableCells().forEach(this::unmarkAsTargetForSummon);
  }


  // Engine

  private void markAsTargetForSummon(Cell cell) {
    SpriteManager.placeSpriteOn(summoningCircle, cell);
    cell.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        end(); // Though this is junk, and probably a bad idea to remove listeners within a listener - IT WORKS!
        SoundUtils.playSummonSound();
        cell.placePiece(piece);
        SpriteManager.placeSpriteOn(piece, cell); // place sprite after cleaning up other places.
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  /**
   * @param cell target to remove action listeners and custom sprites from.
   */
  private void unmarkAsTargetForSummon(Cell cell) {
    cell.clearListeners();
    cell.setStyle(new Button.ButtonStyle());
  }

  /**
   * TODO Remove the hard coding from this. Defaulting to the entire back row.
   *
   * @return Set of Cells that the Player can summon to on this Board.
   */
  private Set<Cell> summonableCells() {
    Set<Cell> homeRow = new HashSet<>();
    for (int i = 0; i < board.getCells().length; i++) {
      homeRow.add(board.getCells()[i][0]);
    }
    return homeRow.stream().filter(not(Cell::isOccupied)).collect(Collectors.toSet());
  }
}
