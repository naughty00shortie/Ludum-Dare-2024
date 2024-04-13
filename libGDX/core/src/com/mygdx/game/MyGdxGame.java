package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	ChessBoardRenderer chessBoardRenderer;
	
	@Override
	public void create () {
		chessBoardRenderer = new ChessBoardRenderer();
		chessBoardRenderer.create();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		chessBoardRenderer.render();
	}
	
	@Override
	public void dispose () {
		chessBoardRenderer.dispose();
	}
}
