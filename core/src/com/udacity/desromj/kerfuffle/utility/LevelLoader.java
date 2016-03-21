package com.udacity.desromj.kerfuffle.utility;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.desromj.kerfuffle.level.Level;

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


        return level;
    }
}
