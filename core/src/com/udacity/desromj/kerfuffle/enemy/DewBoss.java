package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Phase;
import com.udacity.desromj.kerfuffle.pattern.CirclePattern;
import com.udacity.desromj.kerfuffle.pattern.DirectShotPattern;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;
import com.udacity.desromj.kerfuffle.utility.LevelPatterns;

/**
 * Created by Mike on 2016-03-21.
 *
 * Dew is the halfway Boss of the first level
 */
public class DewBoss extends Boss
{
    // TODO: Replace with DewAssets
    Assets.MiteAssets assets;

    public DewBoss(Vector2 position, float screenActivationHeight) {
        super(position, screenActivationHeight);
        assets = Assets.instance.makeMiteAssets();
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        this.assets.skeleton.setPosition(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        this.assets.render(batch);
    }

    @Override
    protected void setHitRadius()
    {
        this.hitRadius = Constants.BOSS_DEW_RADIUS;
    }

    @Override
    public void dropCollectibles()
    {
        // TODO: Implement powerup drops here, using GameScreen.instance.addCollectibles()
    }

    @Override
    public Array<Phase> loadPhases()
    {
        Array<Phase> phases = new DelayedRemovalArray<Phase>();
        Array<Pattern> phasePatterns;

        for (int i = 0; i < Constants.BOSS_DEW_PATTERN_TAGS.length; i++)
        {
            phasePatterns = new DelayedRemovalArray<Pattern>();

            phasePatterns.addAll(LevelPatterns.LevelNumber.makePattern(1, this, Constants.BOSS_DEW_PATTERN_TAGS[i]));
            phases.add(new Phase(Constants.BOSS_DEW_PATTERN_HEALTH[i], phasePatterns));
        }

        return phases;
    }
}
