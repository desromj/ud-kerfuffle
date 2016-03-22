package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-03-16.
 */
public abstract class Boss extends Shooter
{
    public Array<Phase> phases;
    protected float screenActivationHeight;

    private Phase currentPhase;

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
        this.phases = this.loadPhases();
        this.currentPhase = phases.peek();
    }

    @Override
    public final void update(float delta)
    {
        // If the current Phase is defeated,
        if (this.currentPhase.isDead())
        {
            // Remove it from the Array
            phases.pop();

            // Assign the current Phase to the next item in the Array, if it exists. If not, destroy the boss
            if (!this.isDead())
                this.currentPhase = phases.peek();
            else
                GameScreen.instance.destroyBoss(this);
        }

        // Scroll the boss down the screen with the rest of the level
        if (!this.shooting)
            this.position.y -= Constants.ENEMY_WORLD_SCROLL_SPEED * delta;

        // check whether or not we need to activate our shooter
        if (this.position.y <= this.screenActivationHeight)
            shooting = true;

        // Shoot our Patterns in the current Phase if we are active
        if (this.shooting)
            this.currentPhase.shootPatterns();
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
     * Health for bosses does not matter - it is determined by their Phases
     */
    @Override
    public final void setHealth()
    {
        this.health = 1.0f;
    }

    @Override
    public void reduceHealth(Bullet hitBy)
    {
        this.currentPhase.damageHitPoints(hitBy.damage);
    }

    /**
     * For each Boss, load in Phases appropriate with the Difficulty of the game
     */
    public abstract Array<Phase> loadPhases();

    /**
     * Bosses will need to render themselves in some fashion
     * @param batch
     */
    public abstract void render(SpriteBatch batch);
}
