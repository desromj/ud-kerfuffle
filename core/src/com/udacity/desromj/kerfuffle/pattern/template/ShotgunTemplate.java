package com.udacity.desromj.kerfuffle.pattern.template;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.bullet.BulletFactory;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import java.util.Random;

/**
 * Created by Mike on 2016-02-09.
 */
public abstract class ShotgunTemplate extends Pattern
{
    boolean targetted;
    int shotsPerArm;
    int arms;
    float radius;
    float armAngleOffsetDegrees;
    float armSpeedModifier;
    float speed;

    BulletType mainShotType;
    BulletType armShotType;

    /**
     * Spawns bullets either targetted - towards the player, or not targetted - straight vertically down
     *
     * Spawns a double row of bullets (number = shotsPerArm) shot in a straight line. The row of bullets is
     * offset by armAngleOffsetDegrees for the origin (initial shot) and for each arm thereafter (arms).
     *
     * @param parent The Shooter shooting this Pattern
     * @param position The position of the Pattern, NOT the Bullets it spawns
     * @param velocity The velocity of the Pattern, NOT the Bullets it spawns
     * @param shotDelay The delay, in seconds, between spawning waves of Spawnables
     * @param targetted true to target the player, false to target straight down onscreen
     * @param shotsPerArm Number of bullets to spawn per row
     * @param arms Number of arms to spawn along with the main one. Capped between 1 - 50
     * @param radius Radius of the circle to spawn the shots from
     * @param armAngleOffsetDegrees Number of degrees to separate shots by. Capped between 0.0 - 15.0f
     * @param armSpeedModifier Value to multiply the speed by for each new arm. Capped between 0.75 - 1.00
     * @param speed Speed to launch the initial shot at. Future arms are reduced by armSpeedModifier
     * @param angledShots true to launch arms in the direction of their angle of spawning. false to launch them straight down the screen
     * @param mainShotType BulletType for the first arm
     * @param armShotType BulletType for each additional arm
     */
    public ShotgunTemplate(Shooter parent, Vector2 position, Vector2 velocity, float shotDelay,
                           boolean targetted, int shotsPerArm, int arms, float radius, float armAngleOffsetDegrees,
                           float armSpeedModifier, float speed, boolean angledShots, BulletType mainShotType, BulletType armShotType)
    {
        super(parent, position, velocity, shotDelay);

        this.targetted = targetted;
        this.shotsPerArm = shotsPerArm;
        this.radius = radius;

        // Cap number of arms
        if (arms <= 0)
            this.arms = 1;
        else if (arms > 50)
            this.arms = 50;
        else
            this.arms = arms;

        // Cap arm offset
        if (armAngleOffsetDegrees <= 0.0f)
            this.armAngleOffsetDegrees = 0.0f;
        else if (armAngleOffsetDegrees > 15.0f)
            this.armAngleOffsetDegrees = 15.0f;
        else
            this.armAngleOffsetDegrees = armAngleOffsetDegrees;

        // Cap arm speed modifier
        if (armSpeedModifier <= 0.75f)
            this.armSpeedModifier = 0.75f;
        else if (armSpeedModifier > 1.0f)
            this.armSpeedModifier = 1.0f;
        else
            this.armSpeedModifier = armSpeedModifier;

        this.speed = speed;
        this.mainShotType = mainShotType;
        this.armShotType = armShotType;
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
        if (this.targetted) {
            angleRads = MathUtils.atan2(
                    playerPos.y - origin.y,
                    playerPos.x - origin.x
            );
        } else {
            angleRads = (float) (3.0f * Math.PI) / 2.0f;
        }

        // Spawn a bullet arm distributed at the arm angle offset for EACH arm. First is main Bullet Type, everything after is secondary
        for (int i = 0; i < this.arms; i++)
        {
            boolean firstArm = (i == 0);

            float offsetRads;

            if (firstArm)
                offsetRads = 0.0f;
            else
                offsetRads = (float)(this.armAngleOffsetDegrees * Math.PI / 180.0f) * i;

            // Working angle to spawn bullets at for each new bullet in the circle
            float workingAngleLeft = angleRads - offsetRads;
            float workingAngleRight = angleRads + offsetRads;

            // Spawn each Bullet in the arm
            for (int j = 0; j < this.shotsPerArm; j++) {
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
                origin.x + (float) Math.cos(angleRads) * this.radius,
                origin.y + (float) Math.sin(angleRads) * this.radius
        );

        // velocity = normalized distance between origin and spawnPosition, multiplied by a scalar and the power of the arm speed multiplier
        Vector2 spawnVelocity = new Vector2();

        // Spawn at the generated angle if targetted, otherwise straight down
        if (this.targetted)
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
        spawnVelocity.nor().scl(this.speed * (1.0f * (float) Math.pow(this.armSpeedModifier, armShotIdx)));

        // Spawn the main shot type for first arm, and the secondary type for each additional one
        if (firstArm) {
            return BulletFactory.makeBullet(this.mainShotType, this.getParent(), spawnPosition, spawnVelocity);
        } else {
            return BulletFactory.makeBullet(this.armShotType, this.getParent(), spawnPosition, spawnVelocity);
        }
    }

    @Override
    public void render(ShapeRenderer renderer) {

    }
}
