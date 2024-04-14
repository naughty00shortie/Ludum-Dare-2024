package com.mygdx.game.event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.SoundUtils;
import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.players.Player;
import com.mygdx.game.ui.SpriteManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MoveEvent {

  private final Board board;

  private final Player player;

  private final Drawable possibleMove;

  private Callback callback;

  public MoveEvent(Board board, Player player) {
    this.board = board;
    this.player = player;
    possibleMove = SpriteManager.getPossibleMove();
  }

  // --- --- API --- ---

  public void start(Callback callback) {
    this.callback = callback;
    startSelectPiece();
  }

  /**
   * Cleans up all potentially marked cells.
   */
  public void end() {
    playerOccupiedCells().forEach(cell -> {
      unmarkAsSelectable(cell);
      destinationCellsFrom(cell).forEach(this::unmarkAsDestinationCell);
    });
    callback.call();
  }

  // --- --- First Stage --- ---

  private void startSelectPiece() {
    playerOccupiedCells().forEach(this::markAsSelectable);
  }

  private void endSelectPiece() {
    playerOccupiedCells().forEach(this::unmarkAsSelectable);
  }

  /**
   * Add hover effects on the Piece in the Cell.
   */
  private void markAsSelectable(Cell cell) {
    cell.getStyle().over = SpriteManager.tintForSprite(pieceFrom(cell), Color.DARK_GRAY);
    cell.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        endSelectPiece();
        startMoveOrder(cell);
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  /**
   * Remove the hover effects from the Cell.
   */
  private void unmarkAsSelectable(Cell cell) {
    cell.clearListeners();
    SpriteManager.placeSpriteOn(pieceFrom(cell), cell);
  }

  /**
   * @return Cells occupied by the Player.
   */
  private Set<Cell> playerOccupiedCells() {
    Set<Cell> occupiedCells = new HashSet<>();
    Cell[][] cells = board.getCells();
    for (Cell[] cell : cells) {
      for (Cell value : cell) {
        Boolean playerPiece = value
                .getPiece()
                .map(piece -> (piece.isPlayerPiece(player)))
                .orElse(false);
        if (playerPiece) occupiedCells.add(value);
      }
    }
    return occupiedCells;
  }

  // --- --- Second Stage --- ---

  /**
   * Highlights Cells the selected Piece can move to, and enables click events on them.
   * Clicking a highlighted cell will issue a move order to that Cell.
   * Selecting the Piece again will deselect the move order.
   *
   * @param origin Cell that the Piece is on.
   */
  private void startMoveOrder(Cell origin) {
    destinationCellsFrom(origin).forEach(destination -> markAsDestinationCell(origin, destination));
    origin.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        endMoveOrder(origin);
        startSelectPiece();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void endMoveOrder(Cell origin) {
    destinationCellsFrom(origin).forEach(this::unmarkAsDestinationCell);
  }

  /**
   * CHAIN ender.
   * Moves are pre-validated. If the Destination Cell is occupied, it is an enemy.
   */
  private void markAsDestinationCell(Cell origin, Cell destination) {
    if (destination.isOccupied()) {
      destination.getStyle().over = SpriteManager.tintForSprite(SpriteManager.getEnemy(), Color.RED);
    } else {
      SpriteManager.placeSpriteOn(possibleMove, destination);
    }
    destination.addListener(new ClickListener() {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        end();
        if (destination.isOccupied()) {
          destination.removePiece(); // Send this somewhere
          SpriteManager.removeSpriteFrom(destination);
        }
        SpriteManager.placeSpriteOn(pieceFrom(origin), destination);
        SpriteManager.removeSpriteFrom(origin);
        SoundUtils.playCrashSound();
        destination.placePiece(origin.removePiece()); // TODO Inner class shenanigans, this may not actually be moving on the Board.
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  /**
   * TODO : This assumes that every enemy is a Grunt, and Cells at this stage
   * are guaranteed to hold an enemy
   */
  private void unmarkAsDestinationCell(Cell cell) {
    cell.clearListeners();
    if (cell.isOccupied()) {
      SpriteManager.placeSpriteOn(SpriteManager.getEnemy(), cell);
    } else {
      cell.setStyle(new ImageButton.ImageButtonStyle());
    }
  }

  private Set<Cell> destinationCellsFrom(Cell cell) {
    Piece piece = pieceFrom(cell);
    return piece.moveSet(cell.getCoOrdinatePair(), board).stream()
            .map(board::getCell)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet());
  }

  // --- --- Helper --- ---

  private Piece pieceFrom(Cell cell) {
    if (cell.getPiece().isPresent()) return cell.getPiece().get();
    throw new IllegalStateException("Cell does not have a Piece!");
  }

}
