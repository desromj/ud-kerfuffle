package com.udacity.desromj.kerfuffle.entity;

import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-01-27.
 */
public abstract class Enemy extends Shooter
{
    protected float screenActivationHeight;
    protected Pattern [] patterns;

    public Enemy(Vector2 position, float heightRatio)
    {
        super(position);
        this.screenActivationHeight = heightRatio * Constants.WORLD_HEIGHT;
    }

    public Enemy(Vector2 position, float heightRatio, Pattern [] patterns)
    {
        super(position);
        setPatterns(patterns);
        this.screenActivationHeight = heightRatio * Constants.WORLD_HEIGHT;
    }

    public final void setPatterns(Pattern [] patterns)
    {
        this.patterns = patterns;
    }

    public abstract void loadDefaultPattern();

    @Override
    public void update(float delta)
    {
        if (!this.shooting)
            this.position.y -= Constants.ENEMY_WORLD_SCROLL_SPEED * delta;

        // check whether or not we need to activate our shooter
        if (!shooting && this.position.y <= this.screenActivationHeight)
        {
            for (Pattern pattern : patterns) {
                pattern.props.setCannotWaveFor(0.0f);
                pattern.props.setCannotShootFor(0.0f);
            }

            shooting = true;
        }

        // Shoot our Patterns if active
        if (this.shooting) {
            for (Pattern pattern : patterns)
                pattern.shoot();
        }
    }
}
