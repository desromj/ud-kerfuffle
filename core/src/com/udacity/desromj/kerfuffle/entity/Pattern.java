package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

/**
 * Created by Mike on 2016-01-27.
 *
 * Pattern positions and velocities SHOULD be influenced by their parent
 */
public abstract class Pattern extends Spawnable
{
    /** true means the shoot() method will shoot its Spawnables (Bullets and Patterns) */
    boolean active;

    /** Time (in seconds) to wait between waves of shooting children Spawnable objects */
    float shotDelay;

    /** Internal timer used in conjuction with shotDelay */
    float cannotShootFor;

    public Pattern(Shooter parent, Vector2 position, Vector2 velocity, float shotDelay)
    {
        this(parent, position, velocity, shotDelay, 0.0f);
    }

    public Pattern(Shooter parent, Vector2 position, Vector2 velocity, float shotDelay, float cannotShootFor)
    {
        super(parent, position, velocity);
        this.shotDelay = shotDelay;
        this.cannotShootFor = cannotShootFor;
        this.active = true;
    }

    /**
     * Attempts to shoot the Pattern. Takes into account the last time since being shot, so
     * this method can be continuously called from an update() method without fear of
     * shooting thousands of times at once.
     *
     * If you would like to shoot regardless of the shot rate, use shootNow()
     */
    public final void shoot()
    {
        this.cannotShootFor -= Gdx.graphics.getDeltaTime();

        if (this.active && cannotShootFor <= 0.0f)
        {
            cannotShootFor = shotDelay;
            GameScreen.instance.addSpawnables(this.spawnChildren());
        }
    }

    /**
     * Shoots the Pattern NOW, regardless of whether or not it is currently active.
     * Does not affect internal timing shot clock
     */
    public final void shootNow()
    {
        GameScreen.instance.addSpawnables(this.spawnChildren());
    }

    /**
     * Each pattern will need to know how to spawn its children, whether those
     * are Bullet objects or further Pattern children
     *
     * @return All children this Pattern should spawn when it shoots
     */
    protected abstract Array<Spawnable> spawnChildren();

    /*
    Getters and Setters
     */

    public final void turnOff() { this.active = false; }
    public final void turnOn() { this.active = true; }
    public final void setActive(boolean active) { this.active = active; }

    public final boolean isActive() { return this.active; }
    public final float getShotDelay() { return this.shotDelay; }
    public final float getCannotShootFor() { return this.cannotShootFor; }

}
