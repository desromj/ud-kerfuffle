package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 *
 * Pattern positions and velocities SHOULD be influenced by their parent.
 *
 * Set Properties using PatternProperties.Builder - follows the Builder design pattern
 */
public abstract class Pattern extends Spawnable
{
    protected PatternProperties props;

    public Pattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        super(parent, position, velocity);
        this.props = props;
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
        props.setCannotShootFor(props.getCannotShootFor() - Gdx.graphics.getDeltaTime());

        if (props.isActive() && props.getCannotShootFor() <= 0.0f)
        {
            props.setCannotShootFor(props.getShotDelay());
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

    public final void turnOff() { props.setActive(false); }
    public final void turnOn() { props.setActive(true); }
    public final void setActive(boolean active) { props.setActive(active); }

    public final boolean isActive() { return props.isActive(); }
    public final float getShotDelay() { return props.getShotDelay(); }
    public final float getCannotShootFor() { return props.getCannotShootFor(); }

}
