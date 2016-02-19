package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Quiv on 2016-01-29.
 */
public class PlayerBulletPattern extends Pattern
{
    public PlayerBulletPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        super(parent, position, velocity, props);
    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Vector2 origin = this.getParent().getPosition();

        spawns.add(getBullet(
                new Vector2(origin.x - Constants.PLAYER_SHOT_SEPARATION * 4, origin.y + Constants.PLAYER_SHOT_SEPARATION)));
        spawns.add(getBullet(
                new Vector2(origin.x - Constants.PLAYER_SHOT_SEPARATION * 2, origin.y + Constants.PLAYER_SHOT_SEPARATION * 3)));
        spawns.add(getBullet(
                new Vector2(origin.x, origin.y + Constants.PLAYER_SHOT_SEPARATION * 5)));
        spawns.add(getBullet(
                new Vector2(origin.x + Constants.PLAYER_SHOT_SEPARATION * 2, origin.y + Constants.PLAYER_SHOT_SEPARATION * 3)));
        spawns.add(getBullet(
                new Vector2(origin.x + Constants.PLAYER_SHOT_SEPARATION * 4, origin.y + Constants.PLAYER_SHOT_SEPARATION)));

        return spawns;
    }

    private Bullet getBullet(Vector2 origin)
    {
        return SpawnFactory.makeBullet(
                Enums.BulletType.PLAYER_BULLET,
                this.getParent(),
                origin,
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED));
    }
}
