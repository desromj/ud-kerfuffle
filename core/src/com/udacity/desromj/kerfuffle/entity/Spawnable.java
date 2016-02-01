package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.utility.Constants;

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
    Array<Spawnable> children;

    protected Vector2 position, velocity;

    public Spawnable(Shooter parent, Vector2 position, Vector2 velocity)
    {
        this.parent = parent;
        this.position = new Vector2(position.x, position.y);
        this.velocity = new Vector2(velocity.x, velocity.y);
    }

    public final Shooter getParent()
    {
        return this.parent;
    }

    public final Array<Spawnable> getChildren()
    {
        return this.children;
    }

    public final Array<Spawnable> getFamilyTree()
    {
        // TODO: Iterate over all leaves and get an array of all Spawnables connected to this object. Will be useful for updating, rendering, and disposing
        return null;
    }

    public final boolean isOffScreen()
    {
        return (this.position.x < 0.0f)
                || (this.position.x > Constants.WORLD_WIDTH)
                || (this.position.y < 0.0f)
                || (this.position.y > Constants.WORLD_HEIGHT);
    }

    /**
     * Updates the position of this Spawnable based on its velocity.
     * Can be overridden to manually control the velocity, or tie it to another object.
     *
     * @param delta
     */
    public void update(float delta)
    {
        this.position.x += this.velocity.x * delta;
        this.position.y += this.velocity.y * delta;
    }

    /**
     * Will eventually be replaced with a SpriteBatch for Pattern and Bullet graphics
     *
     * @param renderer
     */
    public abstract void render(ShapeRenderer renderer);
}
