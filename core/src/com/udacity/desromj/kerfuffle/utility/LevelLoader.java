package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.ai.MoveBehaviour;
import com.udacity.desromj.kerfuffle.ai.MoveFactory;
import com.udacity.desromj.kerfuffle.enemy.DewBoss;
import com.udacity.desromj.kerfuffle.enemy.FlyEnemy;
import com.udacity.desromj.kerfuffle.enemy.MantisEnemy;
import com.udacity.desromj.kerfuffle.enemy.MiteEnemy;
import com.udacity.desromj.kerfuffle.enemy.MwapBoss;
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

/**
 * Created by Mike on 2016-03-17.
 *
 * LevelLoader loads JSON files which contain enemies, bosses, shot types,
 * activation heights, and enemy/boss types.
 *
 * Placeholder Enemy and Boss images at 48x48 resultion are used. The Player
 * start position is 0, 0 in Overlap 2D, with the world ranging from -210 to +210.
 * As well, to keep the editor useable, Y values are compressed by a factor of 10.
 *
 * Therefore, adding assets through the LevelLoader should do the following to x/y values:
 *
 * X: add Constants.WORLD_WIDTH * 0.5
 * Y: multiply by 10
 *
 */
public class LevelLoader
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

        // Start by adding the player at default spawn position
        level.addPlayer(Constants.PLAYER_DEFAULT_SPAWN_POSITION);

        // Load enemies and bosses from JSON
        JSONParser parser = new JSONParser();
        JSONObject levelJson;

        try
        {
            levelJson = (JSONObject) parser.parse(Gdx.files.internal(path).readString());

            // Load enemies, bosses, and positions from JASON
            JSONObject comp = Utils.castJSON(levelJson, "composite");
            JSONArray objects = Utils.castJSON(comp, "sImages");

            for (Object o: objects)
            {
                JSONObject item = (JSONObject) o;

                if (item.get("imageName").equals(Constants.ENEMY_ID_TAG))
                    addEnemy(item, level);
                if (item.get("imageName").equals(Constants.BOSS_ID_TAG))
                    addBoss(item, level);
            }
        }
        catch (ParseException ex)
        {
            Gdx.app.error(TAG, "Could not parse JSON correctly. Message: " + ex.getMessage());
        }

        return level;
    }

    private static void addBoss(JSONObject item, Level level)
    {
        Vector2 itemXYPos = Utils.castJSONVector2(item);

        /*
            Add image origins to x and y coords to get origin for game object.
            This changes the position from the bottom-left corner to the origin
          */
        float width = Utils.castJSONFloat(item, "originX");
        float height = Utils.castJSONFloat(item, "originY");

        itemXYPos.x = itemXYPos.x + width + Constants.WORLD_WIDTH / 2.0f;
        itemXYPos.y = itemXYPos.y * Constants.WORLD_Y_LEVEL_EDITOR_SCALE + height;

        // Read custom vars from JASON
        Map<String, Object> customs = Utils.readJSONCustomVars(item);

        float heightRatio = new Float(customs.get("heightRatio").toString());
        String bossTypeString = new String(customs.get("bossType").toString());
        Enums.BossType bossType = Enums.BossType.getType(bossTypeString);

        // Determine the type of enemy to add
        Boss boss;

        switch (bossType)
        {
            case MWAP:
                boss = new MwapBoss(itemXYPos, heightRatio);
                break;

            case DEW:
            default:
                boss = new DewBoss(itemXYPos, heightRatio);
                break;
        }

        level.addBoss(boss);
    }

    private static void addEnemy(JSONObject item, Level level)
    {
        Vector2 itemXYPos = Utils.castJSONVector2(item);

        // Add image origins to x and y coords to get origin for game object
        float width = Utils.castJSONFloat(item, "originX");
        float height = Utils.castJSONFloat(item, "originY");

        itemXYPos.x = itemXYPos.x + width + Constants.WORLD_WIDTH / 2.0f;
        itemXYPos.y = itemXYPos.y * Constants.WORLD_Y_LEVEL_EDITOR_SCALE + height;

        // Read custom vars from JASON
        Map<String, Object> customs = Utils.readJSONCustomVars(item);

        float heightRatio = new Float(customs.get("heightRatio").toString());
        String enemyTypeString = new String(customs.get("enemyType").toString());
        Enums.EnemyType enemyType = Enums.EnemyType.getType(enemyTypeString);
        String patternTag = new String(customs.get("patternTag").toString());

        // Determine the type of enemy to add
        Enemy add;

        switch (enemyType)
        {
            case DRAGONFLY:
                add = new FlyEnemy(itemXYPos, heightRatio);
                break;

            case MANTIS:
                add = new MantisEnemy(itemXYPos, heightRatio);
                break;

            case MITE:
            default:
                add = new MiteEnemy(itemXYPos, heightRatio);
                break;
        }

        // custom vars: MoveBehaviours
        if (customs.containsKey("move-tag"))
        {
            MoveFactory.Builder builder = new MoveFactory.Builder();

            if (customs.containsKey("speed")) builder.speed(Float.parseFloat(customs.get("speed").toString()));
            if (customs.containsKey("clockwise")) builder.clockwise(Boolean.parseBoolean(customs.get("clockwise").toString()));
            if (customs.containsKey("retargetDelay")) builder.retargetDelay(Float.parseFloat(customs.get("retargetDelay").toString()));

            MoveBehaviour behaviour = builder.getBehaviour(customs.get("move-tag").toString(), add);
            add.setMoveBehaviour(behaviour);
        }

        // Set patterns based on the current level and pattern tag defined from JASON
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
