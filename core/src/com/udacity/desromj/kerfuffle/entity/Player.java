package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.collectible.LargePowerCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPowerCollectible;
import com.udacity.desromj.kerfuffle.pattern.PlayerBulletPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;
import com.udacity.desromj.kerfuffle.utility.Utils;

import spine.SlotData;

/**
 * Created by Mike on 2016-01-27.
 */
public class Player extends Shooter
{
    private Assets.SpineAnimationAsset asset;

    private Sound deathSound;
    Pattern bulletPattern;

    private float magnitude;
    int lives, bombs;
    float shotPowerLevel;

    private float invincibleFor;

    public Player(Vector2 position)
    {
        super(position);

        this.asset = Assets.instance.makeAsset(this);

        this.deathSound = Gdx.audio.newSound(Gdx.files.internal(Constants.SOUND_DEATH_POOF));
        this.bulletPattern = SpawnFactory.makePattern(
                Enums.PatternType.PLAYER_BULLET_PATTERN,
                this,
                new Vector2(this.position.x, this.position.y),
                new Vector2(0, 0),
                new PatternProperties.Builder()
                        .shotDelay(1.0f / Constants.PLAYER_SHOTS_PER_SECOND)
                        .createProps()
        );

        this.magnitude = 0.0f;
        this.lives = Constants.PLAYER_STARTING_LIVES;
        this.bombs = Constants.PLAYER_STARTING_BOMBS;
        this.invincibleFor = 0.0f;

        init(position);
    }

    public void init(Vector2 spawnPoint)
    {
        this.position = new Vector2(spawnPoint.x, spawnPoint.y);
        this.bombs = Constants.PLAYER_STARTING_BOMBS;
        this.shotPowerLevel = Constants.PLAYER_SHOT_DEFAULT_POWER_LEVEL;
        this.invincibleFor = Constants.PLAYER_RESPAWN_INVINCIBILITY_SECONDS;
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

    public int getLives()
    {
        if (this.lives < 0)
            return 0;
        return this.lives;
    }

    public int getBombs()
    {
        if (this.bombs < 0)
            return 0;
        return this.bombs;
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
        this.invincibleFor -= delta;

        // Keyboard Controls
        this.magnitude = (this.isFocussed() ? Constants.PLAYER_FOCUS_SPEED : Constants.PLAYER_SPEED) * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) position.x -= magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) position.x += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) position.y += magnitude;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) position.y -= magnitude;

        // Constrain the player to the play area
        if (position.x - this.hitRadius <= 0.0f) position.x = this.hitRadius;
        if (position.x + this.hitRadius >= Constants.WORLD_WIDTH) position.x = Constants.WORLD_WIDTH - this.hitRadius;
        if (position.y - this.hitRadius <= 0.0f) position.y = this.hitRadius;
        if (position.y + this.hitRadius >= Constants.WORLD_HEIGHT) position.y = Constants.WORLD_HEIGHT - this.hitRadius;

        // Handle shooting Bullets
        if (Gdx.input.isKeyPressed(Input.Keys.Z))
            bulletPattern.shoot();

        // Handle shooting Bombs
        if (Gdx.input.isKeyPressed(Input.Keys.X))
        {
            if (this.bombs > 0)
            {
                this.bombs--;
                // TODO: Shoot bomb here
            }
        }

        /*
            Update skeleton and animation data
         */
        this.asset.skeleton.setPosition(position.x, position.y);
    }

    public final boolean isFocussed()
    {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    public final void wasHit()
    {
        this.deathSound.play(Constants.DEATH_POOF_VOLUME);
        this.dropCollectibles();
    }

    @Override
    public final void dropCollectibles()
    {
        /*
            Player drops their current power minus 1 in large powerups
                (ex: 4.7 power takes 4.7 - 1.0 rounded down (3.0) then divides by large powerup worth, rounding down)
                    (powerup worth of 0.5 will drop 6 large powerups)
            and the remaining small powerups (1.7) divided by the small powerup value
          */
        float half = Constants.COLLECTIBLE_PLAYER_SPAWN_RADIUS / 2.0f;
        float ignoreAmt = 1.0f + Constants.COLLECTIBLE_LARGE_POWER_AMOUNT;
        float workingPower = this.shotPowerLevel - ignoreAmt;

        int numLarge = (int) (workingPower / Constants.COLLECTIBLE_LARGE_POWER_AMOUNT);
        int numSmall = 0;

        // Only spawn small ones if large ones exist - to prevent death abuse
        if (numLarge > 0)
            numSmall = (int) ((workingPower - numLarge * Constants.COLLECTIBLE_LARGE_POWER_AMOUNT) / Constants.COLLECTIBLE_SMALL_POWER_AMOUNT);

        // Spawn Large Power Collectibles
        for (int i = 0; i < numLarge; i++)
        {
            Collectible coll = new LargePowerCollectible(
                    this.getPosition().x + (Constants.COLLECTIBLE_PLAYER_SPAWN_RADIUS * Utils.randomFloat() - half),
                    this.getPosition().y + (Constants.COLLECTIBLE_PLAYER_SPAWN_RADIUS * Utils.randomFloat() - half),
                    0.0f,
                    Constants.COLLECTIBLE_PLAYER_INIT_Y_VELOCITY);
            coll.setGravity(Constants.COLLECTIBLE_PLAYER_ACCEL_DUE_TO_GRAVITY);
            GameScreen.instance.addCollectible(coll);
        }

        // Spawn Small Power Collectibles
        for (int i = 0; i < numSmall; i++)
        {
            Collectible coll = new SmallPowerCollectible(
                    this.getPosition().x + (Constants.COLLECTIBLE_PLAYER_SPAWN_RADIUS * Utils.randomFloat() - half),
                    this.getPosition().y + (Constants.COLLECTIBLE_PLAYER_SPAWN_RADIUS * Utils.randomFloat() - half),
                    0.0f,
                    Constants.COLLECTIBLE_PLAYER_INIT_Y_VELOCITY);
            coll.setGravity(Constants.COLLECTIBLE_PLAYER_ACCEL_DUE_TO_GRAVITY);
            GameScreen.instance.addCollectible(coll);
        }
    }

    public boolean isAtMaxPower()
    {
        return this.shotPowerLevel >= Constants.PLAYER_SHOT_MAX_POWER_LEVEL;
    }

    public boolean isInvincible() { return this.invincibleFor > 0.0f; }

    @Override
    public void render(SpriteBatch batch)
    {
        // Tint the sprites to flash red and normal if we are invincible
        if (this.isInvincible())
        {
            Color slotColor;

            if (invincibleFor % 0.1f < 0.05f)
                slotColor = Constants.BLOOM_INVINCIBLE_COLOR;
            else
                slotColor = Constants.BLOOM_NORMAL_COLOR;

            this.asset.skeleton.getColor().set(slotColor);
        }
        else
        {
            this.asset.skeleton.getColor().set(Constants.BLOOM_NORMAL_COLOR);
        }

        // Then render the skeleton
        this.asset.render(batch);
    }

    // Hitbox Rendering - specific to the player
    public void renderShapes(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.PLAYER_HITBOX_BORDER_COLOR);
        renderer.circle(this.position.x, this.position.y, Constants.PLAYER_RADIUS * Constants.PLAYER_HITBOX_BUFFER_RATIO);

        renderer.setColor(Constants.PLAYER_HITBOX_COLOR);
        renderer.circle(this.position.x, this.position.y, (Constants.PLAYER_RADIUS - Constants.PLAYER_HITBOX_BORDER) * Constants.PLAYER_HITBOX_BUFFER_RATIO);
    }
}
