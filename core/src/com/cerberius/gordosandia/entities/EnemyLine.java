package com.cerberius.gordosandia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class EnemyLine {

    public static final String TEXTURE = "enemy.png";
    public static final int SPRITE_WIDTH = 64;
    public static final int SPRITE_HEIGHT = 64;
    Texture texture;
    Array<Enemy> enemies;
    int maxSprites;

    public EnemyLine() {
        this.texture = new Texture(Gdx.files.internal(TEXTURE));
        this.enemies = new Array<>();
        this.maxSprites = MathUtils.floor(Gdx.graphics.getWidth() / SPRITE_WIDTH);
        final int holePosition = MathUtils.floor(MathUtils.random() * this.maxSprites);
        final int neighbourPosition = holePosition == this.maxSprites ? holePosition - 1 : holePosition + 1;

        for (int i = 0; i <= this.maxSprites; i++) {
            if (i == holePosition || i == neighbourPosition) {
                continue;
            }
            this.enemies.add(
                    new Enemy(this.texture, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_WIDTH * i, Gdx.graphics.getHeight()));
        }
    }

    public void logic() {
        for (final Enemy enemy : new Array.ArrayIterator<>(this.enemies)) {
            enemy.logic();
        }
    }

    public Array<Enemy> getEnemies() {
        return this.enemies;
    }


    public void removeEnemy(final Enemy enemy) {
        this.enemies.removeValue(enemy, true);
    }
}
