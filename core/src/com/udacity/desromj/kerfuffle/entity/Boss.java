package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Mike on 2016-03-16.
 */
public abstract class Boss extends Enemy
{
    public Array<Phase> phases;

    /**
     * Bosses do not use their own Patterns - they read Patterns from the
     * Phases of the Boss fight instead
     *
     * @param position
     * @param screenActivationHeight
     */
    public Boss(Vector2 position, float screenActivationHeight) {
        super(position, screenActivationHeight, new Pattern[] {});

    }

    @Override
    public boolean isDead()
    {
        return this.phases.size <= 0;
    }

    /**
     * TODO: For each Boss, load in Phases appropriate with the Difficulty of the game
     */
    public abstract void loadPhases();
}
