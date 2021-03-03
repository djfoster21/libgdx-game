package com.cerberius.gordosandia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    Array<Sound> sounds;
    Sprite sprite;
    Texture texture;
    int stressLevel;
    public static int MAX_STRESS_LEVEL = 100;

    public Player() {
        this.texture = new Texture(Gdx.files.internal("playerSprite.png"));
        this.sprite = new Sprite(this.texture, 64, 64);
        this.sprite.setPosition(240, 0);

        this.sounds = new Array<>();
        this.sounds.add(
                Gdx.audio.newSound(Gdx.files.internal("sound1.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sound2.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sound3.mp3"))
        );
    }

    public void logic(final Camera camera) {
        if (Gdx.input.isTouched()) {
            final Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            this.sprite.setX(touchPosition.x);
        }
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public boolean checkCollision(final Sprite enemySprite) {
        if (this.getSprite().getBoundingRectangle().overlaps(enemySprite.getBoundingRectangle())) {
            final int soundIndex = MathUtils.ceil(MathUtils.random() * 3) - 1;
            for (final Sound sound : new Array.ArrayIterator<>(this.sounds)) {
                sound.stop();
            }
            this.stressLevel = this.stressLevel >= MAX_STRESS_LEVEL ? MAX_STRESS_LEVEL : this.stressLevel + 10;
            this.sounds.get(soundIndex).play(0.5f);
            return true;
        }
        return false;
    }
}
