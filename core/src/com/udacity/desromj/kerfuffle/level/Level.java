package com.udacity.desromj.kerfuffle.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.ai.HomingMoveBehaviour;
import com.udacity.desromj.kerfuffle.bullet.PlayerBombBullet;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Bullet;
import com.udacity.desromj.kerfuffle.entity.Collectible;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.screen.GameScreen;
import com.udacity.desromj.kerfuffle.utility.Constants;

import java.util.Comparator;

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
    Array<Collectible> collectibles;
    Array<ParticleEffect> effects;

    Player player;

    public Level(Viewport viewport)
    {
        this.viewport = viewport;
        init();
    }

    public void init()
    {
        spawnables = new DelayedRemovalArray<Spawnable>();
        shooters = new DelayedRemovalArray<Shooter>();
        bosses = new DelayedRemovalArray<Boss>();
        collectibles = new DelayedRemovalArray<Collectible>();
        effects = new Array<ParticleEffect>();
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

        // Update logic
        preRenderUpdate(delta);

        // Check for Collisions with the player and enemies
        handleCollisions();

        // Handle all rendering logic
        renderBackground(renderer);
        renderSpriteBackground(batch);

        doShapeRender(renderer);
        doSpriteRender(batch);
    }

    private void preRenderUpdate(float delta)
    {
        // Update the player
        if (!loseCondition())
            player.update(delta);

        // Update all collectibles, remove if offscreen
        for (int i = 0; i < collectibles.size; i++)
        {
            collectibles.get(i).update(delta);

            if (collectibles.get(i).isOffScreen())
                collectibles.removeIndex(i);
        }

        // Update all Bullets and Patterns onscreen, and remove if offscreen
        for (int i = 0; i < spawnables.size; i++)
        {
            spawnables.get(i).update(delta);

            if (spawnables.get(i).isOffScreen())
                spawnables.removeIndex(i);
        }

        // Update Particle Effects
        for (int i = 0; i < effects.size; i++)
        {
            effects.get(i).update(delta);

            if (effects.get(i).isComplete())
                effects.removeIndex(i);
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

        // Bosses - If boss is onscreen, only update the boss onscreen and no other bosses
        for (int i = 0; i < bosses.size; i++) {
            if (bossOnScreen && !bosses.get(i).isOnScreen())
                continue;

            bosses.get(i).update(delta);
        }

        // Enemies - If boss is onscreen, only update onscreen enemies. Otherwise, update all
        for (int i = 0; i < shooters.size; i++) {

            if (bossOnScreen && !shooters.get(i).isOnScreen())
                continue;

            shooters.get(i).update(delta);

            if (shooters.get(i).enemyShouldBeDisposed())
                shooters.removeIndex(i);
        }
    }

    private void renderBackground(ShapeRenderer renderer)
    {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Constants.BACKGROUND_COLOR);
        renderer.rect(
                0,
                0,
                Constants.WORLD_WIDTH,
                Constants.WORLD_HEIGHT
        );
        renderer.end();
    }

    private void doShapeRender(ShapeRenderer renderer)
    {
        // ShapeRenderer - background, and Spawnables if applicible
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        /*
         Attempt to sort the bullets so the render from largest to smallest
         TODO: May be a big performance drain, we will see
          */
        spawnables.sort(new Comparator<Spawnable>() {
            @Override
            public int compare(Spawnable o1, Spawnable o2) {

                // Do no sorting if we're not working with two Bullets
                if (!(o1 instanceof Bullet && o2 instanceof Bullet))
                    return 0;

                Bullet first = (Bullet) o1;
                Bullet second = (Bullet) o2;

                if (second.getShotRadius() > first.getShotRadius())
                    return 1;
                else if (second.getShotRadius() < first.getShotRadius())
                    return -1;
                else
                    return 0;
            }
        });

        // Render Collectibles
        for (Collectible c: collectibles)
            c.render(renderer);

        // Render Bullets and Patterns
        for (Spawnable sp: spawnables)
            sp.render(renderer);

        // Render player hitbox, if present
        if (player.isFocussed())
            player.renderShapes(renderer);

        renderer.end();
    }

    private void renderSpriteBackground(SpriteBatch batch)
    {
        // Sprites - Player comes under boolets to check collision easier
        batch.getProjectionMatrix().set(GameScreen.instance.getCamera().combined);
        batch.begin();

        if (!loseCondition())
            player.render(batch);

        batch.end();
    }

    private void doSpriteRender(SpriteBatch batch)
    {
        // Sprites - Shooters, Bosses come in front of boolets
        batch.getProjectionMatrix().set(GameScreen.instance.getCamera().combined);
        batch.begin();

        for (Shooter shooter: shooters)
            shooter.render(batch);
        for (Boss boss: bosses)
            boss.render(batch);

        for (ParticleEffect effect: effects)
            effect.draw(batch);

        batch.end();
    }
    /**
     * Collision logic for game objects
     */
    private void handleCollisions()
    {
        // No necessary collision if player is dead
        if (player.isOutOfLives())
            return;

        boolean playerHit = false;

        // For each bullet and pattern
        for (int j = 0; j < spawnables.size; j++)
        {
            Spawnable spawn = spawnables.get(j);

            // Check collision for every bullet - bombs first, then remaining Bullets
            if (spawn instanceof PlayerBombBullet)
            {
                // Bombs destroy any non-player bullet within their radius
                for (int k = 0; k < spawnables.size; k++)
                {
                    Spawnable other = spawnables.get(k);

                    if (other == spawn || other.getParent() == player)
                        continue;

                    if (other.getPosition().dst(spawn.getPosition()) <= Constants.PLAYER_BOMB_RADIUS)
                        spawnables.removeIndex(k);
                }

                // Check collision for enemies and bosses, without destroying the Bomb
                checkCollision((Bullet)spawn, shooters, false);
                checkCollision((Bullet)spawn, bosses, false);
            }
            else if (spawn instanceof Bullet)
            {
                // Normal Bullet collisions
                Bullet bullet = (Bullet) spawnables.get(j);

                // Check for bullets grazing the player
                if (!bullet.isGrazed() && bullet.isGrazing(player))
                    bullet.graze();

                // Check for bullets hitting the player, if player is not invincible
                if (!player.isInvincible() && !playerBombIsOnscreen() && !playerHit && bullet.isColliding(player)) {
                    playerHit = true;
                    player.wasHit();
                    player.respawn(Constants.PLAYER_DEFAULT_SPAWN_POSITION);

                    // Clear all Bullets from the screen - give Player chance to react again
                    spawnables.clear();

                    // Make all homing enemies onscreen not retarget so they will slowly fly offscreen
                    for (Shooter shooter: shooters) {
                        if (shooter.isOnScreen() && shooter instanceof Enemy) {
                            Enemy enemy = (Enemy) shooter;
                            if (enemy.getMoveBehaviour() instanceof HomingMoveBehaviour)
                                ((HomingMoveBehaviour) enemy.getMoveBehaviour()).setRetargetIn(999f);
                        }
                    }
                }

                // Check for player bullets hitting the enemy or bosses - destroy on collision
                if (bullet.getParent() == player)
                {
                    checkCollision(bullet, shooters, true);
                    checkCollision(bullet, bosses, true);
                }
            }
        }

        // Handle each collectible, if the player has picked it up
        for (int i = 0; i < collectibles.size; i++) {
            Collectible collectible = collectibles.get(i);

            if (collectible.isColliding(player)) {
                collectible.playSound();
                collectible.collectEffect(player);
                collectibles.removeIndex(i);
            } else if (collectible.isWithinPickupRadius(player)) {
                collectible.changeVelocityTowardPlayer(player);
            }
        }
    }

    /**
     * Utility collision checker to iterate through an Array of Shooter implementations, to see
     * if the passed Bullet object is colliding with any objects in the array. If so, it
     * reduces the health of the target, drops any collectibles if necessary, and proceeds to
     * destroy the bullet if requested.
     *
     * @param bullet The bullet to check against objects in the Array
     * @param array The array to check the bullet against all items from
     * @param destroyBullet true to destroy the Bullet after doing collision effects. Generally
     *                      true for normal Bullets and false for Bombs
     */
    private void checkCollision(Bullet bullet, Array<? extends Shooter> array, boolean destroyBullet)
    {
        // Collision with normal enemies
        for (int i = 0; i < array.size; i++)
        {
            if (bullet.isColliding(array.get(i)))
            {
                // Reduce the shooter's health when hit, and remove it if dead
                array.get(i).reduceHealth(bullet);

                if (array.get(i).isDead()) {
                    array.get(i).dropCollectibles();
                    array.removeIndex(i);
                }

                // Remove the bullet when it hits an enemy, if requested
                if (destroyBullet) {
                    for (int j = 0; j < spawnables.size; j++) {
                        if (spawnables.get(j) == bullet) {
                            spawnables.removeIndex(j);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * We win when there are no Bosses remaining in the level, and all collectibles have been picked up
     *
     * @return
     */
    public boolean winCondition()
    {
        return bosses.size <= 0 && collectibles.size <= 0;
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

    public void removeSpawnable(Spawnable spawnable)
    {
        for (int i = 0; i < spawnables.size; i++) {
            if (spawnables.get(i).equals(spawnable)) {
                spawnables.removeIndex(i);
                break;
            }
        }
    }

    public boolean playerBombIsOnscreen()
    {
        for (Spawnable spawnable: spawnables)
            if (spawnable instanceof PlayerBombBullet)
                return true;
        return false;
    }

    public void addParticleEffect(ParticleEffect effect) { this.effects.add(effect); }

    public void addCollectible(Collectible collectible)
    {
        this.collectibles.add(collectible);
    }

    public void addCollectibles(Array<Collectible> collectibles)
    {
        for (Collectible collectible: collectibles)
            this.collectibles.add(collectible);
    }

    public Vector2 getPlayerPosition()
    {
        return new Vector2(player.getPosition().x, player.getPosition().y);
    }

    // TODO: Dangerous method, used for debug only. Remove this later
    public final Player getPlayer()
    {
        return this.player;
    }

    /**
        Utility methods to add Game Objects to the Level - to be used with the LevelLoader class
     */

    public void addPlayer(Vector2 spawnPosition)
    {
        this.player = new Player(spawnPosition);
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

            if (chk == boss) {
                chk.dropCollectibles();
                bosses.removeIndex(i);
            }
        }
    }
}
