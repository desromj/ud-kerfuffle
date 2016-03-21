package com.udacity.desromj.kerfuffle.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.enemy.MiteEnemy;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.pattern.RandomBurstPattern;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;
import com.udacity.desromj.kerfuffle.utility.Enums;

/**
 * Created by Mike on 2016-03-17.
 */
public class Level
{
    Viewport viewport;

    // Easier manipulation if we can separate normal Enemies and Bosses
    Array<Shooter> shooters;
    Array<Boss> bosses;
    Array<Spawnable> spawnables;

    Player player;

    private Vector2 spawnPosition = new Vector2(
            Constants.WORLD_WIDTH / 2.0f,
            Constants.WORLD_HEIGHT / 8.0f);

    public Level(Viewport viewport)
    {
        init();
        this.viewport = viewport;
    }

    public void init()
    {
        player = new Player(spawnPosition);

        spawnables = new DelayedRemovalArray<Spawnable>();
        shooters = new DelayedRemovalArray<Shooter>();
        bosses = new DelayedRemovalArray<Boss>();

        Enemy enemy;

        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH / 4.0f,
                        Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);
        enemy.loadDefaultPattern();

        shooters.add(enemy);

        // Second Spiral
        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH * 3.0f / 4.0f,
                        Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);
        enemy.loadDefaultPattern();

        shooters.add(enemy);

        // Random Bursting
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH / 2.0f,
                Constants.WORLD_HEIGHT * 1.5f),
                Constants.ACTIVATION_HEIGHT_MEDIUM);

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

    /**
     * Updates the game state, then renders all game objects in this level
     *
     * @param delta
     * @param renderer
     * @param batch
     */
    public void render(float delta, ShapeRenderer renderer, SpriteBatch batch)
    {
        viewport.apply();
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
         Update logic
          */

        // Update the player
        player.update(delta);

        // Update all Bullets and Patterns onscreen, and remove if offscreen
        for (int i = 0; i < spawnables.size; i++)
        {
            spawnables.get(i).update(delta);

            if (spawnables.get(i).isOffScreen())
                spawnables.removeIndex(i);
        }

        // Update shooters, aside from player. Remove if needed
        boolean bossOnScreen = false;

        for (Shooter boss: bosses) {
            if (boss.isOnScreen()) {
                bossOnScreen = true;
                break;
            }
        }

        /*
            If a boss is onscreen, remove all enemies and do not update them further.
            Only that boss will update. If there are no bosses onscreen, proceed
            with Enemy waves as normal
          */
        if (bossOnScreen)
        {
            for (int i = 0; i < bosses.size; i++) {
                bosses.get(i).update(delta);

                if (bosses.get(i).isDead())
                    bosses.removeIndex(i);
            }
        }
        else {
            for (int i = 0; i < shooters.size; i++) {
                shooters.get(i).update(delta);

                if (shooters.get(i).enemyShouldBeDisposed())
                    shooters.removeIndex(i);
            }
        }

        // Check for Collisions with the player and enemies
        handleCollisions();

        /*
         Render logic
          */

        // ShapeRenderer - background, and Spawnables if applicible
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

        // Sprites - Player, Shooters, Bosses
        batch.getProjectionMatrix().set(GameScreen.instance.getCamera().combined);
        batch.begin();

        for (Shooter shooter: shooters)
            shooter.render(batch);
        for (Boss boss: bosses)
            boss.render(batch);

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
                    player.respawn(spawnPosition);

                    // Clear all Bullets from the screen - give Player chance to react again
                    spawnables.clear();
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

    /**
     * We win when there are no Bosses remaining in the level
     *
     * @return
     */
    public boolean winCondition()
    {
        return bosses.size <= 0;
    }

    /**
     * We lose when the player is out of lives
     *
     * @return
     */
    public boolean loseCondition()
    {
        return player.isOutOfLives();
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

    public Vector2 getPlayerPosition()
    {
        return new Vector2(player.getPosition().x, player.getPosition().y);
    }

    /**
        Utility methods to add Game Objects to the Level - to be used with the LevelLoader class
     */

    public void addPlayer(Vector2 spawnPosition)
    {
        this.spawnPosition = new Vector2(spawnPosition.x, spawnPosition.y);
        this.player = new Player(this.spawnPosition);
    }

    public void addShooter(Shooter shooter)
    {
        shooters.add(shooter);
    }

    public void addBoss(Boss boss)
    {
        bosses.add(boss);
    }

    // Remove a boss from the Array of Bosses in the level
    public void destroyBoss(Boss boss)
    {
        for (int i = 0; i < bosses.size; i++)
        {
            Boss chk = bosses.get(i);

            if (chk == boss)
                bosses.removeIndex(i);
        }
    }
}
