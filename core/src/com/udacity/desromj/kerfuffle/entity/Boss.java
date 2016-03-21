package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

/**
 * Created by Mike on 2016-03-16.
 */
public abstract class Boss extends Shooter
{
    public Array<Phase> phases;
    protected float screenActivationHeight;

    /**
     * Bosses do not use their own Patterns - they read Patterns from the
     * Phases of the Boss fight instead
     *
     * @param position
     * @param screenActivationHeight
     */
    protected Boss(Vector2 position, float screenActivationHeight) {
        super(position);
        this.screenActivationHeight = screenActivationHeight;
        this.phases = new DelayedRemovalArray<Phase>();
    }

    /**
     * Bosses are not dead when their HP is 0, they are dead when there are no Phases remaining.
     * @return
     */
    @Override
    public final boolean isDead()
    {
        return this.phases.size <= 0;
    }

    /**
     * TODO: For each Boss, load in Phases appropriate with the Difficulty of the game
     */
    public abstract void loadPhases();
}
