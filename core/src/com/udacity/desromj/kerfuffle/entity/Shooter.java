package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

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
     * Each Shooter on load will need stats such as health. Use setHealth(float) for subclasses
     */
    protected abstract void setHealth();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    public final Vector2 getPosition()
    {
        return this.position;
    }

    protected final void setHealth(float health)
    {
        this.health = health;
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
