package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.pattern.CirclePattern;
import com.udacity.desromj.kerfuffle.pattern.PlayerBulletPattern;
import com.udacity.desromj.kerfuffle.pattern.DirectShotPattern;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-02-03.
 */
public final class SpawnFactory
{
    private SpawnFactory() {}

    /**
     * Creates a Bullet based on all available bullet types
     *
     * @param type enum to determine the bullet type to create
     * @param parent the Shooter who is shooting the bullet
     * @param position The position of the bullet on spawn
     * @param velocity The velocity of the bullet on spawn
     * @return The matching Bullet object which was created. null if the BulletType enum is not yet implemented
     */
    public static final Bullet makeBullet(Enums.BulletType type, Shooter parent, Vector2 position, Vector2 velocity)
    {
        switch (type)
        {
            case PLAYER_BULLET:             return new PlayerBullet(parent, position, velocity);
            case SMALL_RED_PELLET:          return new SmallRedPelletBullet(parent, position, velocity);
            case LARGE_YELLOW_BALL:         return new LargeYellowBallBullet(parent, position, velocity);
        }

        return null;
    }

    /**
     * Creates a Pattern based on all available pattern types
     *
     * @param type enum to determine the bullet type to create
     * @param parent the Shooter who is shooting the bullet
     * @param position The position of the bullet on spawn
     * @param velocity The velocity of the bullet on spawn
     * @return The matching Bullet object which was created. null if the BulletType enum is not yet implemented
     */
    public static final Pattern makePattern(Enums.PatternType type, Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        switch (type)
        {
            case PLAYER_BULLET_PATTERN:         return new PlayerBulletPattern(parent, position, velocity, props);
            case CIRCLE_PATTERN:                return new CirclePattern(parent, position, velocity, props);
            case DIRECT_SHOT_PATTERN:               return new DirectShotPattern(parent, position, velocity, props);
        }

        return null;
    }
}
