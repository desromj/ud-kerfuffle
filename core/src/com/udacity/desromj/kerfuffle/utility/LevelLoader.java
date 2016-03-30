package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.enemy.DewBoss;
import com.udacity.desromj.kerfuffle.enemy.MiteEnemy;
import com.udacity.desromj.kerfuffle.entity.Boss;
import com.udacity.desromj.kerfuffle.entity.Enemy;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.PatternProperties;
import com.udacity.desromj.kerfuffle.entity.Player;
import com.udacity.desromj.kerfuffle.entity.Shooter;
import com.udacity.desromj.kerfuffle.entity.Spawnable;
import com.udacity.desromj.kerfuffle.level.Level;
import com.udacity.desromj.kerfuffle.pattern.RandomBurstPattern;

public class LevelLoader
/**
 * Created by Mike on 2016-03-17.
 */
{
    public static final String TAG = LevelLoader.class.getName();

    private LevelLoader() {}

    public static Level load(String path, Viewport viewport)
    {
        Level level = new Level(viewport);

        // TODO: Load level data from JSON files
        level.addPlayer(Constants.PLAYER_DEFAULT_SPAWN_POSITION);

        Enemy enemy;

        /*
        Add first set of enemies
         */

        // First spiral
        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH / 4.0f,
                        Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "rsg"));

        level.addShooter(enemy);

        // Second spiral
        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH * 3.0f / 4.0f,
                        Constants.WORLD_HEIGHT),
                Constants.ACTIVATION_HEIGHT_HIGH);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "rsg"));

        level.addShooter(enemy);

        // Random bursting
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH / 2.0f,
                Constants.WORLD_HEIGHT * 1.5f),
                Constants.ACTIVATION_HEIGHT_MEDIUM);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        level.addShooter(enemy);

        /*
         Add a Boss
          */

        Boss midBoss = new DewBoss(new Vector2(
                Constants.WORLD_WIDTH / 2.0f,
                Constants.WORLD_HEIGHT * 2.5f),
                Constants.ACTIVATION_HEIGHT_HIGH);

        level.addBoss(midBoss);

        /*
         More shooters above the Boss
          */

        // First burst
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH * 3.0f / 4.0f,
                Constants.WORLD_HEIGHT * 4.0f),
                Constants.ACTIVATION_HEIGHT_MEDIUM);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        // Second burst
        level.addShooter(enemy);
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH / 4.0f,
                Constants.WORLD_HEIGHT * 4.0f),
                Constants.ACTIVATION_HEIGHT_MEDIUM);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        level.addShooter(enemy);

        return level;
    }
}
