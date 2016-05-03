package com.udacity.desromj.kerfuffle.utility;

/**
 * Created by Mike on 2016-02-19.
 */
public class Enums
{
    /**
     * Difficulty for the game - determines which Patterns load from the JSON level file
     */
    public enum Difficulty
    {
        EASY,
        MEDIUM,
        HARD,
        INSANE
    }

    public enum EnemyType
    {
        MITE("mite"),
        DRAGONFLY("dragonfly"),
        MANTIS("mantis");

        private String tag;

        private EnemyType(String tag)
        {
            this.tag = tag;
        }

        public static EnemyType getType(String tag)
        {
            for (EnemyType type: EnemyType.values())
                if (type.tag.equalsIgnoreCase(tag))
                    return type;

            return EnemyType.MITE;
        }
    }
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
        DIRECT_SHOT_PATTERN,
        SHOTGUN_PATTERN
    }

    /**
     * Enums to denote how Enemies should be shooting their Patterns []
     */
    public enum PatternShotType
    {
        // Shoot all of the Bullet Patterns at the same time
        COMBINED,

        // Pick one random Pattern to shoot for a length of time, then switch to another
        RANDOM,

        // Shoot the first Pattern for a length of time, then switch to Pattern 2, then 3, then loop, etc.
        SEQUENTIAL_LOOP
    }
}
