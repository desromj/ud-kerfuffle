package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.Gdx;
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
 * Created by Quiv on 2016-02-23.
 */
public class SpiralPattern extends Pattern
{
    private float nextShotDegree;

    public SpiralPattern(Shooter parent, PatternProperties props) {
        super(parent, props);
        initFields();
    }

    public SpiralPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props) {
        super(parent, position, velocity, props);
        initFields();
    }

    private void initFields()
    {
        this.nextShotDegree = 0.0f;
    }

    @Override
    protected Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = new Vector2(parent.getPosition().x, parent.getPosition().y);

        float angleRads = this.nextShotDegree * MathUtils.degreesToRadians;

        // Randomize shots if we're not targetted
        if (!props.isTargetted())
        {
            float mod = props.getArmAngleOffsetDegrees() * MathUtils.degreesToRadians;
            float randomMod = 2.0f * mod - mod;
            randomMod *= new Random().nextFloat();

            angleRads += randomMod;
        }

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

        // Update fields for next shot
        this.nextShotDegree += props.getArmAngleOffsetDegrees();
        this.nextShotDegree %= 360.0f;

        return spawns;
    }
}
