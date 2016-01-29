package com.udacity.desromj.kerfuffle.pattern;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.udacity.desromj.kerfuffle.entity.Pattern;
import com.udacity.desromj.kerfuffle.entity.Spawnable;

/**
 * Created by Quiv on 2016-01-29.
 */
public class PlayerBulletPattern extends Pattern
{
    public PlayerBulletPattern(Vector2 position, Vector2 velocity) {
        super(position, velocity);
    }

    @Override
    public void render(ShapeRenderer renderer)
    {

    }

    @Override
    public Array<Spawnable> spawnChildren(Vector2 origin) {
        return null;
    }
}
