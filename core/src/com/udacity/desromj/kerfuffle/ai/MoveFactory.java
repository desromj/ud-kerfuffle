package com.udacity.desromj.kerfuffle.ai;

import com.udacity.desromj.kerfuffle.entity.Shooter;

/**
 * Created by Quiv on 02-07-2016.
 *
 * Uses a Builder inner static class to generate movement patterns.
 *
 * Use new MoveFactory.Builder() to access the Builder and set properties. Finish
 * creating the MoveBehaviour object by calling the method
 * Builder.getBehaviour(String tag, Shooter parent)
 * The parameters tag and Parent are required for every behaviour, so they are mandatory
 * parameters in the Builder.
 *
 * All parameters can be set in Overlap2D by using their exact label as named in this class.
 * Current parameters:
 *      - tag (see MoveType enum for available tags. If not found, will default to stationary)
 *      - speed (value of 1 works well for slow scrolling. This is called every update)
 *      - retargetDelay (amount of time to wait before re-querying the desired target)
 *      - clockwise (in circular patterns, true to circle clockwise, false to go counterclockwise)
 */
public class MoveFactory
{
    private MoveFactory() {}

    private static MoveBehaviour getBehaviour(
            String tag,
            Shooter parent,
            float speed,
            float retargetDelay,
            boolean clockwise
    )
    {
        MoveBehaviour behaviour;
        MoveType type = MoveType.getType(tag);

        switch (type)
        {
            case CIRCLE_OFFSCREEN:
                return new CircleOffscreenBehaviour(parent, speed, clockwise);
            case HOMING:
                return new HomingMoveBehaviour(parent, speed, retargetDelay);
            default:
                return new StationaryMoveBehaviour(parent, 0.0f);
        }
    }

    public static class Builder
    {
        float speed = 0.0f;
        float retargetDelay = 0.5f;
        boolean clockwise = true;

        public MoveBehaviour getBehaviour(String tag, Shooter parent)
        {
            return MoveFactory.getBehaviour(
                    tag,
                    parent,
                    speed,
                    retargetDelay,
                    clockwise
            );
        }

        public Builder speed(float speed)
        {
            this.speed = speed;
            return this;
        }

        public Builder retargetDelay(float retargetDelay)
        {
            this.retargetDelay = retargetDelay;
            return this;
        }

        public Builder clockwise(boolean clockwise)
        {
            this.clockwise = clockwise;
            return this;
        }

    }
}
