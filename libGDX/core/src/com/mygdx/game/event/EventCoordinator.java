package com.mygdx.game.event;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameManager;
import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.players.Player;
import com.mygdx.game.round.RoundManager;
import com.mygdx.game.ui.MenuSummonScreen;

public class EventCoordinator {

  private final RoundManager roundManager = RoundManager.INSTANCE;

  private final Board board;

  private final TextButton moveButton;

  private final TextButton summonButton;

  private final MenuSummonScreen summonScreen;

  public EventCoordinator(GameManager game) {
    this.board = game.getChessBoardRenderer().getBoard();
    this.moveButton = game.getPlayMenuScreen().getMoveButton();
    this.summonButton = game.getPlayMenuScreen().getSummonButton();
    summonScreen = game.getSummonScreen();
  }

  // API

  public void run() {
    activateSummonButton(); // TODO Uncomment for Summoning Demo.
    activateMoveButton(); // TODO Uncomment for Moving Demo.
  }


  // Class Utils.

  private Player currentPlayer() {
    return roundManager.getCurrentTurn().getPlayer();
  }

  private EventCoordinator eventCoordinator() {
    return this;
  }


  // --- --- Event Handling --- ---

  // Summon Events

  private void activateSummonButton() {
    summonButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        deactivateSummonButton();
        new SummonSelectionEvent(summonScreen, eventCoordinator()::callbackToSummonPiece).start();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void deactivateSummonButton() {
    summonButton.clearListeners();
  }

  private void callbackToSummonPiece(Piece piece) {
    new SummonEvent(board, piece).start();
  }

  // Move Events

  private void activateMoveButton() {
    moveButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        deactivateMoveButton();
        new MoveEvent(board, currentPlayer()).start();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void deactivateMoveButton() {
    summonButton.clearListeners();
  }
}
