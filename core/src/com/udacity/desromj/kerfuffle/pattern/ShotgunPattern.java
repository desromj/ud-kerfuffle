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
 * Created by Mike on 2016-03-30.
 *
 * Shotgun Pattern uses the following properties:
 *      targetted, shotsPerArm, radius, armAngleOffsetDegrees, speed, mainShotType, armSpeedModifier
 *
 * It fires a number of bullets (shotsPerArm) towards the player (if targetted) or straight down (if not targetted).
 * Bullets are randomized within an angle of affect towards the player (target position + or - armAngleOffsetDegrees)
 * Speed of each bullet is randomized as a ratio of (speed) between (armSpeedModifier) and 1.
 * For example, armSpeedModifier of 0.5 will randomize the max bullet speed between 0.5 - 1.0 times the speed (if 400, values between 200-400)
 */
public class ShotgunPattern extends Pattern
{
    public ShotgunPattern(Shooter parent, PatternProperties props) {
        super(parent, props);
    }

    public ShotgunPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props) {
        super(parent, position, velocity, props);
    }

    @Override
    protected Array<Spawnable> spawnChildren()
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

        Random random = new Random();

        for (int i = 0; i < props.getShotsPerArm(); i++)
        {
            float modifiedAngle = (float)(props.getArmAngleOffsetDegrees() * Math.PI / 180.0f);     // convert to radians
            modifiedAngle *= 2.0f;                                      // double the angle
            modifiedAngle *= random.nextFloat();                        // randomize the angle's range
            modifiedAngle -= props.getArmAngleOffsetDegrees() / 2.0f;   // subtract half the max range

            float upperRange = 1.0f - props.getArmSpeedModifier();          // Determine the upper random range             (ex: val=0.6: 0.0 - 0.4)
            float modifiedSpeed = upperRange * random.nextFloat();          // Get random upper range value                 (ex: val=0.2 (random))
            modifiedSpeed += props.getArmSpeedModifier();                   // Add speed modified for lower range           (ex: 0.6 - 1.0; val=0.8)
            modifiedSpeed = MathUtils.clamp(modifiedSpeed, 0.01f, 1.0f);    // Ensure the modifier is between 0.01 - 1.00   (0.8 clamped)

            // position = origin of the Shooter + radius * offset
            Vector2 spawnPosition = new Vector2(
                    origin.x + (float) Math.cos(angleRads + modifiedAngle) * props.getRadius(),
                    origin.y + (float) Math.sin(angleRads + modifiedAngle) * props.getRadius()
            );

            // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar and the power of the arm speed multiplier
            Vector2 spawnVelocity = new Vector2();

            // Spawn at the generated angle if targetted, otherwise straight down
            if (props.isTargetted())
            {
                spawnVelocity.set(
                        (float) Math.cos(angleRads + modifiedAngle),
                        (float) Math.sin(angleRads + modifiedAngle)
                );
            }
            else
            {
                spawnVelocity.set(
                        0.0f,
                        -1.0f
                );
            }

            spawnVelocity.nor().scl(modifiedSpeed * props.getSpeed());

            spawns.add(SpawnFactory.makeBullet(props.getMainShotType(), this.getParent(), spawnPosition, spawnVelocity));
        }

        return spawns;
    }
}
