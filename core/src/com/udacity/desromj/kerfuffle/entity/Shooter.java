package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */
public abstract class Shooter
{
    Vector2 position;
    protected float health, hitRadius;
    protected boolean shooting;

    public Shooter(Vector2 position)
    {
        this(position.x, position.y);
    }

    public Shooter(float xPos, float yPos)
    {
        this.position = new Vector2(xPos, yPos);
        this.setHealth();
        this.setHitRadius();
        this.shooting = false;
    }

    /**
     * Each Shooter on load will need stats such as health. Use setHealth(float) for subclasses
     */
    protected abstract void setHealth();
    protected abstract void setHitRadius();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    /*
    Functional Methods
     */
    public final boolean isOnScreen()
    {
        return (this.position.x >= 0.0f - this.hitRadius) && (this.position.x <= Constants.WORLD_WIDTH + this.hitRadius)
                && (this.position.y >= 0.0f - this.hitRadius) && (this.position.y <= Constants.WORLD_HEIGHT + this.hitRadius);
    }

    public final boolean isShooting()
    {
        return this.shooting;
    }

    /**
     * Only dispose Enemies who have been activated (are shooting) and are offscreen. Or are dead
      */
    public final boolean enemyShouldBeDisposed()
    {
        return
                (this instanceof Enemy) &&
                    (this.isDead() ||
                            ((this.isShooting()) && (!this.isOnScreen())));
    }

    /*
    Misc Getters and Setters
     */

    public final Vector2 getPosition() { return this.position; }
    public final float getHealth()
    {
        return this.health;
    }
    public final float getHitRadius() { return this.hitRadius; }

    /** Overridden by Bosses, since they deal with multiple Phases */
    public boolean isDead()
    {
        return this.health <= 0.0f;
    }

    public final void reduceHealth(Bullet hitBy)
    {
        this.health -= hitBy.getDamage();
    }
}
