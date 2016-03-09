package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-03-09.
 */
public class FlyEnemy extends Enemy
{
    public FlyEnemy(Vector2 position, float screenActivationHeight)
    {
        this(position, screenActivationHeight, new Pattern[]{});
    }

    public FlyEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
    }

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_FLY_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_FLY_RADIUS;
    }

    @Override
    public void render(SpriteBatch batch)
    {

    }

    @Override
    public void loadDefaultPattern()
    {

    }
}
