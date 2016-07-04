package com.udacity.desromj.kerfuffle.ai;

import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

/**
 * Created by Quiv on 01-07-2016.
 *
 * Homing behaviour homes in on the player. Every x seconds, the target it updated
 * to the player's position
 */
public class HomingMoveBehaviour extends MoveBehaviour
{
    private Vector2 velocity;
    private float retargetDelay, retargetIn = 0.0f;

    public HomingMoveBehaviour(Shooter parent, float speed, float retargetDelay)
    {
        super(parent, speed);
        this.velocity = new Vector2();
        this.retargetDelay = retargetDelay;
    }

    public final void setRetargetIn(float seconds)
    {
        this.retargetIn = seconds;
    }

    @Override
    public void activate() {}

    @Override
    public void move(float delta)
    {
        // Retarget if necessary
        this.retargetIn -= delta;

        if (this.retargetIn <= 0.0f)
            this.retarget(delta);

        // Then move towards the target
        Vector2 pos = this.parent.getPosition();

        pos.x += this.velocity.x * delta;
        pos.y += this.velocity.y * delta;
    }

    public void retarget(float delta)
    {
        this.velocity.set(
                GameScreen.instance.getPlayerPosition().x - parent.getPosition().x,
                GameScreen.instance.getPlayerPosition().y - parent.getPosition().y
        );

        // Since homing velocity is based on delta updates, divide by delta to get accurate speed
        this.velocity.nor().scl(this.speed / delta);

        this.retargetIn = retargetDelay;
    }
}
