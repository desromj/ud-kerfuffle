package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2016-01-27.
 *
 * Bullet positions and velocities should NOT be influenced by their parent
 */
public class Bullet extends Spawnable
{
    public Bullet(Vector2 position, Vector2 velocity) { super(position, velocity); }


}
