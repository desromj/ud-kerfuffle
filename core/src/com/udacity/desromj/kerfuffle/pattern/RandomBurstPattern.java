package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import java.util.Random;

/**
 * Created by Mike on 2016-02-25.
 */
public class RandomBurstPattern extends Pattern
{
    public RandomBurstPattern(Shooter parent, PatternProperties props) {
        super(parent, props);
    }

    public RandomBurstPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props) {
        super(parent, position, velocity, props);
    }

    @Override
    protected Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = new Vector2(parent.getPosition().x, parent.getPosition().y);

        float angleRads = MathUtils.atan2(
                    new Random().nextInt(200) - 100,
                    new Random().nextInt(200) - 100
            );

        // position = origin + radius offset by the angle
        Vector2 spawnPosition = new Vector2(
                origin.x + (float) Math.cos(angleRads) * props.getRadius(),
                origin.y + (float) Math.sin(angleRads) * props.getRadius()
        );

        // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar
        Vector2 spawnVelocity = new Vector2(
                spawnPosition.x - origin.x,
                spawnPosition.y - origin.y
        ).nor().scl(props.getSpeed());

        spawns.add(SpawnFactory.makeBullet(props.getMainShotType(), this.getParent(), spawnPosition, spawnVelocity));

        return spawns;
    }
}
