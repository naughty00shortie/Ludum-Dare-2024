package com.mygdx.game.round;

import com.mygdx.game.players.Player;

import java.util.Random;

public class RoundManager {

  private Turn currentTurn = Turn.ENEMY_TURN;

  private static final int MANA_PER_TURN_INCREASE = 1;

  private volatile boolean pause = true;

  public static final RoundManager INSTANCE = new RoundManager();

  public RoundManager() {
  }

  public void run() {
    new Thread(() -> {
      while (true) {
        INSTANCE.startTurn();
      }
    }).start();
  }

  private void startTurn() {
    pause();
    while (pause) ;
  }

  public void executeMove(Move m) {
    m.execute();
    unPause();
  }

  private synchronized void pause() {
    pause = true;
  }

  private synchronized void unPause() {
    pause = false;
    INSTANCE.endTurn();
  }

  private synchronized void endTurn() {
    whosTurn().increaseMannaBy(MANA_PER_TURN_INCREASE);
    currentTurn = currentTurn == Turn.ENEMY_TURN ? Turn.PLAYER_TURN : Turn.ENEMY_TURN;
  }

  public synchronized Player whosTurn() {
    return null;
  }
}
