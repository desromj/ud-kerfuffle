package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.collectible.LargePointCollectible;
import com.udacity.desromj.kerfuffle.collectible.LargePowerCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPointCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPowerCollectible;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;
import com.udacity.desromj.kerfuffle.utility.LevelPatterns;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Quiv on 2016-01-31.
 */

public class MiteEnemy extends Enemy
{
    Assets.MiteAssets assets;

    /**
     * Loads a default set of Patterns. Recommended to use the other constructor
     *
     * @param position
     * @param screenActivationHeight
     */
    public MiteEnemy(Vector2 position, float screenActivationHeight)
    {
        super(position, screenActivationHeight);
        assets = Assets.instance.makeMiteAssets();
    }

    public MiteEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
        assets = Assets.instance.makeMiteAssets();
    }

    /*
        TODO: implement movement behaviour here, when it is better understood
     */
    public void move(float delta)
    {
        // this.getPosition().x += Constants.ENEMY_WORLD_SCROLL_SPEED * delta;
    }

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_MITE_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_MITE_RADIUS;
    }

    public void update(float delta)
    {
        super.update(delta);

        this.assets.skeleton.setPosition(this.getPosition().x, this.getPosition().y);

        if (this.isShooting())
            move(delta);
    }

    @Override
    public void render(SpriteBatch batch)
    {
        this.assets.render(batch);
    }

    @Override
    public void dropCollectibles()
    {
        // 75% chance to drop powerup, 25% chance to drop points
        float half = Constants.ENEMY_MITE_DROP_RADIUS / 2.0f;

        for (int i = 0; i < Constants.ENEMY_MITE_POWERUP_DROPS; i++)
        {
            /*
                Mite Powerup Drops:
                    60% chance for small power
                    25% chance for small point
                    10% chance for large power
                    5% chance for large point
             */
            if (Utils.randomFloat() < 0.6f) {
                GameScreen.instance.addCollectible
                        (new SmallPowerCollectible(
                                this.getPosition().x + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half)));
            } else if (Utils.randomFloat() < 0.85f) {
                GameScreen.instance.addCollectible
                        (new SmallPointCollectible(
                                this.getPosition().x + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half)));
            } else if (Utils.randomFloat() < 0.95f) {
                GameScreen.instance.addCollectible
                        (new LargePowerCollectible(
                                this.getPosition().x + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half)));
            } else {
                GameScreen.instance.addCollectible
                        (new LargePointCollectible(
                                this.getPosition().x + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MITE_DROP_RADIUS * Utils.randomFloat() - half)));
            }
        }
    }

    @Override
    public void loadDefaultPattern()
    {
        this.setPatterns(LevelPatterns.LevelNumber.makePattern(1, this, "drs"));
    }
}
