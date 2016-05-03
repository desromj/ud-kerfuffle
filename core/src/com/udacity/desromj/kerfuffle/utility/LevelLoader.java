package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.Gdx;
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
import com.udacity.desromj.kerfuffle.screen.GameScreen;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class LevelLoader
/**
 * Created by Mike on 2016-03-17.
 */
{
    public static final String TAG = LevelLoader.class.getName();

    private LevelLoader() {}

    /**
     * Enemies loaded require the following tags:
     *      - x/y coordinates (included by default in JSONObject)
     *          - should add 1/2 the width and height of the sprite used to place it
     *      - heightRatio (passed as a ratio 0.0 - 1.0
     *      - patternTag (see LevelPatterns enum classes)
     *      - enemyType (determines type of enemy to spawn)
     *
     * @param path
     * @param viewport
     * @return
     */
    public static Level load(String path, Viewport viewport)
    {
        Level level = new Level(viewport);

        // TODO: Load level data from JSON files
        level.addPlayer(Constants.PLAYER_DEFAULT_SPAWN_POSITION);

        // TODO: Load enemies from JSON
        JSONParser parser = new JSONParser();
        JSONObject levelJson;

        try
        {
            levelJson = (JSONObject) parser.parse(Gdx.files.internal(path).readString());

            // Load images and 9patchs from the JSON file
            JSONObject comp = Utils.castJSON(levelJson, "composite");
            JSONArray objects = Utils.castJSON(comp, "sImages");

            for (Object o: objects)
            {
                JSONObject item = (JSONObject) o;

                // Add enemy
                if (item.get("imageName").equals(Constants.ENEMY_ID_TAG))
                {
                    float width = Utils.castJSONFloat(item, "originX");
                    float height = Utils.castJSONFloat(item, "originY");

                    Vector2 itemXYPos = Utils.castJSONVector2(item);

                    itemXYPos.x += width + Constants.WORLD_WIDTH / 2.0f;
                    itemXYPos.y += height;

                    Map<String, Object> customs = Utils.readJSONCustomVars(item);

                    float heightRatio = new Float(customs.get("heightRatio").toString());
                    String enemyTypeString = new String(customs.get("enemyType").toString());
                    Enums.EnemyType enemyType = Enums.EnemyType.getType(enemyTypeString);
                    String patternTag = new String(customs.get("patternTag").toString());

                    Enemy add;

                    switch (enemyType)
                    {
                        case MITE:
                            add = new MiteEnemy(
                                    itemXYPos,
                                    heightRatio
                            );

                            break;

                        default:
                            continue;
                    }

                    add.setPatterns(
                            LevelPatterns.LevelNumber.makePattern(
                                    GameScreen.instance.getCurrentLevelNum(),
                                    add,
                                    patternTag
                            )
                    );

                    level.addShooter(add);
                }
            }
        }
        catch (ParseException ex)
        {
            Gdx.app.error(TAG, "Could not parse JSON correctly. Message: " + ex.getMessage());
        }

        /*
        Enemy enemy;

        // Add first set of enemies

        // First spiral
        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH / 4.0f,
                        Constants.WORLD_HEIGHT),
                0.75f);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "rsg"));

        level.addShooter(enemy);

        // Second spiral
        enemy = new MiteEnemy(
                new Vector2(
                        Constants.WORLD_WIDTH * 3.0f / 4.0f,
                        Constants.WORLD_HEIGHT),
                0.75f);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "rsg"));

        level.addShooter(enemy);

        // Random bursting
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH / 2.0f,
                Constants.WORLD_HEIGHT * 1.5f),
                0.625f);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        level.addShooter(enemy);

        // Add a Boss

        Boss midBoss = new DewBoss(new Vector2(
                Constants.WORLD_WIDTH / 2.0f,
                Constants.WORLD_HEIGHT * 2.5f),
                0.75f);

        level.addBoss(midBoss);

        // More shooters above the Boss

        // First burst
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH * 3.0f / 4.0f,
                Constants.WORLD_HEIGHT * 4.0f),
                0.625f);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        // Second burst
        level.addShooter(enemy);
        enemy = new MiteEnemy(new Vector2(
                Constants.WORLD_WIDTH / 4.0f,
                Constants.WORLD_HEIGHT * 4.0f),
                0.625f);

        enemy.setPatterns(LevelPatterns.LevelNumber.makePattern(1, enemy, "yb"));

        level.addShooter(enemy);
        */

        return level;
    }
}
