package com.mygdx.game.round;

import static com.mygdx.game.round.Turn.*;

public class RoundManager {

    private Turn currentTurn = ENEMY_TURN;

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public void startTurn() {
        // calculate mana
        // present menu ui if player turn
        // send output to user depending on turn stuff
    }

    public void executeMove(Move m) {
        m.execute();
        // check if valid move wrt mana
    }

    public void endTurn() {
        currentTurn = currentTurn == ENEMY_TURN ? PLAYER_TURN : ENEMY_TURN;
    }
}
