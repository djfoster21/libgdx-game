package com.cerberius.gordosandia.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.util.List;

public class EnemyLine {
    Texture texture;
    Array<Sprite> sprites;
    public static final int MAX_SPRITES = 6;
    public EnemyLine(){
        texture = new Texture(Gdx.files.internal("enemy.png"));
        sprites = new Array<>();
        Array<Integer> values = new Array<>();
        while(values.size <= MAX_SPRITES-2){
            int position = MathUtils.floor((MathUtils.random() * 7) + 1);
            if(!values.contains(position,false)){
                values.add(position);
            }
        }
        for(Integer position : new Array.ArrayIterator<>(values)){

            Sprite sprite = new Sprite(texture,64,64);
            sprite.setPosition(480 - 64*position,800);
            sprites.add(sprite);
        }
    }
    public void logic(){
        for(Sprite sprite : new Array.ArrayIterator<>(sprites)){
            sprite.setY(sprite.getY() - 200 * Gdx.graphics.getDeltaTime());
        }
    }

    public Array<Sprite> getSprites() {
        return sprites;
    }
}
