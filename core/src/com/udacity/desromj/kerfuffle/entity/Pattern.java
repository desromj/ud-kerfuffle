package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
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

    /**
     * Defaults the position and velocity to the same as the current Shooter's
     *
     * TODO: Shooter Velocity currently doesn't exist. Set to 0 instead
     *
     * @param parent
     * @param props
     */
    public Pattern(Shooter parent, PatternProperties props)
    {
        super(parent, parent.getPosition(), new Vector2());
        this.props = props;
    }

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
        if (!props.isActive())
            return;

        float delta = Gdx.graphics.getDeltaTime();

        // Check wave timing first, only if wave shooting is enabled
        if (props.getShotsPerWave() > 0)
        {
            props.setCannotWaveFor(props.getCannotWaveFor() - delta);

            // Check if there are any shots left in the current wave. If not, reset shots in the wave and start delay timer
            if (props.getShotsLeftInWave() <= 0)
            {
                props.setCannotWaveFor(props.getWaveDelay());
                props.setShotsLeftInWave(props.getShotsPerWave());
            }

            // If the delay timer is active, reset the shot timer and return without spawning anything
            if (props.getCannotWaveFor() > 0.0f)
            {
                props.setCannotShootFor(0.0f);
                return;
            }
        }

        // Then check standard shot timing within the wave
        props.setCannotShootFor(props.getCannotShootFor() - delta);

        if (props.getCannotShootFor() <= 0.0f)
        {
            props.setCannotShootFor(props.getShotDelay());
            props.setShotsLeftInWave(props.getShotsLeftInWave() - 1);

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

    /**
     * Default render method - renders debug circle area of each pattern
     *
     * @param renderer
     */
    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setAutoShapeType(true);
        renderer.setColor(Constants.PATTERN_DEBUG_COLOR);

        renderer.set(ShapeRenderer.ShapeType.Line);

        renderer.circle(
                this.position.x,
                this.position.y,
                Constants.PATTERN_DEBUG_OUTER_RADIUS,
                24);

        renderer.circle(
                this.position.x,
                this.position.y,
                Constants.PATTERN_DEBUG_INNER_RADIUS,
                8);
    }

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
