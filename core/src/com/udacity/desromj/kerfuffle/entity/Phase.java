package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-03-16.
 */
public class Phase
{
    float hitPoints, maxHitPoints;
    Array<Pattern> patterns;
    Enums.PatternShotType shotType;

    long timeLeftInPattern;
    int currentIndex;

    /**
     * Each Pattern in the phase can use waveDelay and shotsPerWave to determine when it is finished
     *
     * @param hitPoints
     * @param patterns
     * @param shotType
     */
    public Phase(float hitPoints, Array<Pattern> patterns, Enums.PatternShotType shotType)
    {
        this.maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.patterns = patterns;
        this.shotType = shotType;
        this.currentIndex = -1;

        shootNextPattern();
    }

    /**
     * The phase is aware of timing, and can return the set of Pattern objects which should be shooting.
     * TODO: Should be run in the update() method of Bosses
     */
    public Array<Pattern> getCurrentShootingPatterns()
    {
        switch (shotType)
        {
            case COMBINED:
                return patterns;

            case SEQUENTIAL_LOOP:
            case RANDOM:

                Array<Pattern> ret = new Array<Pattern>();
                ret.add(patterns.get(currentIndex));
                return ret;
        }

        return new Array<Pattern>();
    }

    // Increments shooting index and sets the time the next Pattern should be shooting
    private void shootNextPattern()
    {
        this.currentIndex++;

        if (this.currentIndex >= patterns.size)
            this.currentIndex = 0;
    }

    public void damageHitPoints(float damage)
    {
        this.hitPoints -= damage;
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
