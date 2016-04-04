package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.pattern.PlayerBulletPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player extends Shooter
{
    Pattern bulletPattern;
    int lives, bombs;
    float shotPowerLevel;

    public Player(Vector2 position)
    {
        super(position);

        this.bulletPattern = SpawnFactory.makePattern(
                Enums.PatternType.PLAYER_BULLET_PATTERN,
                this,
                new Vector2(this.position.x, this.position.y),
                new Vector2(0, 0),
                new PatternProperties.Builder()
                        .shotDelay(1.0f / Constants.PLAYER_SHOTS_PER_SECOND)
                        .createProps()
        );

        this.lives = Constants.PLAYER_STARTING_LIVES;

        init(position);
    }

    public void init(Vector2 spawnPoint)
    {
        this.position = new Vector2(spawnPoint.x, spawnPoint.y);
        this.bombs = Constants.PLAYER_STARTING_BOMBS;
        this.shotPowerLevel = Constants.PLAYER_SHOT_DEFAULT_POWER_LEVEL;
    }

    /**
     * Exact same as init(), except reduces number of lives by 1 also
     * @param spawnPoint
     */
    public void respawn(Vector2 spawnPoint)
    {
        this.lives--;
        init(spawnPoint);
    }

    public final boolean isOutOfLives()
    {
        return this.lives <= 0;
    }

    public final float getShotPowerLevel() { return this.shotPowerLevel; };

    public final void addShotPower(float power)
    {
        this.setShotPower(this.shotPowerLevel + power);
    }

    /**
     * Dangerous method, used for initialization. Consider using addShotPower instead
     * @param newLevel
     */
    public final void setShotPower(float newLevel)
    {
        this.shotPowerLevel = MathUtils.clamp(newLevel, Constants.PLAYER_SHOT_DEFAULT_POWER_LEVEL, Constants.PLAYER_SHOT_MAX_POWER_LEVEL);
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
