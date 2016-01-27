package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

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
    Spawnable parent;
    Array<Spawnable> children;

    Vector2 position, velocity;

    public Spawnable(Vector2 position, Vector2 velocity)
    {
        this.position = position;
        this.velocity = velocity;
    }

    public final Spawnable getParent()
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
}
