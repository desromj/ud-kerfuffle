package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.pattern.FlyBulletPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */

public class FlyEnemy extends Enemy
{
    // TODO: Use ShapeRenderer until graphics can be made
    ShapeRenderer renderer;

    float shotDelay, cannotShootFor;

    Pattern bulletPattern;

    public FlyEnemy(Vector2 position)
    {
        super(position);
        renderer = new ShapeRenderer();
        this.shotDelay = 1.0f / Constants.ENEMY_FLY_SHOTS_PER_SECOND;
        cannotShootFor = 0.0f;

        this.bulletPattern = new FlyBulletPattern(
                this,
                new Vector2(this.getPosition().x, this.getPosition().y),
                new Vector2(0, 0)
        );
    }

    @Override
    protected void setHealth() {
        this.setHealth(Constants.ENEMY_FLY_HEALTH);
    }

    @Override
    public void update(float delta)
    {
        this.cannotShootFor -= delta;

        if (cannotShootFor <= 0.0f)
        {
            cannotShootFor = shotDelay;
            GameScreen.instance.addSpawnables(this.bulletPattern.spawnChildren());
        }
    }

    @Override
    public void render(SpriteBatch batch)
    {
        renderer.setProjectionMatrix(GameScreen.instance.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.ENEMY_FLY_COLOUR);
        renderer.circle(this.getPosition().x, this.getPosition().y, Constants.ENEMY_FLY_RADIUS);
        renderer.end();
    }
}
