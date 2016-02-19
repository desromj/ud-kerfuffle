package com.udacity.desromj.kerfuffle.utility;

/**
 * Created by Mike on 2016-02-19.
 */
public class Enums
{
    /**
     * Types of Bullets which can be spawned
     */
    public enum BulletType
    {
        PLAYER_BULLET,

        SMALL_RED_PELLET,
        LARGE_YELLOW_BALL
    }

    /**
     * Types of Patterns which can be spawned
     */
    public enum PatternType
    {
        PLAYER_BULLET_PATTERN,

        CIRCLE_PATTERN,
        SHOTGUN_PATTERN
    }

}
