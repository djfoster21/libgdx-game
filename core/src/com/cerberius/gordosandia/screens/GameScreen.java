package com.cerberius.gordosandia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.cerberius.gordosandia.GameMain;
import com.cerberius.gordosandia.entities.Enemy;
import com.cerberius.gordosandia.entities.EnemyLine;
import com.cerberius.gordosandia.entities.Player;


public class GameScreen extends BaseScreen {

    long gameRunTime = 0;
    long lastFrameTime;
    Player player;
    Array<EnemyLine> enemies;
    long lastSpawn;
    Texture background;
    int bgPosition;


    public GameScreen(final GameMain gameMain) {
        super(gameMain);
        this.lastFrameTime = TimeUtils.millis();
        this.player = new Player();
        this.enemies = new Array<>();
        this.enemies.add(new EnemyLine());
        this.lastSpawn = 0;
        this.background = new Texture(Gdx.files.internal("background2.jpg"));
        this.background.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.bgPosition = 0;
    }

    @Override
    public void render(final float delta) {
        this.gameRunTime += TimeUtils.timeSinceMillis(this.lastFrameTime);
        this.lastFrameTime = TimeUtils.millis();
        ScreenUtils.clear(Color.GRAY);

        this.camera.update();

        this.processSpriteBatch();
        this.processShapeBatch();
        this.player.logic(this.camera);
        for (final EnemyLine enemyLine : new Array.ArrayIterator<>(this.enemies)) {
            enemyLine.logic();
            final Array<Enemy> enemies = enemyLine.getEnemies();
            for (final Enemy enemy : new Array.ArrayIterator<>(enemies)) {
                if (this.player.checkCollision(enemy.getSprite())) {
                    enemyLine.removeEnemy(enemy);
                }
                if (enemy.getSprite().getY() == 0) {
                    this.enemies.removeValue(enemyLine, true);
                }
            }
        }
        this.spawnEnemy();
        super.render(delta);
    }

    private void processShapeBatch() {
        this.game.shapeRenderer.setProjectionMatrix(this.camera.combined);
        this.game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        this.game.shapeRenderer.setColor(Color.WHITE);
        this.game.shapeRenderer.rect(0, Gdx.graphics.getHeight() - 50, Gdx.graphics.getWidth(), 50);
        this.game.shapeRenderer.end();
        this.game.shapeRenderer.flush();
    }

    private void processSpriteBatch() {

        this.game.batch.setProjectionMatrix(this.camera.combined);
        this.game.batch.begin();
        this.drawBackground();
        this.game.font.draw(this.game.batch, "Seconds: " + this.getRuntimeInSeconds(), 0, 12);
        for (final EnemyLine enemyLine : new Array.ArrayIterator<>(this.enemies)) {
            final Array<Enemy> enemies = enemyLine.getEnemies();
            for (final Enemy enemy : new Array.ArrayIterator<>(enemies)) {
                enemy.getSprite().draw(this.game.batch, 1);
            }
        }
        this.player.getSprite().draw(this.game.batch, 1);
        this.game.batch.end();
        this.game.batch.flush();
    }

    private void drawBackground() {
        this.game.batch.draw(
                this.background,
                0,
                0,
                0,
                this.bgPosition -= 200 * Gdx.graphics.getDeltaTime(),
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );
    }

    public long getRuntimeInSeconds() {
        return this.gameRunTime != 0 ? (long) MathUtils.ceil(this.gameRunTime / 1000) : 0;
    }

    public void spawnEnemy() {
        if (this.getRuntimeInSeconds() % 2 == 0 && this.getRuntimeInSeconds() != this.lastSpawn) {
            this.enemies.add(new EnemyLine());
            this.lastSpawn = this.getRuntimeInSeconds();
        }
    }
}
