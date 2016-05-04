package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
     * TODO: remove this or ensure there is a way to set Patterns through the level designer
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
        float rand = Utils.randomFloat();

        // 75% chance to drop powerup, 25% chance to drop points
        if (rand < 0.75f) {
            GameScreen.instance.addCollectible
                    (new SmallPowerCollectible(this.getPosition().x, this.getPosition().y));
        } else {
            // TODO: Add point values here when ready
            GameScreen.instance.addCollectible
                    (new SmallPowerCollectible(this.getPosition().x, this.getPosition().y));
        }
    }

    @Override
    public void loadDefaultPattern()
    {
        this.setPatterns(LevelPatterns.LevelNumber.makePattern(1, this, "drs"));
    }
}
