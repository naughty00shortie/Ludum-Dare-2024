package com.mygdx.game.round;

import com.mygdx.game.players.Player;
public class RoundManager {

  private Turn currentTurn = Turn.ENEMY_TURN;

  private static final int MANA_PER_TURN_INCREASE = 1;

  private volatile boolean pause = true;
  private volatile boolean run = true;

  public static final RoundManager INSTANCE = new RoundManager();

    public Turn getCurrentTurn() {
        return currentTurn;
    }

  public void run() {
    new Thread(() -> {
      while (run) {
        INSTANCE.startTurn();
      }
    });
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
    whoseTurn().increaseMannaBy(MANA_PER_TURN_INCREASE);
    currentTurn = currentTurn == Turn.ENEMY_TURN ? Turn.PLAYER_TURN : Turn.ENEMY_TURN;
  }

  public synchronized Player whoseTurn() {
    return currentTurn.getPlayer();
  }

  public void stop() {
    run = false;
  }


}
