package com.udacity.desromj.kerfuffle.bullet;

import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Shooter;

/**
 * Created by Mike on 2016-02-03.
 */
public final class BulletFactory
{
    private BulletFactory() {}

    /**
     * Creates a Bullet based on all available bullet types
     *
     * @param type enum to determine the bullet type to create
     * @param parent the Shooter who is shooting the bullet
     * @param position The position of the bullet on spawn
     * @param velocity The velocity of the bullet on spawn
     * @return The matching Bullet object which was created. null if the BulletType enum is not yet implemented
     */
    public static final Bullet makeBullet(BulletType type, Shooter parent, Vector2 position, Vector2 velocity)
    {
        switch (type)
        {
            case PLAYER_BULLET:             return new PlayerBullet(parent, position, velocity);
            case SMALL_RED_PELLET:          return new SmallRedPelletBullet(parent, position, velocity);
            case LARGE_YELLOW_BALL:         return new LargeYellowBallBullet(parent, position, velocity);
        }

        return null;
    }
}
