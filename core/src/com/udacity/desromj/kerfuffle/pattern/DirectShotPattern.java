package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.SpawnFactory;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

/**
 * Created by Mike on 2016-02-09.
 */
public class DirectShotPattern extends Pattern
{
    /**
     * Spawns bullets either targetted - towards the player, or not targetted - straight vertically down
     *
     * Spawns a double row of bullets (number = shotsPerArm) shot in a straight line. The row of bullets is
     * offset by armAngleOffsetDegrees for the origin (initial shot) and for each arm thereafter (arms).
     *
     * Properties should set: targetted, arms, shotsPerArm, radius, armAngleOffsetDegrees, armSpeedModifier, speed, mainShotType, secondaryShotType
     */
    public DirectShotPattern(Shooter parent, PatternProperties props)
    {
        super(parent, props);
    }

    /**
     * Spawns bullets either targetted - towards the player, or not targetted - straight vertically down
     *
     * Spawns a double row of bullets (number = shotsPerArm) shot in a straight line. The row of bullets is
     * offset by armAngleOffsetDegrees for the origin (initial shot) and for each arm thereafter (arms).
     *
     * Properties should set: targetted, arms, shotsPerArm, radius, armAngleOffsetDegrees, armSpeedModifier, speed, mainShotType, secondaryShotType
     */
    public DirectShotPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props)
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

        // Initial Shot Angle
        float angleRads;

        /*
         Get the angle between the origin of this pattern and the player if targetted.
         Get the angle straight down if not (3 * PI / 2)
          */
        if (props.isTargetted()) {
            angleRads = MathUtils.atan2(
                    playerPos.y - origin.y,
                    playerPos.x - origin.x
            );
        } else {
            angleRads = (float) (3.0f * Math.PI) / 2.0f;
        }

        // Spawn a bullet arm distributed at the arm angle offset for EACH arm. First is main Bullet Type, everything after is secondary
        for (int i = 0; i < props.getArms(); i++)
        {
            boolean firstArm = (i == 0);

            float offsetRads;

            if (firstArm)
                offsetRads = 0.0f;
            else
                offsetRads = (float)(props.getArmAngleOffsetDegrees() * Math.PI / 180.0f) * i;

            // Working angle to spawn bullets at for each new bullet in the circle
            float workingAngleLeft = angleRads - offsetRads;
            float workingAngleRight = angleRads + offsetRads;

            // Spawn each Bullet in the arm
            for (int j = 0; j < props.getShotsPerArm(); j++) {
                spawns.add(generateBullet(origin, j, workingAngleLeft, firstArm));

                // Don't spawn duplicate bullets in the same position
                if (workingAngleRight != workingAngleLeft)
                    spawns.add(generateBullet(origin, j, workingAngleRight, firstArm));
            }
        }

        return spawns;
    }

    private Bullet generateBullet(Vector2 origin, int armShotIdx, float angleRads, boolean firstArm)
    {
        // position = origin of the Shooter + radius * offset
        Vector2 spawnPosition = new Vector2(
                origin.x + (float) Math.cos(angleRads) * props.getRadius(),
                origin.y + (float) Math.sin(angleRads) * props.getRadius()
        );

        // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar and the power of the arm speed multiplier
        Vector2 spawnVelocity = new Vector2();

        // Spawn at the generated angle if targetted, otherwise straight down
        if (props.isTargetted())
        {
            spawnVelocity.set(
                    spawnPosition.x - origin.x,
                    spawnPosition.y - origin.y
            );
        }
        else
        {
            spawnVelocity.set(
                    0.0f,
                    -1.0f
            );
        }

        // Scale the bullet to the instance of the bullet in the arm
        spawnVelocity.nor().scl(props.getSpeed() * (1.0f * (float) Math.pow(props.getArmSpeedModifier(), armShotIdx)));

        // Spawn the main shot type for first arm, and the secondary type for each additional one
        if (firstArm) {
            return SpawnFactory.makeBullet(props.getMainShotType(), this.getParent(), spawnPosition, spawnVelocity);
        } else {
            return SpawnFactory.makeBullet(props.getSecondaryShotType(), this.getParent(), spawnPosition, spawnVelocity);
        }
    }
}
