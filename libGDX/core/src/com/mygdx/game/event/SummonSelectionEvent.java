package com.mygdx.game.event;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.movement.pieces.Grunt;
import com.mygdx.game.movement.pieces.Hopper;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.movement.pieces.Sentinel;
import com.mygdx.game.players.Player;
import com.mygdx.game.round.RoundManager;
import com.mygdx.game.ui.MenuSummonScreen;

import java.util.function.Consumer;

public class SummonSelectionEvent {

  private final Button gruntButton;

  private final Button sentinelButton;

  private final Button hopperButton;

  private Consumer<Piece> callback;

  public SummonSelectionEvent(MenuSummonScreen summonScreen) {
    gruntButton = summonScreen.getGruntButton();
    sentinelButton = summonScreen.getSentinelButton();
    hopperButton = summonScreen.getHopperButton();
  }

  // API

  public void start(Consumer<Piece> callback) {
    this.callback = callback;
    activateGruntButton();
    activateSentinelButton();
    activateHopperButton();
  }

  public void end(Piece piece) {
    gruntButton.clearListeners();
    sentinelButton.clearListeners();
    hopperButton.clearListeners();
    callback.accept(piece);
  }

  // Helper

  private static Player currentPlayer() {
    return RoundManager.INSTANCE.getCurrentTurn().getPlayer();
  }

  // Events

  private void activateGruntButton() {
    gruntButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        end(new Grunt(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void activateSentinelButton() {
    sentinelButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        end(new Sentinel(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void activateHopperButton() {
    hopperButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        end(new Hopper(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

}
