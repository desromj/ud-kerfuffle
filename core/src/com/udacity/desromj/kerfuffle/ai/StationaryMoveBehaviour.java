package com.udacity.desromj.kerfuffle.ai;

import com.udacity.desromj.kerfuffle.entity.Shooter;

/**
 * Created by Quiv on 01-07-2016.
 */
public class StationaryMoveBehaviour extends MoveBehaviour
{
    public StationaryMoveBehaviour(Shooter parent, float speed)
    {
        super(parent, speed);
    }

    @Override
    public void activate() {}

    // Don't move at all once on screen
    @Override
    public void move(float delta) {}
}
