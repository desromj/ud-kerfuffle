package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Quiv on 2016-05-03.
 */
public abstract class Collectible extends Spawnable
{
    protected float hitRadius;

    public Collectible(float x, float y)
    {
        this(new Vector2(x, y));
    }

    private Collectible(Vector2 position)
    {
        super(
                null,
                position,
                new Vector2(0.0f, Constants.COLLECTIBLE_INIT_Y_VELOCITY)
        );

        setHitRadius();
    }

    protected abstract void setHitRadius();

    public void update(float delta)
    {
        this.velocity.y += Constants.COLLECTIBLE_ACCEL_DUE_TO_GRAVITY;
        super.update(delta);
    }

    @Override
    public boolean isOffScreen()
    {
        return !Utils.isOnScreen(this.position, this.hitRadius);
    }

    public boolean isColliding(Player player)
    {
        float dist = Vector2.dst(this.position.x, this.position.y, player.getPosition().x, player.getPosition().y);

        if (dist < this.hitRadius + player.hitRadius)
            return true;

        return false;
    }

    /**
     * Affect the player as necessary for this collectible
     *
     * @param player
     */
    public abstract void collectEffect(Player player);
}
