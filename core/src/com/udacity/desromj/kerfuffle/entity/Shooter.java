package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Quiv on 2016-01-31.
 */
public abstract class Shooter
{
    Vector2 position;
    float health;

    public Shooter(Vector2 position)
    {
        this(position.x, position.y);
    }

    public Shooter(float xPos, float yPos)
    {
        this.position = new Vector2(xPos, yPos);
    }

    /**
     * Each Shooter on load will need stats such as health
     */
    protected abstract void setHealth();

    public final Vector2 getPosition()
    {
        return this.position;
    }

    public final float getHealth()
    {
        return this.health;
    }

    public final boolean isDead()
    {
        return this.health <= 0.0f;
    }
}
