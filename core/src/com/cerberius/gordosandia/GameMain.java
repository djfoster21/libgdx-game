package com.cerberius.gordosandia;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cerberius.gordosandia.screens.GameScreen;

public class GameMain extends com.badlogic.gdx.Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;

    @Override
    public void create() {
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.shapeRenderer = new ShapeRenderer();
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
		this.batch.dispose();
		this.font.dispose();
    }
}
