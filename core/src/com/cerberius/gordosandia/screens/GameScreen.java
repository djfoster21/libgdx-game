package com.cerberius.gordosandia.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.cerberius.gordosandia.GameMain;
import com.cerberius.gordosandia.entities.EnemyLine;
import com.cerberius.gordosandia.entities.Player;

import java.sql.Time;

public class GameScreen extends BaseScreen {

    long gameRunTime = 0;
    long lastFrameTime;
    Player player;
    Array<EnemyLine> enemies;
    long lastSpawn;
    Array<Sound> sounds;

    public GameScreen(GameMain gameMain) {
        super(gameMain);
        lastFrameTime = TimeUtils.millis();
        player = new Player();
        enemies = new Array<>();
        enemies.add(new EnemyLine());
        lastSpawn = 0;
        sounds = new Array<>();
        sounds.add(
                Gdx.audio.newSound(Gdx.files.internal("sound1.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sound2.mp3")),
                Gdx.audio.newSound(Gdx.files.internal("sound3.mp3"))
        );
    }

    @Override
    public void render(float delta) {
        gameRunTime += TimeUtils.timeSinceMillis(lastFrameTime);
        lastFrameTime = TimeUtils.millis();
        ScreenUtils.clear(100,100,25,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch,"Seconds: "+getRuntimeInSeconds(),0,12);
        for(EnemyLine enemyLine : new Array.ArrayIterator<>(enemies)){
            Array<Sprite> sprites = enemyLine.getSprites();
            for(Sprite sprite : new Array.ArrayIterator<>(sprites)){
                sprite.draw(game.batch);
            }
        }
        player.getSprite().draw(game.batch,1);
        game.batch.end();
        player.logic(camera);
        for(EnemyLine enemyLine : new Array.ArrayIterator<>(enemies)){
            enemyLine.logic();
            Array<Sprite> sprites = enemyLine.getSprites();
            for(Sprite sprite : new Array.ArrayIterator<>(sprites)){
                if(player.getSprite().getBoundingRectangle().overlaps(sprite.getBoundingRectangle())){
                    sprites.removeValue(sprite,true);
                    int soundIndex = MathUtils.ceil(MathUtils.random() * 3 ) -1 ;
                    sounds.get(soundIndex).play();
                }
            }
            if(enemyLine.getSprites().get(0).getX() == 0){
                enemies.removeValue(enemyLine,true);
            }
        }
        spawnEnemy();
        super.render(delta);
    }
    public long getRuntimeInSeconds()
    {
        return  gameRunTime != 0 ? (long) MathUtils.ceil( gameRunTime / 1000  ) : 0;
    }
    public void spawnEnemy(){
        if(getRuntimeInSeconds() % 2 == 0 && getRuntimeInSeconds() != lastSpawn){
            enemies.add(new EnemyLine());
            lastSpawn = getRuntimeInSeconds();
        }
    }
}
