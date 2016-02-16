package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-02-11.
 *
 * PatternProperties uses a PatternProperties Builder as a static inner class.
 * If you would like to make a new set of props, use new PatternProperties.Builder().createProps()
 */
public class PatternProperties
{
    private boolean active;              // true means the shoot() method will shoot its Spawnables (Bullets and Patterns)
    private float cannotShootFor;        // Internal timer used in conjuction with shotDelay

    private float shotDelay;             // Time (in seconds) to wait between waves of shooting children Spawnable objects
    private boolean targetted;           // true to target the Player, false for random or other targetting
    private int shotsPerArm;                // Number of Bullets per arm
    private int arms;                       // Number of arms/columns of Bullets to spawn
    private float radius;                       // Radius of a circle around the origin to spawn Bullets
    private float armAngleOffsetDegrees;        // Offset in degrees for each new Bullet arm
    private float armSpeedModifier;             // Multiplier for multiple Bullets in the arm
    private float speed;                        // Speed the Pattern will shoot Bullets at

    private BulletType mainShotType;          // Shot type for majority of the Bullets
    private BulletType secondaryShotType;     // Shot type for secondary Bullets

    private PatternProperties(
            boolean active,
            float cannotShootFor,
            float shotDelay,
            boolean targetted,
            int shotsPerArm,
            int arms,
            float radius,
            float armAngleOffsetDegrees,
            float armSpeedModifier,
            float speed,
            BulletType mainShotType,
            BulletType secondaryShotType
    )
    {
        this.active = active;
        this.cannotShootFor = cannotShootFor;
        this.shotDelay = shotDelay;
        this.targetted = targetted;
        this.shotsPerArm = shotsPerArm;
        this.arms = arms;
        this.radius = radius;
        this.armAngleOffsetDegrees = armAngleOffsetDegrees;
        this.armSpeedModifier = armSpeedModifier;
        this.speed = speed;
        this.mainShotType = mainShotType;
        this.secondaryShotType = secondaryShotType;
    }

    /*
    Pattern Property Builder - holds all optional parameters which Patterns may make use of.
    Also creates standard default values for the Properties which should be relatively decent if not overridden.
     */
    public static class Builder
    {
        private boolean active = true;              // true means the shoot() method will shoot its Spawnables (Bullets and Patterns)
        private float cannotShootFor = 0.0f;        // Internal timer used in conjuction with shotDelay
        private float shotDelay = 1.0f;             // Time (in seconds) to wait between waves of shooting children Spawnable objects
        private boolean targetted = true;           // true to target the Player, false for random or other targetting
        private int shotsPerArm = 1;                // Number of Bullets per arm
        private int arms = 1;                       // Number of arms/columns of Bullets to spawn
        private float radius = Constants.WORLD_WIDTH / 250.0f;      // Radius of a circle around the origin to spawn Bullets
        private float armAngleOffsetDegrees = 0.0f;                 // Offset in degrees for each new Bullet arm
        private float armSpeedModifier = 1.0f;                      // Multiplier for multiple Bullets in the arm
        private float speed = Constants.DEFAULT_SHOT_SPEED;         // Speed the Pattern will shoot Bullets at

        private BulletType mainShotType = Constants.DEFAULT_SHOT_TYPE;          // Shot type for majority of the Bullets
        private BulletType secondaryShotType = Constants.DEFAULT_SHOT_TYPE;     // Shot type for secondary Bullets

        public PatternProperties createProps()
        {
            return new PatternProperties(
                    active,
                    cannotShootFor,
                    shotDelay,
                    targetted,
                    shotsPerArm,
                    arms,
                    radius,
                    armAngleOffsetDegrees,
                    armSpeedModifier,
                    speed,
                    mainShotType,
                    secondaryShotType
            );
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder cannotShootFor(float cannotShootFor) {
            this.cannotShootFor = cannotShootFor;
            return this;
        }

        public Builder shotDelay(float shotDelay) {
            this.shotDelay = shotDelay;
            return this;
        }

        public Builder targetted(boolean targetted) {
            this.targetted = targetted;
            return this;
        }

        public Builder shotsPerArm(int shotsPerArm) {
            this.shotsPerArm = shotsPerArm;
            return this;
        }

        public Builder arms(int arms) {
            this.arms = arms;
            return this;
        }

        public Builder radius(float radius) {
            this.radius = radius;
            return this;
        }

        public Builder armAngleOffsetDegrees(float armAngleOffsetDegrees) {
            this.armAngleOffsetDegrees = armAngleOffsetDegrees;
            return this;
        }

        public Builder armSpeedModifier(float armSpeedModifier) {
            this.armSpeedModifier = armSpeedModifier;
            return this;
        }

        public Builder speed(float speed) {
            this.speed = speed;
            return this;
        }

        public Builder mainShotType(BulletType mainShotType) {
            this.mainShotType = mainShotType;
            return this;
        }

        public Builder secondaryShotType(BulletType secondaryShotType) {
            this.secondaryShotType = secondaryShotType;
            return this;
        }
    }

    /*
    Pattern Properties Getters and Setters
     */

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getCannotShootFor() {
        return cannotShootFor;
    }

    public void setCannotShootFor(float cannotShootFor) {
        this.cannotShootFor = cannotShootFor;
    }

    public float getShotDelay() {
        return shotDelay;
    }

    public void setShotDelay(float shotDelay) {
        this.shotDelay = shotDelay;
    }

    public boolean isTargetted() {
        return targetted;
    }

    public void setTargetted(boolean targetted) {
        this.targetted = targetted;
    }

    public int getShotsPerArm() {
        return shotsPerArm;
    }

    public void setShotsPerArm(int shotsPerArm) {
        this.shotsPerArm = shotsPerArm;
    }

    public int getArms() {
        return arms;
    }

    public void setArms(int arms) {
        this.arms = arms;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getArmAngleOffsetDegrees() {
        return armAngleOffsetDegrees;
    }

    public void setArmAngleOffsetDegrees(float armAngleOffsetDegrees) {
        this.armAngleOffsetDegrees = armAngleOffsetDegrees;
    }

    public float getArmSpeedModifier() {
        return armSpeedModifier;
    }

    public void setArmSpeedModifier(float armSpeedModifier) {
        this.armSpeedModifier = armSpeedModifier;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public BulletType getMainShotType() {
        return mainShotType;
    }

    public void setMainShotType(BulletType mainShotType) {
        this.mainShotType = mainShotType;
    }

    public BulletType getSecondaryShotType() {
        return secondaryShotType;
    }

    public void setSecondaryShotType(BulletType secondaryShotType) {
        this.secondaryShotType = secondaryShotType;
    }
}
