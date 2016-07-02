package com.udacity.desromj.kerfuffle.ai;

import com.udacity.desromj.kerfuffle.entity.Shooter;

/**
 * Created by Quiv on 02-07-2016.
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
        String tag = "mt-stationary";
        Shooter parent = null;
        float speed = 0.0f;
        float retargetDelay = 0.5f;
        boolean clockwise = true;

        public MoveBehaviour getBehaviour()
        {
            return MoveFactory.getBehaviour(
                    tag,
                    parent,
                    speed,
                    retargetDelay,
                    clockwise
            );
        }

        public Builder tag(String tag)
        {
            this.tag = tag;
            return this;
        }

        public Builder parent(Shooter parent)
        {
            this.parent = parent;
            return this;
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
