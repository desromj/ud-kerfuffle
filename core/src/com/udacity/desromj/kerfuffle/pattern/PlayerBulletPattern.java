package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.PlayerBullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-29.
 */
public class PlayerBulletPattern extends Pattern
{
    public PlayerBulletPattern(Shooter parent, Vector2 position, Vector2 velocity) { super(parent, position, velocity); }

    @Override
    public void render(ShapeRenderer renderer)
    {

    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = parent.getPosition();
        float hitRadius = parent.getHitRadius();

        spawns.add(new PlayerBullet(
                parent,
                new Vector2(origin.x - hitRadius * 4, origin.y + hitRadius),
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED)));

        spawns.add(new PlayerBullet(
                this.getParent(),
                new Vector2(origin.x - hitRadius * 2, origin.y + hitRadius * 3),
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED)));

        spawns.add(new PlayerBullet(
                this.getParent(),
                new Vector2(origin.x, origin.y + hitRadius * 5),
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED)));

        spawns.add(new PlayerBullet(
                this.getParent(),
                new Vector2(origin.x + hitRadius * 2, origin.y + hitRadius * 3),
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED)));

        spawns.add(new PlayerBullet(
                this.getParent(),
                new Vector2(origin.x + hitRadius * 4, origin.y + hitRadius),
                new Vector2(0.0f, Constants.PLAYER_SHOT_SPEED)));

        return spawns;
    }
}
