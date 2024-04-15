package com.mygdx.game.round;

import com.mygdx.game.players.Player;

public class RoundManager {

  private Turn currentTurn = Turn.ENEMY_TURN;

  private static final int MANA_PER_TURN_INCREASE = 1;

  private volatile boolean pause = true;

  public static final RoundManager INSTANCE = new RoundManager();

  public Turn getCurrentTurn() {
    return currentTurn;
  }

  public void run() {
    if (! pause) INSTANCE.startTurn();
  }

  public void executeMove(Runnable m) {
    m.run();
    unPause();
  }

  public Player whoseTurn() {
    return currentTurn.getPlayer();
  }

  private synchronized void pause() {
    pause = true;
  }

  private synchronized void unPause() {
    INSTANCE.endTurn();
    pause = false;
  }

  private void startTurn() {
    pause();
  }

  private synchronized void endTurn() {
    whoseTurn().increaseMannaBy(MANA_PER_TURN_INCREASE);
    currentTurn = currentTurn == Turn.ENEMY_TURN ? Turn.PLAYER_TURN : Turn.ENEMY_TURN;
  }
}
