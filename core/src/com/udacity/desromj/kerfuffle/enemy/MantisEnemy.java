package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;

/**
 * Created by Mike on 2016-03-09.
 */
public class MantisEnemy extends Enemy
{

    public MantisEnemy(Vector2 position, float screenActivationHeight)
    {
        super(position, screenActivationHeight);
        // TODO: set asset loader here when sprites are ready
    }

    public MantisEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
        // TODO: set asset loader here when sprites are ready
    }

    @Override
    public void dropCollectibles()
    {
        // TODO: Implement powerup drops here, using GameScreen.instance.addCollectibles()
    }

    @Override
    public void loadDefaultPattern() {

    }

    @Override
    protected void setHealth() {

    }

    @Override
    protected void setHitRadius() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }
}
