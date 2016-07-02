package com.udacity.desromj.kerfuffle.ai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 02-07-2016.
 */
public class CircleOffscreenBehaviour extends MoveBehaviour
{
    private Vector2 origin;
    private float radius;
    private boolean clockwise;
    private float radSpeed;
    private float currentAngle;

    public CircleOffscreenBehaviour(Shooter parent, float speed, boolean clockwise)
    {
        super(parent, speed);

        this.radius = Constants.WORLD_WIDTH / 2.0f;
        this.clockwise = clockwise;
        this.origin = new Vector2();
        this.radSpeed = this.speed / (float) Math.PI;
        this.currentAngle = 0.0f;

        // Going left, we go clockwise / negative rads
        if (clockwise)
            this.radSpeed *= -1.0f;
    }

    @Override
    public void activate()
    {
        float xOff = parent.getPosition().x + (clockwise ? -this.radius : this.radius);

        this.origin.set(
                xOff,
                parent.getPosition().y
        );

        this.currentAngle = MathUtils.atan2(
                parent.getPosition().y - this.origin.y,
                parent.getPosition().x - this.origin.x
        );
    }

    @Override
    public void move(float delta)
    {
        float nextAngle = currentAngle + this.radSpeed * delta;

        // Using the angle, generate our new position for the parent
        parent.getPosition().set(
                this.origin.x + ((float) Math.cos(nextAngle) * this.radius),
                this.origin.y + ((float) Math.sin(nextAngle) * this.radius)
        );

        this.currentAngle = nextAngle;
    }
}
