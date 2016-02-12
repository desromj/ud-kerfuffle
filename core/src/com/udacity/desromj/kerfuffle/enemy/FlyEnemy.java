package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.pattern.template.ShotgunTemplate;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

/**
 * Created by Quiv on 2016-01-31.
 */

public class FlyEnemy extends Enemy
{
    // TODO: Use ShapeRenderer until graphics can be made
    ShapeRenderer renderer;

    public class TestShotgunPattern extends ShotgunTemplate
    {
        public TestShotgunPattern(Shooter parent, Vector2 position, Vector2 velocity, PatternProperties props) {
            super(parent, position, velocity, props);
        }
    }

    public FlyEnemy(Vector2 position)
    {
        super(position);
        renderer = new ShapeRenderer();

        this.setPatterns(
                new Pattern [] {
                        new TestShotgunPattern(
                                this,
                                new Vector2(this.getPosition().x, this.getPosition().y),
                                new Vector2(),
                                new PatternProperties.Builder()
                                        .shotDelay(1.0f / Constants.ENEMY_FLY_SHOTS_PER_SECOND)
                                        .targetted(true)
                                        .arms(4)
                                        .shotsPerArm(6)
                                        .radius(25.0f)
                                        .armAngleOffsetDegrees(15.0f)
                                        .armSpeedModifier(0.8f)
                                        .speed(240.0f)
                                        .mainShotType(BulletType.SMALL_RED_PELLET)
                                        .secondaryShotType(BulletType.SMALL_RED_PELLET)
                                        .createProps()
                        )
                }
        );
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
        renderer.end();
    }
}
