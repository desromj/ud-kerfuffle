package com.udacity.desromj.kerfuffle.entity;

import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;
import com.udacity.desromj.kerfuffle.utility.Utils;

import org.json.simple.JSONObject;

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

    private float waveDelay;             // time, in seconds, to wait between waves of Spawnables
    private float cannotWaveFor;         // Internal timer for tracking wave delay

    private int shotsPerWave;               // number of shots to make before resetting the wave delay
    private int shotsLeftInWave;            // number of shots remaining in the current wave of Spawnables

    private Enums.BulletType mainShotType;          // Shot type for majority of the Bullets
    private Enums.BulletType secondaryShotType;     // Shot type for secondary Bullets

    private int drawOrder;                          // order the bullet gets drawn in

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
            float waveDelay,
            float cannotWaveFor,
            int shotsPerWave,
            int shotsLeftInWave,
            Enums.BulletType mainShotType,
            Enums.BulletType secondaryShotType,
            int drawOrder
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
        this.waveDelay = waveDelay;
        this.cannotWaveFor = cannotWaveFor;
        this.shotsPerWave = shotsPerWave;
        this.shotsLeftInWave = shotsLeftInWave;
        this.mainShotType = mainShotType;
        this.secondaryShotType = secondaryShotType;
        this.drawOrder = drawOrder;
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

        private float waveDelay = 0.0f;             // time, in seconds, to wait between waves of Spawnables
        private float cannotWaveFor = 0.0f;         // Internal timer for tracking wave pattern spawning
        private int shotsPerWave = 0;               // number of shots to make before resetting the wave delay
        private int shotsLeftInWave = 0;            // number of shots remaining in the current wave of Spawnables

        private Enums.BulletType mainShotType = Constants.DEFAULT_SHOT_TYPE;          // Shot type for majority of the Bullets
        private Enums.BulletType secondaryShotType = Constants.DEFAULT_SHOT_TYPE;     // Shot type for secondary Bullets

        private int drawOrder = mainShotType.getDrawOrder();

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
                    waveDelay,
                    cannotWaveFor,
                    shotsPerWave,
                    shotsLeftInWave,
                    mainShotType,
                    secondaryShotType,
                    drawOrder
            );
        }

        // TODO: Not tested, but already this is not right. Needs to read property values from the JSONObject
        public PatternProperties createPropsFromJSON(JSONObject object)
        {
            PatternProperties props = createProps();

            for (Object key: object.keySet())
            {
                String tag;

                try {
                    tag = Utils.castJSON(object, key);
                } catch (Exception ex) { continue; }

                if (tag.equals("active")) {
                    Boolean val = Utils.castJSON(object, key);
                    props.setActive(val);
                }
                if (tag.equals("shotDelay")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setShotDelay(val);
                }
                if (tag.equals("targetted")) {
                    Boolean val = Utils.castJSON(object, key);
                    props.setTargetted(val);
                }
                if (tag.equals("shotsPerArm")) {
                    int val = Utils.castJSONInteger(object, key);
                    props.setShotsPerArm(val);
                }
                if (tag.equals("arms")) {
                    int val = Utils.castJSONInteger(object, key);
                    props.setArms(val);
                }
                if (tag.equals("radius")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setRadius(val);
                }
                if (tag.equals("armAngleOffsetDegrees")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setArmAngleOffsetDegrees(val);
                }
                if (tag.equals("armSpeedModifier")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setArmSpeedModifier(val);
                }
                if (tag.equals("speed")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setSpeed(val);
                }
                if (tag.equals("waveDelay")) {
                    float val = Utils.castJSONFloat(object, key);
                    props.setWaveDelay(val);
                }
                if (tag.equals("shotsPerWave")) {
                    int val = Utils.castJSONInteger(object, key);
                    props.setShotsPerWave(val);
                }
            }

            return props;
        }

        /**
         * Creates a deep clone of the passed PatternProperties object. The result can be safely
         * edited without affecting the original
         *
         * @param props
         * @return
         */
        public PatternProperties clone(PatternProperties props)
        {
            return new PatternProperties(
                    props.active,
                    props.cannotShootFor,
                    props.shotDelay,
                    props.targetted,
                    props.shotsPerArm,
                    props.arms,
                    props.radius,
                    props.armAngleOffsetDegrees,
                    props.armSpeedModifier,
                    props.speed,
                    props.waveDelay,
                    props.cannotWaveFor,
                    props.shotsPerWave,
                    props.shotsLeftInWave,
                    props.mainShotType,
                    props.secondaryShotType,
                    props.drawOrder
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

        public Builder waveDelay(float waveDelay) {
            this.waveDelay = waveDelay;
            return this;
        }

        public Builder cannotWaveFor(float cannotWaveFor) {
            this.cannotWaveFor = cannotWaveFor;
            return this;
        }

        public Builder shotsPerWave(int shotsPerWave) {
            this.shotsPerWave = shotsPerWave;
            return this;
        }

        public Builder shotsLeftInWave(int shotsLeftInWave) {
            this.shotsLeftInWave = shotsLeftInWave;
            return this;
        }

        public Builder mainShotType(Enums.BulletType mainShotType) {
            this.mainShotType = mainShotType;
            return this;
        }

        public Builder secondaryShotType(Enums.BulletType secondaryShotType) {
            this.secondaryShotType = secondaryShotType;
            return this;
        }

        public Builder drawOrder(int drawOrder) {
            this.drawOrder = drawOrder;
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

    public float getWaveDelay() {
        return waveDelay;
    }

    public void setWaveDelay(float waveDelay) {
        this.waveDelay = waveDelay;
    }

    public float getCannotWaveFor() {
        return cannotWaveFor;
    }

    public void setCannotWaveFor(float cannotWaveFor) {
        this.cannotWaveFor = cannotWaveFor;
    }

    public int getShotsPerWave() {
        return shotsPerWave;
    }

    public void setShotsPerWave(int shotsPerWave) {
        this.shotsPerWave = shotsPerWave;
    }

    public int getShotsLeftInWave() {
        return shotsLeftInWave;
    }

    public void setShotsLeftInWave(int shotsLeftInWave) {
        this.shotsLeftInWave = shotsLeftInWave;
    }

    public Enums.BulletType getMainShotType() {
        return mainShotType;
    }

    public void setMainShotType(Enums.BulletType mainShotType) {
        this.mainShotType = mainShotType;
    }

    public Enums.BulletType getSecondaryShotType() {
        return secondaryShotType;
    }

    public void setSecondaryShotType(Enums.BulletType secondaryShotType) {
        this.secondaryShotType = secondaryShotType;
    }
}
