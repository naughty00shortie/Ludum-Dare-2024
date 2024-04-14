package com.mygdx.game.round;

import com.mygdx.game.movement.Board;
import com.mygdx.game.players.Player;

public enum Turn {
    PLAYER_TURN(new Player(Board.INSTANCE)),
    ENEMY_TURN(new Player(Board.INSTANCE));

    private Player player;

    Turn(Player p) {
    }

    public Player getPlayer() {
        return player;
    }
}
