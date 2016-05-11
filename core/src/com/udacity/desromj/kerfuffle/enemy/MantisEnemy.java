package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.collectible.LargePointCollectible;
import com.udacity.desromj.kerfuffle.collectible.LargePowerCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPointCollectible;
import com.udacity.desromj.kerfuffle.collectible.SmallPowerCollectible;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.LevelPatterns;
import com.udacity.desromj.kerfuffle.utility.Utils;

/**
 * Created by Mike on 2016-03-09.
 */
public class MantisEnemy extends Enemy
{
    public MantisEnemy(Vector2 position, float screenActivationHeight)
    {
        super(position, screenActivationHeight);
    }

    public MantisEnemy(Vector2 position, float screenActivationHeight, Pattern[] patterns)
    {
        super(position, screenActivationHeight, patterns);
    }

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_MANTIS_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_MANTIS_RADIUS;
    }

    @Override
    public void dropCollectibles()
    {
        // 75% chance to drop powerup, 25% chance to drop points
        float half = Constants.ENEMY_MANTIS_DROP_RADIUS / 2.0f;

        for (int i = 0; i < Constants.ENEMY_MANTIS_POWERUP_DROPS; i++)
        {
            /*
                Mite Powerup Drops:
                    20% chance for small power
                    20% chance for small point
                    30% chance for large power
                    30% chance for large point
             */
            if (Utils.randomFloat() < 0.2f) {
                GameScreen.instance.addCollectible
                        (new SmallPowerCollectible(
                                this.getPosition().x + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half)));
            } else if (Utils.randomFloat() < 0.4f) {
                GameScreen.instance.addCollectible
                        (new SmallPointCollectible(
                                this.getPosition().x + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half)));
            } else if (Utils.randomFloat() < 0.7f) {
                GameScreen.instance.addCollectible
                        (new LargePowerCollectible(
                                this.getPosition().x + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half)));
            } else {
                GameScreen.instance.addCollectible
                        (new LargePointCollectible(
                                this.getPosition().x + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half),
                                this.getPosition().y + (Constants.ENEMY_MANTIS_DROP_RADIUS * Utils.randomFloat() - half)));
            }
        }
    }

    @Override
    public void loadDefaultPattern()
    {
        this.setPatterns(LevelPatterns.LevelNumber.makePattern(1, this, "yb"));
    }
}
