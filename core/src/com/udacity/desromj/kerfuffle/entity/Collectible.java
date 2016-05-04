package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Quiv on 2016-05-03.
 */
public abstract class Collectible
{
    private float hitRadius;
    private Vector2 position, velocity;

    public Collectible(float x, float y)
    {
        this(new Vector2(x, y));
    }

    private Collectible(Vector2 position)
    {
        this.hitRadius = Constants.COLLECTIBLE_HIT_RADIUS;
        this.position = position;
        this.velocity = new Vector2(0.0f, Constants.COLLECTIBLE_INIT_Y_VELOCITY);
    }

    public void update(float delta)
    {
        this.velocity.y += Constants.COLLECTIBLE_ACCEL_DUE_TO_GRAVITY;
        this.position.y += this.velocity.y;
    }

    public boolean isOnScreen()
    {
        return Utils.isOnScreen(this.position, this.hitRadius);
    }

    public abstract void render(ShapeRenderer renderer, SpriteBatch batch);
}
