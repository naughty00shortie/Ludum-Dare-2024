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

  private final Consumer<Piece> callback;

  public SummonSelectionEvent(MenuSummonScreen summonScreen, Consumer<Piece> callback) {
    gruntButton = summonScreen.getGruntButton();
    sentinelButton = summonScreen.getSentinelButton();
    hopperButton = summonScreen.getHopperButton();
    this.callback = callback;
  }

  // API

  public void start() {
    activateGruntButton();
    activateSentinelButton();
    activateHopperButton();
  }

  public void end() {
    gruntButton.clearListeners();
    sentinelButton.clearListeners();
    hopperButton.clearListeners();
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
        System.out.println("Grunt");
        end();
        callback.accept(new Grunt(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void activateSentinelButton() {
    sentinelButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("Sentinel");
        end();
        callback.accept(new Sentinel(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void activateHopperButton() {
    hopperButton.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        System.out.println("Hopper");
        end();
        callback.accept(new Hopper(currentPlayer()));
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

}
