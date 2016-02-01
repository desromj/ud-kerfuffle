package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.pattern.PlayerBulletPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player extends Shooter
{
    float shotDelay, cannotShootFor;

    Pattern bulletPattern;

    public Player(Vector2 position)
    {
        super(position);
        this.shotDelay = 1.0f / Constants.PLAYER_SHOTS_PER_SECOND;
        this.cannotShootFor = 0.0f;

        this.bulletPattern = new PlayerBulletPattern(
                this,
                new Vector2(this.position.x, this.position.y),
                new Vector2(0, 0)
        );
    }

    /**
     * Player always dies in 1 hit
     */
    @Override
    protected final void setHealth()
    {
        this.health = Constants.PLAYER_HEALTH;
    }

    @Override
    public void update(float delta)
    {
        this.cannotShootFor -= delta;

        // Keyboard Controls
        float magnitude = ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) ? Constants.PLAYER_FOCUS_SPEED : Constants.PLAYER_SPEED) * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) position.y += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) position.y -= magnitude;

        // Constrain the player to the play area
        if (position.x - Constants.PLAYER_RADIUS <= 0.0f) position.x = Constants.PLAYER_RADIUS;
        if (position.x + Constants.PLAYER_RADIUS >= Constants.WORLD_WIDTH) position.x = Constants.WORLD_WIDTH - Constants.PLAYER_RADIUS;
        if (position.y - Constants.PLAYER_RADIUS <= 0.0f) position.y = Constants.PLAYER_RADIUS;
        if (position.y + Constants.PLAYER_RADIUS >= Constants.WORLD_HEIGHT) position.y = Constants.WORLD_HEIGHT - Constants.PLAYER_RADIUS;

        // Handle shooting bullets
        if (Gdx.input.isKeyPressed(Input.Keys.Z))
        {
            if (cannotShootFor <= 0.0f)
            {
                cannotShootFor = shotDelay;
                GameScreen.instance.addSpawnables(this.bulletPattern.spawnChildren());
            }
        }

        /*
            Update skeleton and animation data
         */
        Assets.instance.bloomAssets.skeleton.setPosition(position.x, position.y);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        Assets.instance.bloomAssets.render(batch);
    }
}
