package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Mike on 2016-01-27.
 *
 * Spawnables can be either Bullets or Patterns
 *
 * Spawnables can be spawned at a certain position and follow a certain velocity
 *
 * Spawnables can be nested and combined for some intricate patterns
 *
 * Follows the Composite Design Pattern
 */
public abstract class Spawnable
{
    Shooter parent;
    protected Vector2 position, velocity;
    protected float rotationRads;

    public Spawnable(Shooter parent, Vector2 position, Vector2 velocity)
    {
        this.parent = parent;
        this.position = new Vector2(position.x, position.y);
        this.velocity = new Vector2(velocity.x, velocity.y);
        this.rotationRads = 0.0f;
    }

    public final Shooter getParent()
    {
        return this.parent;
    }
    public boolean isOffScreen()
    {
        return !Utils.isOnScreen(this.position, 0.0f);
    }
    public final Vector2 getPosition() { return this.position; }

    /**
     * Updates the position of this Spawnable based on its velocity.
     * Can be overridden to manually control the velocity, or tie it to another object.
     *
     * @param delta
     */
    public void update(float delta)
    {
        this.position.mulAdd(this.velocity, delta);
        this.rotationRads = this.velocity.angleRad();
    }

    /**
     * Will eventually be replaced with a SpriteBatch for Pattern and Bullet graphics
     *
     * @param renderer
     */
    public abstract void render(ShapeRenderer renderer);
}
