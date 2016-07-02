package com.udacity.desromj.kerfuffle.ai;

/**
 * Created by Quiv on 02-07-2016.
 */
public enum MoveType
{
    STATIONARY ("mt-stationary"),
    HOMING ("mt-homing"),
    CIRCLE_OFFSCREEN("mt-circle");

    private String tag;

    private MoveType(String tag)
    {
        this.tag = tag;
    }

    public static MoveType getType(String tag)
    {
        for (MoveType type: MoveType.values())
            if (type.tag.equalsIgnoreCase(tag))
                return type;

        return STATIONARY;
    }
}
