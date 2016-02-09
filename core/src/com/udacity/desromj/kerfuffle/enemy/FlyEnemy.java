package com.udacity.desromj.kerfuffle.enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.udacity.desromj.kerfuffle.bullet.BulletType;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.pattern.LargeYellowBallCirclePattern;
import com.udacity.desromj.kerfuffle.pattern.RedPelletCirclePattern;
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

    // TODO: This should probably be moved to the 'Pattern' abstract class
    float shotDelay, cannotShootFor;

    public class TestShotgunPattern extends ShotgunTemplate
    {
        public TestShotgunPattern(Shooter parent, Vector2 position, Vector2 velocity, boolean targetted, int shotsPerArm, int arms, float radius, float armAngleOffsetDegrees, float armSpeedModifier, float speed, boolean angledShots, BulletType mainShotType, BulletType armShotType) {
            super(parent, position, velocity, targetted, shotsPerArm, arms, radius, armAngleOffsetDegrees, armSpeedModifier, speed, angledShots, mainShotType, armShotType);
        }
    }

    public FlyEnemy(Vector2 position)
    {
        super(position);
        renderer = new ShapeRenderer();
        this.shotDelay = 1.0f / Constants.ENEMY_FLY_SHOTS_PER_SECOND;
        cannotShootFor = 0.0f;

        this.setPatterns(
                new Pattern [] {
                        new TestShotgunPattern(
                                this,
                                new Vector2(this.getPosition().x, this.getPosition().y),
                                new Vector2(),
                                true,
                                5,
                                3,
                                80.0f,
                                15.0f,
                                0.8f,
                                240.0f,
                                true,
                                BulletType.SMALL_RED_PELLET,
                                BulletType.SMALL_RED_PELLET
                        )
                }
        );

        /* Funky random/targetted pattern
        this.setPatterns(
                new Pattern [] {
                    new RedPelletCirclePattern(
                            this,
                            new Vector2(this.getPosition().x, this.getPosition().y),
                            new Vector2(0, 0),
                            16,                     // Bullets per circle
                            5.0f,                  // Radius for spawning
                            280.0f,                 // shot speed
                            true                   // targetted
                    ),
                    new RedPelletCirclePattern(
                            this,
                            new Vector2(this.getPosition().x, this.getPosition().y),
                            new Vector2(0, 0),
                            30,                     // Bullets per circle
                            120.0f,                  // Radius for spawning
                            160.0f,                 // shot speed
                            false                   // targetted
                    )
                });
                */
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
        this.cannotShootFor -= delta;

        if (cannotShootFor <= 0.0f)
        {
            cannotShootFor = shotDelay;

            for (Pattern pattern: patterns)
                GameScreen.instance.addSpawnables(pattern.spawnChildren());
        }
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
