package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.pattern.PlayerBulletPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player extends Shooter
{
    Pattern bulletPattern;

    public Player(Vector2 position)
    {
        super(position);

        this.bulletPattern = new PlayerBulletPattern(
                this,
                new Vector2(this.position.x, this.position.y),
                new Vector2(0, 0),
                new PatternProperties.Builder()
                        .shotDelay(1.0f / Constants.PLAYER_SHOTS_PER_SECOND)
                        .createProps()
        );

        init(position);
    }

    public void init(Vector2 spawnPoint)
    {
        this.position = new Vector2(spawnPoint.x, spawnPoint.y);
    }

    /**
     * Player always dies in 1 hit
     */
    @Override
    protected final void setHealth() {
        this.health = Constants.PLAYER_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.PLAYER_RADIUS;
    }

    @Override
    public void update(float delta)
    {
        // Keyboard Controls
        float magnitude = ((Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) ? Constants.PLAYER_FOCUS_SPEED : Constants.PLAYER_SPEED) * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) position.y += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) position.y -= magnitude;

        // Constrain the player to the play area
        if (position.x - this.hitRadius <= 0.0f) position.x = this.hitRadius;
        if (position.x + this.hitRadius >= Constants.WORLD_WIDTH) position.x = Constants.WORLD_WIDTH - this.hitRadius;
        if (position.y - this.hitRadius <= 0.0f) position.y = this.hitRadius;
        if (position.y + this.hitRadius >= Constants.WORLD_HEIGHT) position.y = Constants.WORLD_HEIGHT - this.hitRadius;

        // Handle shooting bullets
        if (Gdx.input.isKeyPressed(Input.Keys.Z))
            bulletPattern.shoot();

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
