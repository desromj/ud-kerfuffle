package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.collectible.HugePointCollectible;
import com.udacity.desromj.kerfuffle.collectible.LargePointCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPointCollectible;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Phase;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.LevelPatterns;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Mike on 2016-03-21.
 *
 * Mwap is the final boss of the first level
 */
public class MwapBoss extends Boss
{
    public MwapBoss(Vector2 position, float screenActivationHeight) {
        super(position, screenActivationHeight);
    }

    @Override
    protected void setHitRadius()
    {
        this.hitRadius = Constants.BOSS_MWAP_RADIUS;
    }

    @Override
    public void dropCollectibles()
    {
        float half = Constants.BOSS_DROP_RADIUS / 2.0f;
        Collectible coll;

        for (int i = 0; i < Constants.BOSS_MWAP_DROPS_HUGE_POINT; i++) {
            coll = new HugePointCollectible(
                    this.getPosition().x + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half),
                    this.getPosition().y + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half));
            coll.setGravity(Constants.COLLECTIBLE_ACCEL_DUE_TO_GRAVITY_SLOW);
            GameScreen.instance.addCollectible(coll);
        }
        for (int i = 0; i < Constants.BOSS_MWAP_DROPS_LARGE_POINT; i++) {
            coll = new LargePointCollectible(
                    this.getPosition().x + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half),
                    this.getPosition().y + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half));
            coll.setGravity(Constants.COLLECTIBLE_ACCEL_DUE_TO_GRAVITY_SLOW);
            GameScreen.instance.addCollectible(coll);
        }
        for (int i = 0; i < Constants.BOSS_MWAP_DROPS_SMALL_POINT; i++) {
            coll = new SmallPointCollectible(
                    this.getPosition().x + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half),
                    this.getPosition().y + (Constants.BOSS_DROP_RADIUS * Utils.randomFloat() - half));
            coll.setGravity(Constants.COLLECTIBLE_ACCEL_DUE_TO_GRAVITY_SLOW);
            GameScreen.instance.addCollectible(coll);
        }
    }

    @Override
    public Array<Phase> loadPhases()
    {
        Array<Phase> phases = new DelayedRemovalArray<Phase>();
        Array<Pattern> phasePatterns;

        for (int i = 0; i < Constants.BOSS_MWAP_PATTERN_TAGS.length; i++)
        {
            phasePatterns = new DelayedRemovalArray<Pattern>();

            phasePatterns.addAll(LevelPatterns.LevelNumber.makePattern(1, this, Constants.BOSS_MWAP_PATTERN_TAGS[i]));
            phases.add(new Phase(Constants.BOSS_MWAP_PATTERN_HEALTH[i], phasePatterns));
        }

        return phases;
    }
}
