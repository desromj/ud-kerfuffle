package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Phase;
import com.udacity.desromj.kerfuffle.pattern.CirclePattern;
import com.udacity.desromj.kerfuffle.pattern.ShotgunPattern;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

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
    public Array<Phase> loadPhases()
    {
        // TODO: Load Phases based on the difficulty here
        Array<Phase> phases = new DelayedRemovalArray<Phase>();
        Array<Pattern> phasePatterns = new DelayedRemovalArray<Pattern>();

        // First Phase

        phasePatterns = new DelayedRemovalArray<Pattern>();

        // Properties should set: shotDelay, mainShotType, arms, radius, speed, targetted
        phasePatterns.add(new CirclePattern(
                this,
                new PatternProperties.Builder()
                        .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                        .shotDelay(0.2f)
                        .arms(24)
                        .speed(120.0f)
                        .targetted(true)
                        .createProps()));

        phasePatterns.add(new SpiralPattern(
                this,
                new PatternProperties.Builder()
                        .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                        .shotDelay(0.06f)
                        .speed(200.0f)
                        .targetted(false)
                        .armAngleOffsetDegrees(5.0f)
                        .createProps()));

        phases.add(new Phase(350.0f, phasePatterns));

        // Second Phase

        phasePatterns = new DelayedRemovalArray<Pattern>();

        // Properties should set: targetted, arms, shotsPerArm, radius, armAngleOffsetDegrees, armSpeedModifier, speed, mainShotType, secondaryShotType
        phasePatterns.add(new ShotgunPattern(
                this,
                new PatternProperties.Builder()
                        .targetted(true)
                        .arms(4)
                        .shotsPerArm(7)
                        .armAngleOffsetDegrees(7.5f)
                        .armSpeedModifier(0.95f)
                        .speed(250.0f)
                        .createProps()));

        phases.add(new Phase(120.0f, phasePatterns));

        // Third Phase
        phasePatterns = new DelayedRemovalArray<Pattern>();

        // Properties should set: shotDelay, mainShotType, arms, radius, speed, targetted
        phasePatterns.add(new CirclePattern(
                this,
                new PatternProperties.Builder()
                        .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                        .shotDelay(0.2f)
                        .arms(24)
                        .speed(120.0f)
                        .targetted(false)
                        .createProps()));

        phases.add(new Phase(220.0f, phasePatterns));

        return phases;
    }
}
