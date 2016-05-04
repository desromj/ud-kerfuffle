package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Mike on 2016-01-27.
 *
 * Bullet positions and velocities should NOT be influenced by their parent
 */
public abstract class Bullet extends Spawnable
{
    protected float shotRadius, damage;

    public Bullet(Shooter parent, Vector2 position, Vector2 velocity)
    {
        super(parent, position, velocity);
        setShotRadius();
        setDamage();
    }

    /** Set radius for hitBoxes for each Bullet */
    protected abstract void setShotRadius();
    protected abstract void setDamage();

    public final boolean isColliding(Shooter shooter)
    {
        float dist = Vector2.dst(this.position.x, this.position.y, shooter.getPosition().x, shooter.getPosition().y);

        if (dist < this.shotRadius + shooter.hitRadius)
            return true;

        return false;
    }

    @Override
    public boolean isOffScreen()
    {
        return !Utils.isOnScreen(this.position, this.shotRadius);
    }

    public float getDamage() { return this.damage; }

    public final float getShotRadius() { return this.shotRadius; }
}
