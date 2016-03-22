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

    /**
     * Each Pattern in the phase can use waveDelay and shotsPerWave to determine when it is finished
     *
     * @param hitPoints
     * @param patterns
     */
    public Phase(float hitPoints, Array<Pattern> patterns)
    {
        this.maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.patterns = patterns;
    }

    /**
     * Accessor for Pattern.shoot - should be called every update in the Boss class
     */
    public void shootPatterns()
    {
        for (Pattern p: patterns)
            p.shoot();
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

    /**
     * Easy accessor for determining the remaining percentage of health for this Phase
     * @return
     */
    public float percentHitPointsLeft()
    {
        if (this.hitPoints <= 0.01f)
            return 0.0f;
        else
            return this.hitPoints / this.maxHitPoints;
    }
}
