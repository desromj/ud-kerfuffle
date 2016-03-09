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

        // Default Pattern for MiteEnemy if nothing is passed
        this.setPatterns(
                new Pattern[]{
                        new SpiralPattern(
                                this,
                                new PatternProperties.Builder()
                                        .shotDelay(0.03f)
                                        .targetted(false)
                                        .armAngleOffsetDegrees(12.0f)
                                        .speed(180.0f)
                                        .shotsPerWave(150)
                                        .waveDelay(2.5f)
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
                                        .waveDelay(1.0f)
                                        .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                        .createProps()
                        )
                }
        );
    }

    public MiteEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
        assets = Assets.instance.makeMiteAssets();
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
    }

    @Override
    public void render(SpriteBatch batch)
    {
        this.assets.render(batch);
    }
}
