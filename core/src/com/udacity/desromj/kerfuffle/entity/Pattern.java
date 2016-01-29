package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Mike on 2016-01-27.
 *
 * Pattern positions and velocities SHOULD be influenced by their parent
 */
public abstract class Pattern extends Spawnable
{
    public Pattern(Vector2 position, Vector2 velocity) { super(position, velocity); }

    public abstract Array<Spawnable> spawnChildren(Vector2 origin);
}
