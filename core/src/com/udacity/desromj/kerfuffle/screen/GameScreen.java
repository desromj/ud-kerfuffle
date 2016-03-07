package com.udacity.desromj.kerfuffle.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.enemy.FlyEnemy;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.pattern.RandomBurstPattern;
import com.udacity.desromj.kerfuffle.pattern.SpiralPattern;
import com.udacity.desromj.kerfuffle.utility.Assets;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-01-27.
 */
public class GameScreen extends ScreenAdapter implements InputProcessor
{
    public static final GameScreen instance = new GameScreen();

    Viewport viewport;

    ShapeRenderer renderer;
    SpriteBatch batch;

    Array<Shooter> shooters;
    Player player;
    Array<Spawnable> spawnables;

    private Vector2 playerSpawnPoint = new Vector2(
        Constants.WORLD_WIDTH / 2.0f,
        Constants.WORLD_HEIGHT / 8.0f);

    private GameScreen()
    {
        init();
    }

    public void init()
    {
        player = new Player(playerSpawnPoint);

        spawnables = new DelayedRemovalArray<Spawnable>();
        shooters = new DelayedRemovalArray<Shooter>();

        Enemy enemy;

        /*
        enemy = new FlyEnemy(new Vector2(
                    Constants.WORLD_WIDTH / 4.0f,
                    (Constants.WORLD_HEIGHT * 6.0f) / 8.0f));

        // Check Shotgun Pattern
        enemy.setPatterns(new Pattern[]{
                new ShotgunPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.1f / Constants.ENEMY_FLY_SHOTS_PER_SECOND)
                                .targetted(true)
                                .arms(3)
                                .shotsPerArm(6)
                                .armAngleOffsetDegrees(30.0f)
                                .armSpeedModifier(0.9f)
                                .speed(200.0f)
                                .shotsPerWave(5)
                                .waveDelay(5.0f)
                                .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .secondaryShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .createProps()
                )
        });

        shooters.add(enemy);

        enemy = new FlyEnemy(new Vector2(
                Constants.WORLD_WIDTH * 3.0f / 4.0f,
                (Constants.WORLD_HEIGHT * 6.0f) / 8.0f));

        // Check Circle Pattern
        enemy.setPatterns(new Pattern[]{
                new CirclePattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(1.2f / Constants.ENEMY_FLY_SHOTS_PER_SECOND)
                                .targetted(false)
                                .arms(20)
                                .speed(160.0f)
                                .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                .createProps()
                )
        });

        shooters.add(enemy);
        */

        enemy = new FlyEnemy(new Vector2(
                    Constants.WORLD_WIDTH / 4.0f,
                    Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);

        // Check Spiral Pattern
        enemy.setPatterns(new Pattern[]{
                new SpiralPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.03f)
                                .targetted(false)
                                .armAngleOffsetDegrees(12.0f)
                                .speed(180.0f)
                                .shotsPerWave(150)
                                .waveDelay(2.5f)
                                .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .createProps()
                ),
                new SpiralPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.01f)
                                .targetted(false)
                                .armAngleOffsetDegrees(-7.5f)
                                .speed(140.0f)
                                .shotsPerWave(160)
                                .waveDelay(1.0f)
                                .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .createProps()
                )
        });

        shooters.add(enemy);

        // Second Spiral
        enemy = new FlyEnemy(new Vector2(
                    Constants.WORLD_WIDTH * 3.0f / 4.0f,
                    Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);

        // Check Spiral Pattern
        enemy.setPatterns(new Pattern[]{
                new SpiralPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.015f)
                                .targetted(false)
                                .armAngleOffsetDegrees(6.0f)
                                .speed(220.0f)
                                .shotsPerWave(100)
                                .waveDelay(3.0f)
                                .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .createProps()
                ),
                new SpiralPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.015f)
                                .targetted(false)
                                .armAngleOffsetDegrees(-8.0f)
                                .speed(250.0f)
                                .shotsPerWave(225)
                                .waveDelay(2.0f)
                                .mainShotType(Enums.BulletType.SMALL_RED_PELLET)
                                .createProps()
                )
        });

        shooters.add(enemy);

        // Random Bursting
        enemy = new FlyEnemy(new Vector2(
                    Constants.WORLD_WIDTH / 2.0f,
                    Constants.WORLD_HEIGHT * 1.5f),
                Constants.ACTIVATION_HEIGHT_MEDIUM);

        // Check Spiral Pattern
        enemy.setPatterns(new Pattern[]{
                new RandomBurstPattern(
                        enemy,
                        new PatternProperties.Builder()
                                .shotDelay(0.02f)
                                .speed(300.0f)
                                .mainShotType(Enums.BulletType.LARGE_YELLOW_BALL)
                                .createProps()
                )
        });

        shooters.add(enemy);
    }



    @Override
    public void render(float delta)
    {
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
         Update logic
          */
        player.update(delta);

        for (int i = 0; i < spawnables.size; i++)
        {
            spawnables.get(i).update(delta);

            if (spawnables.get(i).isOffScreen())
                spawnables.removeIndex(i);
        }

        for (Shooter shooter: shooters)
            shooter.update(delta);

        /*
        Check for Collisions with the player and enemies
         */
        handleCollisions();

        /*
         Render logic
          */
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.BACKGROUND_COLOR);
        renderer.rect(
                0,
                0,
                Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT
        );

        for (Spawnable sp: spawnables)
            sp.render(renderer);

        renderer.end();

        // TODO: Debug - starts and closes the ShapeRenderer - once graphics exist, move this to spritebatch rendering
        for (Shooter shooter: shooters)
            shooter.render(batch);

        // Sprites
        batch.getProjectionMatrix().set(viewport.getCamera().combined);
        batch.begin();

        player.render(batch);

        batch.end();
    }

    /**
     * Collision logic for game objects
     */
    private void handleCollisions()
    {
        boolean playerHit = false;

        for (int j = 0; j < spawnables.size; j++)
        {
            // Check collision for every bullet
            if (spawnables.get(j) instanceof Bullet)
            {
                Bullet bullet = (Bullet) spawnables.get(j);

                // Check for bullets hitting the player
                if (!playerHit && bullet.isColliding(player))
                {
                    playerHit = true;
                    player.init(playerSpawnPoint);
                }

                // Check for player bullets hitting the enemy - ONLY player bullets
                if (bullet.getParent() == player)
                {
                    for (int i = 0; i < shooters.size; i++)
                    {
                        if (bullet.isColliding(shooters.get(i)))
                        {
                            // Reduce the shooter's health when hit, and remove it if dead
                            shooters.get(i).reduceHealth(bullet);

                            if (shooters.get(i).isDead())
                                shooters.removeIndex(i);

                            // Remove the bullet when it hits an enemy
                            spawnables.removeIndex(j);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show()
    {
        Assets.instance.init();
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
    }


    /*
        Utility/Spawning Methods
     */
    public void addSpawnable(Spawnable spawnable)
    {
        this.spawnables.add(spawnable);
    }

    public void addSpawnables(Array<Spawnable> spawnables)
    {
        for (Spawnable spawnable: spawnables)
            this.spawnables.add(spawnable);
    }

    /*
    Getters and Setters
     */
    public Camera getCamera()
    {
        return viewport.getCamera();
    }

    public Vector2 getPlayerPosition()
    {
        return new Vector2(player.getPosition().x, player.getPosition().y);
    }

    /*
        Input Processing Overrides
     */



    @Override
    public boolean keyDown(int keycode)
    {
        // Reset the game by pressing 'R' - TODO: remove debug control
        if (keycode == Input.Keys.R)
            instance.init();

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
