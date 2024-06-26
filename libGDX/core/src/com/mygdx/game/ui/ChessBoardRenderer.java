package com.mygdx.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.event.MoveEvent;
import com.mygdx.game.event.SummonEvent;
import com.mygdx.game.movement.Board;
import com.mygdx.game.movement.Cell;
import com.mygdx.game.movement.CoOrdinatePair;
import com.mygdx.game.movement.pieces.Grunt;
import com.mygdx.game.movement.pieces.Piece;
import com.mygdx.game.movement.pieces.Sentinel;
import com.mygdx.game.players.Player;

import java.awt.*;

public class ChessBoardRenderer extends ApplicationAdapter {

  public static final int BOARD_SIZE = 8;

  public static final int SIZE = 100;

  public static final int OFFSET = 100;

  private final Board board = Board.INSTANCE;

  public boolean renderFriendlySummoningPosition = true;

  public boolean renderEnemySummoningPosition = false;

  private ShapeRenderer shapeRenderer;

  private Stage stage;

  @Override
  public void create() {
    stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    shapeRenderer = new ShapeRenderer();
    Texture whiteTexture = new Texture(Gdx.files.internal("WHITE.png"));
    TextureRegionDrawable whiteDrawable = new TextureRegionDrawable(new TextureRegion(whiteTexture));
    Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        cells[i][j] = new Cell(i, j, OFFSET, SIZE);
        stage.addActor(cells[i][j]);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        cells[i][j].setStyle(buttonStyle);
        cells[i][j].setBounds((i + 1) * OFFSET, (j + 1) * OFFSET, SIZE, SIZE);
        buttonStyle.up = whiteDrawable;
      }
    }
    board.setCells(cells);
    //    demoMoveEvent();  // TODO Uncomment for a Move Demo!
    //    demoSummonEvent(); // TODO Uncomment for a Summon Demo!
  }

  @Override
  public void render() {

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        renderSquare(i, j);
      }
    }
    shapeRenderer.end();
    stage.act();
    stage.draw();
  }

  private void renderSquare(int i, int j) {
    boolean isWhiteSquare = (i + j) % 2 == 0;
    if (isWhiteSquare) {
      shapeRenderer.setColor(1, 1, 1, 1);
    } else {
      setSummoningPositionBlack();
    }

    if (j == 0 && renderFriendlySummoningPosition) {
      if (isWhiteSquare) {
        setFriendlySummoningPositionLight();
      } else {
        setFriendlySummoningPositionBlack();
      }
    }
    if (j == BOARD_SIZE - 1 && renderEnemySummoningPosition) {
      if (isWhiteSquare) {
        setEnemySummoningPositionWhite();
      } else {
        setEnemySummoningPositionBlack();
      }
    }
    Rectangle rect = board.getCells()[i][j].getRectangle();
    shapeRenderer.rect(rect.x + 100, rect.y + 100, rect.width, rect.height);
    if (board.getCells()[i][j].isSelected) {
      shapeRenderer.setColor(0, 0.99f, 0.98f, 0.1f);
      shapeRenderer.circle(rect.x + 150, rect.y + 150, rect.width / 4);
    }
  }


  private void setEnemySummoningPositionBlack() {
    shapeRenderer.setColor(0.5f, 0.1f, 0.1f, 1);
  }

  private void setFriendlySummoningPositionBlack() {
    shapeRenderer.setColor(0, 0, 0.5F, 1);
  }

  private void setSummoningPositionBlack() {
    shapeRenderer.setColor(0, 0, 0, 1);
  }

  private void setEnemySummoningPositionWhite() {
    shapeRenderer.setColor(1, 0.5F, 0.5F, 1);
  }

  private void setFriendlySummoningPositionLight() {
    shapeRenderer.setColor(0.5F, 0.69F, 1F, 1);
  }

  @Override
  public void dispose() {
    shapeRenderer.dispose();
  }

  // --- --- Event Demos (can be removed, just to illustrate functionality) --- ---

  private void demoSummonEvent() {
    // Demo an allied piece 'blocking' a summon square.
    Player player = new Player(board);
    Grunt piece = new Grunt(player);
    Cell cell = board.getCell(new CoOrdinatePair(0, 0)).get();
    cell.placePiece(piece);
    SpriteManager.placeSpriteOn(piece, cell);
    // Trigger the Summon Event!
    new SummonEvent(board, new Sentinel(player)).start();
  }

  private void demoMoveEvent() {
    // Place a Piece
    Player player = new Player(board);
    Piece piece = new Sentinel(player);
    Cell cell = board.getCell(new CoOrdinatePair(0, 0)).get();
    cell.placePiece(piece);
    SpriteManager.placeSpriteOn(piece, cell);
    // Place an allied Piece
    Piece ally = new Grunt(player);
    Cell allyCell = board.getCell(new CoOrdinatePair(1, 0)).get();
    allyCell.placePiece(ally);
    SpriteManager.placeSpriteOn(ally, allyCell);
    //    Place enemy piece
    Player enemyPlayer = new Player(board);
    Piece enemyPiece = new Sentinel(enemyPlayer);
    Cell enemyCell = board.getCell(new CoOrdinatePair(1, 1)).get();
    enemyCell.placePiece(enemyPiece);
    SpriteManager.placeSpriteOn(SpriteManager.getEnemy(), enemyCell);
    // Start move event for the player
    new MoveEvent(board, player).start();
  }

  public Stage getStage() {
    return stage;
  }
}




