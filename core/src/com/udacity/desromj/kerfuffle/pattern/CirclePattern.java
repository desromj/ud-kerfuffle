package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.BulletFactory;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.bullet.SmallRedPelletBullet;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import java.util.Random;

/**
 * Created by Mike on 2016-02-03.
 */
public class CirclePattern extends Pattern
{
    /**
     * Spawns bullets in a circular pattern around an origin point
     *
     * Properties should set: shotDelay, mainShotType, arms, radius, speed, targetted
     */
    public CirclePattern(Shooter parent, PatternProperties props)
    {
        super(parent, props);
    }

    /**
     * Spawns bullets in a circular pattern around an origin point
     *
     * Properties should set: shotDelay, mainShotType, arms, radius, speed, targetted
     */
    public CirclePattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
    {
        super(parent, position, velocity, props);
    }

    @Override
    public Array<Spawnable> spawnChildren()
    {
        Array<Spawnable> spawns = new DelayedRemovalArray<Spawnable>();

        Shooter parent = this.getParent();
        Vector2 origin = new Vector2(parent.getPosition().x, parent.getPosition().y);
        Vector2 playerPos = GameScreen.instance.getPlayerPosition();

        float angleRads;

        // Get the angle between the origin of this pattern and the player. Or a random target, if requested
        if (props.isTargetted()) {
            angleRads = MathUtils.atan2(
                    playerPos.y - origin.y,
                    playerPos.x - origin.x
            );
        } else {
            angleRads = MathUtils.atan2(
                    new Random().nextInt(200) - 100,
                    new Random().nextInt(200) - 100
            );
        }

        // get the offset in radians each additional bullet will need to be spawned at
        float stepOffset = (float) (360.0f / props.getArms() * (Math.PI / 180.0f));

        // Spawn a bullet distributed evenly around the outside radius of the circle. First bullet is targetted towards the player
        for (int i = 0; i < props.getArms(); i++)
        {
            // Working angle to spawn bullets at for each new bullet in the circle
            float workingAngle = angleRads + stepOffset * i;

            // position = origin + radius offset by the angle
            Vector2 spawnPosition = new Vector2(
                    origin.x + (float) Math.cos(workingAngle) * props.getRadius(),
                    origin.y + (float) Math.sin(workingAngle) * props.getRadius()
            );

            // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar
            Vector2 spawnVelocity = new Vector2(
                    spawnPosition.x - origin.x,
                    spawnPosition.y - origin.y
            ).nor().scl(props.getSpeed());

            spawns.add(BulletFactory.makeBullet(props.getMainShotType(), this.getParent(), spawnPosition, spawnVelocity));
        }

        return spawns;
    }

    @Override
    public void render(ShapeRenderer renderer) {

    }
}
