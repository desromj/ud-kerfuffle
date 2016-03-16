package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Quiv on 2016-01-31.
 */

public class MiteEnemy extends Enemy
{
    Assets.MiteAssets assets;

    /**
     * Loads a default set of Patterns. Recommended to use the other constructor
     * TODO: remove this or ensure there is a way to set Patterns through the level designer
     *
     * @param position
     * @param screenActivationHeight
     */
    public MiteEnemy(Vector2 position, float screenActivationHeight)
    {
        super(position, screenActivationHeight);
        assets = Assets.instance.makeMiteAssets();
    }

    public MiteEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
        assets = Assets.instance.makeMiteAssets();
    }

    /*
        TODO: implement movement behaviour here, when it is better understood
     */
    public void move(float delta)
    {
        // this.getPosition().x += Constants.ENEMY_WORLD_SCROLL_SPEED * delta;
    }

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_MITE_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_MITE_RADIUS;
    }

    public void update(float delta)
    {
        super.update(delta);

        this.assets.skeleton.setPosition(this.getPosition().x, this.getPosition().y);

        if (this.isShooting())
            move(delta);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        this.assets.render(batch);
    }

    @Override
    public void loadDefaultPattern()
    {
        this.setPatterns(
                new Pattern[]{
                        new SpiralPattern(
                                this,
                                new PatternProperties.Builder()
                                        .shotDelay(0.004f)
                                        .targetted(true)
                                        .armAngleOffsetDegrees(16.0f)
                                        .speed(90.0f)
                                        .shotsPerWave(400)
                                        .waveDelay(1.0f)
                                        .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                        .createProps()
                        ),
                        new SpiralPattern(
                                this,
                                new PatternProperties.Builder()
                                        .shotDelay(0.01f)
                                        .targetted(false)
                                        .armAngleOffsetDegrees(-7.5f)
                                        .speed(140.0f)
                                        .shotsPerWave(160)
                                        .waveDelay(2.5f)
                                        .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                        .createProps()
                        )
                }
        );
    }
}
