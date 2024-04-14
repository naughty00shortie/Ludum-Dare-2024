/**
 * Events happen on the Board.
 * Currently, there are two possible events, a Summon, and a Move.
 * These correspond to the action the Player takes on their turn.
 * The classes in this package bridge the 'backend' to the 'frontend',
 * or the domain model to the rendered Board.
 */
package com.mygdx.game.event;

/*

Try this in ChessBoardRenderer#create as the last line

new SummonEvent(board, new Sentinel(new Player(board))).start()

*/