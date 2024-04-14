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

import java.util.function.Consumer;

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
    activateSummonButton();
  }


  // Class Utils.

  private Player currentPlayer() {
    return roundManager.getCurrentTurn().getPlayer();
  }

  private EventCoordinator eventCoordinator() {
    return this;
  }

  /**
   * TODO Connect this with the RoundManager, which will require some reworking/simplification.
   * Temporarily just reactivates buttons.
   *
   * @return method reference to end the Player's turn.
   */
  private Callback thenEndTurn() {
    System.out.println("Coordinator requesting to end turn");
    //    return (roundManager::endTurn);
    return () -> {
      System.out.println("Round begins!");
      activateMoveButton();
      activateSummonButton();
    };
  }

  private Consumer<Piece> thenSummonPiece() {
    return (piece) -> new SummonEvent(board, piece).start(thenEndTurn());
  }

  // --- --- Event Handling --- ---

  // Summon Events

  private void activateSummonButton() {
    summonButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        deactivateSummonButton();
        new SummonSelectionEvent(summonScreen).start(thenSummonPiece());
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void deactivateSummonButton() {
    summonButton.clearListeners();
  }

  // Move Events

  private void activateMoveButton() {
    moveButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        deactivateMoveButton();
        new MoveEvent(board, currentPlayer()).start(thenEndTurn());
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void deactivateMoveButton() {
    summonButton.clearListeners();
  }
}
