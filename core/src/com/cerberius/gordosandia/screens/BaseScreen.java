package com.cerberius.gordosandia.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.cerberius.gordosandia.GameMain;

public class BaseScreen implements Screen {
    public static final int VIEWPORT_WIDTH = 448;
    public static final int VIEWPORT_HEIGHT = 800;
    final GameMain game;
    OrthographicCamera camera;

    BaseScreen(final GameMain game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {

    }

    @Override
    public void resize(final int width, final int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
