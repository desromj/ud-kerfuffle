package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import org.json.simple.JSONObject;

/**
 * Created by Mike on 2016-03-01.
 *
 * Utility methods to help with common tasks in the program.
 */
public class Utils
{
    private Utils() {}

    /**
     * Draws a TextureRegion using an open SpriteBatch
     *
     * @param batch The open SpriteBatch to use to draw this region
     * @param region The TextureRegion to draw
     * @param position Position to draw the TextureRegion at
     */
    public static void drawTextureRegion(SpriteBatch batch, TextureRegion region, Vector2 position) {
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    /**
     * Returns the number of seconds which have elapsed since the passed nanoTime()
     *
     * @param timeNanos A TimeUtils.nanoTime() value to compare to the current nanoTime()
     * @return The number of seconds elapsed between now and and passed time
     */
    public static float secondsSince(long timeNanos) {
        return MathUtils.nanoToSec * (TimeUtils.nanoTime() - timeNanos);
    }

    /**
     * Utility JSON reader - automatically casts returned objects to
     * the declared class type of the assigning variable
     *
     * @param object JSONObject to read a key value from
     * @param key The key to read properties for
     * @param <T> Data type to cast and return the found property key
     * @return The casted property value
     */
    public static <T extends Object> T castJSON(JSONObject object, String key)
    {
        return (T) object.get(key);
    }

    /**
     * Utility JSON reader to automatically handle casting to expected classes
     * Specifically returns primitive float values, first casting to a Number
     *
     * @param object JSONObject to read a key value from
     * @param key The key to read properties for
     * @return float field containing the requested data
     */
    public static float castJSONFloat(JSONObject object, String key)
    {
        Number num = castJSON(object, key);
        return num.floatValue();
    }

    /**
     * Reads the fields "x" and "y" from a JSON Object and creates a result Vector2
     * X and Y values are set to 0.0 if they do not exist in the object, but a value WILL be returned
     *
     * @param object the JSONObject to read data fields from
     * @return A new Vector2 with x/y coordinates
     */
    public static Vector2 castJSONVector2(JSONObject object)
    {
        float x = 0.0f, y = 0.0f;

        if (object.containsKey("x")) x = castJSONFloat(object, "x");
        if (object.containsKey("y")) y = castJSONFloat(object, "y");

        return new Vector2(x, y);
    }
}
