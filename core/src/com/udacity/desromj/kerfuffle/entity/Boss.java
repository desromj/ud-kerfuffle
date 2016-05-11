package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-03-16.
 */
public abstract class Boss extends Shooter
{
    Assets.SpineAnimationAsset asset;

    public Array<Phase> phases;
    protected float screenActivationHeight;

    private Phase currentPhase;

    /**
     * Bosses do not use their own Patterns - they read Patterns from the
     * Phases of the Boss fight instead
     *
     * @param position
     * @param heightRatio
     */
    protected Boss(Vector2 position, float heightRatio) {
        super(position);
        this.screenActivationHeight = heightRatio * Constants.WORLD_HEIGHT;
        this.phases = this.loadPhases();
        this.currentPhase = phases.peek();
        asset = Assets.instance.makeAsset(this);
    }

    @Override
    public void update(float delta)
    {
        // If the current Phase is defeated,
        if (this.currentPhase.isDead())
        {
            // Remove it from the Array
            try {
                phases.pop();
            } catch (Exception ex) {}

            // Assign the current Phase to the next item in the Array, if it exists
            if (!this.isDead())
                this.currentPhase = phases.peek();
            else
                return;
        }

        // check whether or not we need to activate our shooter
        if (this.position.y <= this.screenActivationHeight)
            shooting = true;

        // Shoot patterns if shooting, Scroll the boss down the screen with the rest of the level if not
        if (this.shooting)
            this.currentPhase.shootPatterns();
        else
            this.position.y -= Constants.ENEMY_WORLD_SCROLL_SPEED * delta;

        // Cling the skeleton position to this boss position
        this.asset.skeleton.setPosition(this.getPosition().x, this.getPosition().y);
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

    @Override
    public void render(SpriteBatch batch)
    {
        this.asset.render(batch);
    }

    /**
     * For each Boss, load in Phases appropriate with the Difficulty of the game
     */
    public abstract Array<Phase> loadPhases();
}
