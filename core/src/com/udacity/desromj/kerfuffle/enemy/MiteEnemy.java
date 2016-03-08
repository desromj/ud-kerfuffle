package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */

public class MiteEnemy extends Enemy
{
    Assets.MiteAssets assets;

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

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_FLY_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_FLY_RADIUS;
    }

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
}
