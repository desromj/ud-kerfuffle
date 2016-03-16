package com.udacity.desromj.kerfuffle.entity;

import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-03-16.
 */
public class Phase
{
    float hitPoints, maxHitPoints;
    Pattern [] patterns;
    Enums.PatternShotType shotType;

    public Phase(float hitPoints, Pattern [] patterns, Enums.PatternShotType shotType)
    {
        this.maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.patterns = patterns;
        this.shotType = shotType;
    }

    // The phase is aware of timing, and can return the set of Pattern objects which should be shooting
    public Pattern[] getCurrentShootingPatterns()
    {

        // TODO: Account for all Enum values in Enums.PatternShotType
        return null;
    }

    /*
    Getters and Setters
     */
    public boolean isDead()
    {
        return hitPoints <= 0.0f;
    }

    public float getHitPoints() {
        return hitPoints;
    }

    public float getMaxHitPoints() {
        return maxHitPoints;
    }

    public Enums.PatternShotType getShotType() {
        return shotType;
    }

    public void setShotType(Enums.PatternShotType shotType) {
        this.shotType = shotType;
    }
}
