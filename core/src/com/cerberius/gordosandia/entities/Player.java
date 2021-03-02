package com.cerberius.gordosandia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    Array<Sound> sounds;
    Sprite sprite;
    Texture texture;

    public Player() {
        texture = new Texture(Gdx.files.internal("playerSprite.png"));
        this.sprite = new Sprite(texture,64,64);

        sprite.setSize(64,64);
        sprite.setPosition(240,0);


        sprite.setTexture(texture);
    }

    public void logic(Camera camera){
        if(Gdx.input.isTouched()){
            Vector3 touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            sprite.setX(touchPosition.x);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}
