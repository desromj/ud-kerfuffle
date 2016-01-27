package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2016-01-27.
 */
public abstract class Enemy
{
    Vector2 position;

    public Enemy(Vector2 position)
    {
        this.position = position;
    }
}
