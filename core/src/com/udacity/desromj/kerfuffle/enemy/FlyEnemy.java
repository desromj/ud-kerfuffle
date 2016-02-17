package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.pattern.ShotgunPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */

public class FlyEnemy extends Enemy
{
    // TODO: Use ShapeRenderer until graphics can be made
    ShapeRenderer renderer;

    public FlyEnemy(Vector2 position)
    {
        super(position);
        renderer = new ShapeRenderer();
    }

    public FlyEnemy(Vector2 position, Pattern [] patterns)
    {
        super(position, patterns);
        renderer = new ShapeRenderer();
    }

    @Override
    protected void setHealth() {
        this.health = Constants.ENEMY_FLY_HEALTH;
    }

    @Override
    protected void setHitRadius() {
        this.hitRadius = Constants.ENEMY_FLY_RADIUS;
    }

    @Override
    public void update(float delta)
    {
        for (Pattern pattern: patterns)
            pattern.shoot();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        renderer.setProjectionMatrix(GameScreen.instance.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.ENEMY_FLY_COLOUR);
        renderer.circle(this.getPosition().x, this.getPosition().y, this.hitRadius);

        // TODO: Render Patterns this enemy is holding
        renderer.setColor(Constants.PATTERN_DEBUG_COLOR);

        for (Spawnable spawnable: this.patterns)
            spawnable.render(renderer);

        renderer.end();
    }
}
