package com.udacity.desromj.kerfuffle.ai;

import com.udacity.desromj.kerfuffle.entity.Shooter;

/**
 * Created by Quiv on 01-07-2016.
 */
public abstract class MoveBehaviour
{
    protected Shooter parent;
    protected float speed;

    public MoveBehaviour(Shooter parent, float speed)
    {
        this.parent = parent;
        this.speed = speed;
    }

    public abstract void move(float delta);
}
