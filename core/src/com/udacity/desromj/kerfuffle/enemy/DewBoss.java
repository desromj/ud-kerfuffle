package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Phase;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Mike on 2016-03-21.
 *
 * Dew is the halfway Boss of the first level
 */
public class DewBoss extends Boss
{
    public DewBoss(Vector2 position, float screenActivationHeight) {
        super(position, screenActivationHeight);
    }

    @Override
    public Array<Phase> loadPhases()
    {
        // TODO: Load Phases based on the difficulty here

        return null;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        // TODO: Load the Sprites through the Assets utility class

    }

    @Override
    protected void setHitRadius()
    {
        this.hitRadius = Constants.BOSS_DEW_RADIUS;
    }
}
