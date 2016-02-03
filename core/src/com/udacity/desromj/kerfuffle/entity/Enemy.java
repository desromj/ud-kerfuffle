package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 2016-01-27.
 */
public abstract class Enemy extends Shooter
{
    protected Pattern [] patterns;

    public Enemy(Vector2 position)
    {
        super(position);
    }

    public Enemy(Vector2 position, Pattern [] patterns)
    {
        super(position);
        setPatterns(patterns);
    }

    public final void setPatterns(Pattern [] patterns)
    {
        this.patterns = patterns;
    }

}
