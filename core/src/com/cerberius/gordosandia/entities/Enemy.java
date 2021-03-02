package com.cerberius.gordosandia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Enemy extends Entity {
    Texture texture;

    public Enemy(final Texture texture, final int sizeX, final int sizeY, final int positionX, final int positionY) {
        this.texture = texture;
        this.sprite = new Sprite(texture, sizeX, sizeY);
        this.sprite.setPosition(positionX, positionY);
    }

    public void logic() {
        this.sprite.setY(this.sprite.getY() - 200 * Gdx.graphics.getDeltaTime());
    }

    public Sprite getSprite() {
        return this.sprite;
    }
}
